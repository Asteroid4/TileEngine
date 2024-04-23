package asteroid4.tileengine.modloader

import asteroid4.tileengine.ProgramData
import java.io.File
import javax.script.*


class ModLoader() {
    private val loadedMods = HashMap<String, ModListener>()

    fun refresh() {
        loadedMods.clear()
        println(File(ProgramData.WORKING_DIR + File.separatorChar + "TileEngineData" + File.separatorChar + "mods").listFiles())
        File(ProgramData.WORKING_DIR + File.separatorChar + "TileEngineData" + File.separatorChar + "mods").listFiles().sorted().forEach { modFile ->
            if (modFile.isFile && modFile.extension == "jar") {
                (ClassLoader.getSystemClassLoader() as DynamicClassLoader).add(modFile.toURI().toURL())
                val mainClass = Class.forName(modFile.nameWithoutExtension, true, ClassLoader.getSystemClassLoader())
                val instance = mainClass.getDeclaredConstructor().newInstance() as ModListener
                instance.loadMod(getApi(instance.getPreferredApiVersion()))
                loadedMods[modFile.nameWithoutExtension] = instance
            }
        }
    }

    private fun getApi(version: Int): TileEngineApi? {
        return when (version) {
            1 -> TileEngineApiV1
            else -> null
        }
    }
}