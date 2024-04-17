package asteroid4.modloader

import asteroid4.ProgramData
import asteroid4.Registries
import asteroid4.game.world.Tile
import asteroid4.registry.Registry
import asteroid4.registry.RegistryKey
import java.io.File
import kotlinx.serialization.json.Json

class ModLoader {
    private val currentVersion : Short = 0
    private var loadedMods = ArrayList<ModInfo>()

    fun refreshLoadedMods() {
        loadedMods.clear()
        File(ProgramData.WORKING_DIR + File.separatorChar + "TileEngineData" + File.separatorChar + "mods").listFiles().forEach {
            if (it.isDirectory) {
                val modInfoFile = File(it.path + File.separatorChar + "mod.tileengine")
                if (modInfoFile.exists()) {
                    val modInfo = Json.decodeFromString<ModInfo>(modInfoFile.readText())
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
        loadFolderToRegistry<Tile>(modInfo, modInfoDir, Registries.TILE_REGISTRY, "tile")
        loadedMods += modInfo
    }

    inline fun <reified T> loadFolderToRegistry(modInfo : ModInfo, modInfoDir : File, registry : Registry<T>, typeName : String) {
        val dir = File(modInfoDir.path + File.separatorChar + typeName)
        if (dir.exists()) {
            dir.listFiles().forEach {
                if (!it.isDirectory) {
                    registry.register(
                        RegistryKey(modInfo.name, "$typeName/${it.name}"),
                        Json.decodeFromString<T>(it.readText())
                    )
                }
            }
        }
    }
}