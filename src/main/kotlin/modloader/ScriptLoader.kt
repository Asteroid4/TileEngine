package asteroid4.modloader

import asteroid4.ProgramData
import org.luaj.vm2.Globals
import org.luaj.vm2.LuaThread
import org.luaj.vm2.LuaValue
import org.luaj.vm2.lib.*
import org.luaj.vm2.lib.jse.JseBaseLib
import org.luaj.vm2.lib.jse.JseMathLib
import java.io.File


class ScriptLoader {
    //data class ActionResult(val info: String? = null, val warn: String? = null, val err: String? = null)

    private val loadedMods = ArrayList<String>(0)

    fun refresh() {
        loadedMods.clear()
        val files = File(ProgramData.WORKING_DIR + File.separatorChar + "TileEngineData" + File.separatorChar + "mods").listFiles().sorted().forEach { modDir ->
            if (modDir.isDirectory) {
                val scriptFile = File(modDir.path + File.separatorChar + "script.lua")
                if (scriptFile.exists()) {
                    runScriptInSandbox(scriptFile)
                    loadedMods += modDir.name
                }
            }
        }
    }

    private fun runScriptInSandbox(scriptFile: File) {
        val sandboxGlobals = Globals()
        sandboxGlobals.load(JseBaseLib())
        sandboxGlobals.load(PackageLib())
        sandboxGlobals.load(Bit32Lib())
        sandboxGlobals.load(TableLib())
        sandboxGlobals.load(StringLib())
        sandboxGlobals.load(JseMathLib())

        sandboxGlobals.load(DebugLib())
        val setHook: LuaValue = sandboxGlobals.get("debug").get("sethook")
        sandboxGlobals.set("debug", LuaValue.NIL)

        val chunk = ProgramData.LUA_GLOBALS.load(scriptFile.reader(), "main", sandboxGlobals)
        val thread = LuaThread(sandboxGlobals, chunk)

        val hookFunc = object: ZeroArgFunction() {
            override fun call(): LuaValue {
                throw Error("${scriptFile.parentFile.name} overran resource limits.")
            }
        }
        val maxInstructions = 500
        setHook.invoke(LuaValue.varargsOf(arrayOf<LuaValue>(thread, hookFunc, LuaValue.EMPTYSTRING, LuaValue.valueOf(maxInstructions))))

        val result = thread.resume(LuaValue.NIL)
        ProgramData.LOGGER.print("${scriptFile.parentFile.name} -> $result")
    }
}