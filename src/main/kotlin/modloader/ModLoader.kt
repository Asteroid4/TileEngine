package asteroid4.modloader

import asteroid4.ProgramData
import asteroid4.Registries
import asteroid4.game.world.Tile
import asteroid4.registry.Registry
import asteroid4.registry.RegistryKey
import java.io.File
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import java.awt.image.BufferedImage
import javax.imageio.ImageIO

class ModLoader {
    private val currentVersion = 1
    private var loadedMods = ArrayList<ModInfo>()

    fun refreshLoadedMods() {
        loadedMods.clear()
        File(ProgramData.WORKING_DIR + File.separatorChar + "TileEngineData" + File.separatorChar + "mods").listFiles().forEach {
            if (it.isDirectory) {
                val modInfoFile = File(it.path + File.separatorChar + "mod.json")
                if (modInfoFile.exists()) {
                    val modInfo = Json.decodeFromString<ModInfo>(modInfoFile.readText())
                    when {
                        modInfo.modLoaderVersion <= 0 -> ProgramData.LOGGER.printErr("Mod ${modInfo.name}-${modInfo.version} is for invalid version (${modInfo.modLoaderVersion})!")
                        modInfo.modLoaderVersion > currentVersion -> ProgramData.LOGGER.printErr("Mod ${modInfo.name}-${modInfo.version} is for newer version (${modInfo.modLoaderVersion}), rather than current (${currentVersion})!")
                        else -> {
                            val modInfoDir = File(it.path + File.separatorChar + "data")
                            loadFolderToRegistry<Tile>(modInfo, modInfoDir, Registries.TILE_REGISTRY, "tile", "json")
                            loadFolderToRegistry<BufferedImage>(modInfo, modInfoDir, Registries.IMAGE_REGISTRY, "tile", "png")
                            loadedMods += modInfo
                        }
                    }
                }
            }
        }
    }

    private inline fun <reified T> loadFolderToRegistry(modInfo : ModInfo, modInfoDir : File, registry : Registry<T>, typeName : String, extension : String) {
        val dir = File(modInfoDir.path + File.separatorChar + typeName)
        if (dir.exists()) {
            dir.listFiles().forEach {
                if (it.isFile) {
                    if (it.extension == extension) {
                        loadResource<T>(it)?.let { it1 ->
                            registry.register(
                                RegistryKey(modInfo.name, typeName, it.nameWithoutExtension),
                                it1
                            )
                        }
                    }
                }
            }
        }
    }

    private inline fun <reified T> loadResource(file : File) : T? {
        return when (file.extension) {
            "json" -> Json.decodeFromStream<T>(file.inputStream())
            "png" -> ImageIO.read(file) as T
            else -> null
        }
    }
}