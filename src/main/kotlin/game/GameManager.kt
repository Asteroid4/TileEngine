package asteroid4.game

import asteroid4.Registries
import asteroid4.game.world.Tile
import asteroid4.game.world.World
import java.awt.Image

class GameManager() {
    var currentWorld : World? = null

    fun tick() {
        if (currentWorld == null) return
    }

    fun getTile(blockPos : IntPosition) : Tile? {
        return Registries.TILE_REGISTRY[currentWorld?.chunks?.get(blockPos / 16)?.get(IntPosition(blockPos.x % 16, blockPos.y % 16))]
    }

    fun getTileImage(blockPos : IntPosition) : Image? {
        return Registries.IMAGE_REGISTRY[currentWorld?.chunks?.get(blockPos / 16)?.get(IntPosition(blockPos.x % 16, blockPos.y % 16))]
    }
}