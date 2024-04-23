package asteroid4.tileengine

import java.io.File
import kotlin.time.DurationUnit
import kotlin.time.measureTime

fun main() {
    val basePath = ProgramData.WORKING_DIR + File.separatorChar + "TileEngineData" + File.separatorChar
    verifyFolder(basePath + "mods")
    val renderThread = Thread({
        while (!Thread.currentThread().isInterrupted) {
            try {
                val frameDelta = measureTime {
                    ProgramData.SCREEN_MANAGER.frame()
                }.toDouble(DurationUnit.MILLISECONDS)
                val maxFrameDelta = 1000 / ProgramData.MAX_FPS
                if (frameDelta < 1000 / maxFrameDelta) {
                    Thread.sleep((maxFrameDelta - frameDelta).toLong())
                }
            } catch (e: InterruptedException) {
                Thread.currentThread().interrupt()
            }
        }
    }, "RenderLogic")
    val gameThread = Thread({
        while (!Thread.currentThread().isInterrupted) {
            try {
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
            } catch (e: InterruptedException) {
                Thread.currentThread().interrupt()
            }
        }
    }, "GameLogic")
    renderThread.start()
    gameThread.start()
}

fun verifyFolder(path: String) {
    val folder = File(path)
    if (!folder.exists()) folder.mkdirs()
}