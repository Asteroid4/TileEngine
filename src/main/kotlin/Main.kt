package asteroid4.tileengine

import java.io.File
import kotlin.time.DurationUnit
import kotlin.time.measureTime

fun main() {
    verifyGameDataFolderExists()
    while (true) {
        if (ProgramData.SCREEN_MANAGER.shouldBeInUnpausedGame()) {
            if (ProgramData.GAME_MANAGER.currentWorld == null) ProgramData.LOGGER.printErr("World not initialized during gameplay!")
            val tickDelta = measureTime {
                ProgramData.GAME_MANAGER.tick()
            }.toDouble(DurationUnit.MILLISECONDS)
            val maxTickDelta = 1000 / ProgramData.MAX_TPS
            if (tickDelta < 1000 / maxTickDelta) {
                Thread.sleep((maxTickDelta - tickDelta).toLong())
            }
        }
    }
}

fun verifyGameDataFolderExists() {
    val basePath = ProgramData.WORKING_DIR + File.separatorChar + "TileEngineData" + File.separatorChar
    verifyFolderExists(basePath + "mods")
}

fun verifyFolderExists(path : String) {
    val folder = File(path)
    if (!folder.exists()) folder.mkdirs()
}