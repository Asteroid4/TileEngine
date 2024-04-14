package asteroid4

import asteroid4.screen.ScreenManager
import kotlin.time.DurationUnit
import kotlin.time.measureTime

fun main() {
    var screen = ScreenManager()
    while (true) {
        val tickDelta = measureTime {
            screen.tick()
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
}