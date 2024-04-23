package asteroid4.tileengine.modloader

import asteroid4.tileengine.ProgramData

open class TileEngineApi()

object TileEngineApiV1: TileEngineApi() {
    fun tile() {
        ProgramData.LOGGER.print("api time")}
}