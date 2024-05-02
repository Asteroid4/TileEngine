package asteroid4.tileengine

import asteroid4.tileengine.game.GameManager
import asteroid4.tileengine.modloader.ModLoader
import asteroid4.tileengine.screen.ScreenManager
import java.io.File
import kotlin.time.DurationUnit
import kotlin.time.measureTime

fun main() {
    ModLoader.init()
    val basePath = ProgramData.WORKING_DIR + File.separatorChar + "TileEngineData" + File.separatorChar
    verifyFolder(basePath + "mods")
    val renderThread = Thread({
        while (!Thread.currentThread().isInterrupted) {
            try {
                val frameDelta = measureTime {
                    ScreenManager.screen.repaint()
                }.toDouble(DurationUnit.MILLISECONDS)
                val maxFrameDelta = 1000 / ProgramData.MAX_FPS
                if (frameDelta < 1000 / maxFrameDelta) {
                    Thread.sleep((maxFrameDelta - frameDelta).toLong())
                }
            } catch (_: InterruptedException) {
                Thread.currentThread().interrupt()
            }
        }
    }, "RenderLogic")
    val gameThread = Thread({
        while (!Thread.currentThread().isInterrupted) {
            try {
                if (ScreenManager.shouldBeInUnpausedGame()) {
                    if (GameManager.currentWorld == null) Logger.printErr("World not initialized during gameplay!")
                    val tickDelta = measureTime {
                        GameManager.tick()
                    }.toDouble(DurationUnit.MILLISECONDS)
                    val maxTickDelta = 1000 / ProgramData.MAX_TPS
                    if (tickDelta < 1000 / maxTickDelta) {
                        Thread.sleep((maxTickDelta - tickDelta).toLong())
                    }
                }
            } catch (_: InterruptedException) {
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