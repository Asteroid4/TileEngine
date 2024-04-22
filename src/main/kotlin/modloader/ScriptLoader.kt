package asteroid4.modloader

import asteroid4.ProgramData
import org.luaj.vm2.Globals
import org.luaj.vm2.LuaThread
import org.luaj.vm2.LuaValue
import org.luaj.vm2.lib.*
import org.luaj.vm2.lib.jse.JseBaseLib
import org.luaj.vm2.lib.jse.JseMathLib
import java.io.File
import javax.script.Invocable
import javax.script.ScriptEngine
import javax.script.ScriptEngineManager


class ScriptLoader() {
    private val loadedMods = ArrayList<String>(0)
    private val api = TileEngineApi()

    private val engineManager = ScriptEngineManager()
    private val engine: ScriptEngine = engineManager.getEngineByName("nashorn")

    fun refresh() {
        loadedMods.clear()
        val files = File(ProgramData.WORKING_DIR + File.separatorChar + "TileEngineData" + File.separatorChar + "mods").listFiles().sorted().forEach { modDir ->
            if (modDir.isDirectory) {
                val scriptFile = File(modDir.path + File.separatorChar + "script.js")
                if (scriptFile.exists()) {
                    engine.eval(scriptFile.reader())
                    val inv = engine as Invocable
                    inv.invokeFunction("init", api)
                }
            }
        }
    }
}