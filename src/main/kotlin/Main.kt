package asteroid4

import asteroid4.game.GameManager
import asteroid4.screen.ScreenManager
import kotlin.time.DurationUnit
import kotlin.time.measureTime

fun main() {
    var screen = ScreenManager(ProgramData.STARTING_WIDTH, ProgramData.STARTING_HEIGHT)
    var game = GameManager()
    while (true) {
        val tickDelta = measureTime {
            if (screen.isInUnpausedGame()) game.tick()
        }.toDouble(DurationUnit.MILLISECONDS)
        val maxTickDelta = 1000 / ProgramData.MAX_TPS
        if (tickDelta < 1000 / maxTickDelta) {
            Thread.sleep((maxTickDelta - tickDelta).toLong())
        }
    }
}

object ProgramData {
    const val PROGRAM_NAME = "Tile Engine"
    const val MAX_TPS = 20
    const val STARTING_WIDTH = 768
    const val STARTING_HEIGHT = 512
}