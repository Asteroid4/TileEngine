package asteroid4.tileengine.game

import asteroid4.tileengine.Logger
import asteroid4.tileengine.Registries
import asteroid4.tileengine.game.math.FloatVector
import asteroid4.tileengine.game.math.IntVector
import asteroid4.tileengine.game.world.Tile
import asteroid4.tileengine.game.world.World
import asteroid4.tileengine.input.InputManager
import java.awt.Image

object GameManager {
    var currentWorld: World? = null

    fun tick() {
        if (currentWorld == null) return
        currentWorld!!.player.tick()
    }

    fun getTile(blockPos: IntVector): Tile? {
        return Registries.TILE_REGISTRY[currentWorld?.chunks?.get(blockPos / 16)
            ?.get(IntVector(blockPos.x % 16, blockPos.y % 16))]
    }

    fun getTileImage(blockPos: IntVector): Image? {
        return Registries.IMAGE_REGISTRY[currentWorld?.chunks?.get(blockPos / 16)
            ?.get(IntVector(blockPos.x % 16, blockPos.y % 16))]
    }
}