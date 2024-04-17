package asteroid4

import asteroid4.game.GameManager
import asteroid4.modloader.ModLoader
import asteroid4.screen.ScreenManager
import java.io.File
import kotlin.time.DurationUnit
import kotlin.time.measureTime

fun main() {
    verifyGameDataFolderExists()
    while (true) {
        if (ProgramData.SCREEN_MANAGER.isInUnpausedGame()) {
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
    verifyFolderExists(basePath + "worlds")
}

fun verifyFolderExists(path : String) {
    val folder = File(path)
    if (!folder.exists()) folder.mkdirs()
}

object ProgramData {
    const val PROGRAM_NAME = "Tile Engine"
    val WORKING_DIR: String = System.getProperty("user.dir")
    const val MAX_TPS = 20
    private const val STARTING_WIDTH = 768
    private const val STARTING_HEIGHT = 512

    val SCREEN_MANAGER = ScreenManager(STARTING_WIDTH, STARTING_HEIGHT)
    val GAME_MANAGER = GameManager()
    val MOD_LOADER = ModLoader()
}