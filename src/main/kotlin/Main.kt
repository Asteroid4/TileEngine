package asteroid4.tileengine

import java.io.File
import kotlin.time.DurationUnit
import kotlin.time.measureTime

fun main() {
    verifyGameDataFolderExists()
    val renderThread = Thread({
        while (true) {
            val frameDelta = measureTime {
                ProgramData.SCREEN_MANAGER.frame()
            }.toDouble(DurationUnit.MILLISECONDS)
            val maxFrameDelta = 1000 / ProgramData.MAX_FPS
            if (frameDelta < 1000 / maxFrameDelta) {
                Thread.sleep((maxFrameDelta - frameDelta).toLong())
            }
        }
    }, "Render Thread")
    val gameThread = Thread({
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
    }, "Game Thread")
    renderThread.start()
    gameThread.start()
}

fun verifyGameDataFolderExists() {
    val basePath = ProgramData.WORKING_DIR + File.separatorChar + "TileEngineData" + File.separatorChar
    verifyFolderExists(basePath + "mods")
}

fun verifyFolderExists(path : String) {
    val folder = File(path)
    if (!folder.exists()) folder.mkdirs()
}