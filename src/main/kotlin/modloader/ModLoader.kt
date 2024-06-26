package asteroid4.tileengine.modloader

import asteroid4.tileengine.ProgramData
import java.io.File
import javax.script.*


object ModLoader {
    private val loadedMods = HashMap<String, Pair<ModListener, TileEngineApi?>>()

    fun init() {
        loadedMods.clear()
        File(ProgramData.WORKING_DIR + File.separatorChar + "TileEngineData" + File.separatorChar + "mods").listFiles()
            .sorted().forEach { modFile ->
            if (modFile.isFile && modFile.extension == "jar") {
                (ClassLoader.getSystemClassLoader() as DynamicClassLoader).add(modFile.toURI().toURL())
                val mainClass = Class.forName("ModKt", true, ClassLoader.getSystemClassLoader())
                val instance = mainClass.getDeclaredConstructor().newInstance() as ModListener
                val api = getApi(modFile.nameWithoutExtension, instance.getPreferredApiVersion())
                instance.loadMod(api)
                loadedMods[modFile.nameWithoutExtension] = Pair(instance, api)
            }
        }
    }

    private fun getApi(modName: String, version: Int) = when (version) {
        1 -> TileEngineApiV1(modName)
        else -> null
    }
}