package asteroid4.tileengine.modloader

import asteroid4.tileengine.Logger
import asteroid4.tileengine.ProgramData
import asteroid4.tileengine.Registries
import asteroid4.tileengine.game.world.Tile
import asteroid4.tileengine.registry.RegistryKey

open class TileEngineApi(val namespace: String)

class TileEngineApiV1(private val modName: String): TileEngineApi(modName) {
    fun newTile(invisible: Boolean): Tile {
        Logger.print("api time")
        return Tile(invisible)
    }

    fun registerTile(tileName: String, tile: Tile) =
        Registries.TILE_REGISTRY.register(RegistryKey(modName, "tile", tileName), tile)
}