package asteroid4.modloader

import asteroid4.ProgramData
import java.io.File

class ModLoader {
    var loadedMods = ArrayList<String>()

    fun refreshLoadedMods() {
        File(ProgramData.WORKING_DIR + File.separatorChar + "TileEngineData" + File.separatorChar + "mods").listFiles().forEach {
            if (it.isDirectory) {
                if (File(it.path + File.separatorChar + "moddata.tileengine").exists()) {}
            }
        }
    }
}