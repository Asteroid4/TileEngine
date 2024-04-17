package asteroid4.modloader

import asteroid4.ProgramData
import java.io.File
import kotlinx.serialization.json.Json
import java.nio.charset.Charset

class ModLoader {
    private val currentVersion : Short = 0
    private var loadedMods = ArrayList<ModInfo>()

    fun refreshLoadedMods() {
        loadedMods.clear()
        File(ProgramData.WORKING_DIR + File.separatorChar + "TileEngineData" + File.separatorChar + "mods").listFiles().forEach {
            if (it.isDirectory) {
                val modInfoFile = File(it.path + File.separatorChar + "mod.tileengine")
                if (modInfoFile.exists()) {
                    val modInfo = Json.decodeFromString<ModInfo>(modInfoFile.readText(Charset.defaultCharset()))
                    when {
                        modInfo.modLoaderVersion <= 0 -> ProgramData.LOGGER.printErr("Mod ${modInfo.name}-${modInfo.version} is for invalid version (${modInfo.modLoaderVersion})!")
                        modInfo.modLoaderVersion > currentVersion -> ProgramData.LOGGER.printErr("Mod ${modInfo.name}-${modInfo.version} is for newer version (${modInfo.modLoaderVersion}), rather than current (${currentVersion})!")
                        else -> loadMod(modInfo, File(it.path + File.separatorChar + "data"))
                    }
                }
            }
        }
    }

    private fun loadMod(modInfo : ModInfo, modInfoDir : File) {
        loadedMods += modInfo
    }
}