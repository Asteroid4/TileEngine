package asteroid4.tileengine.game

import asteroid4.tileengine.ProgramData
import asteroid4.tileengine.Registries
import asteroid4.tileengine.game.math.FloatVector
import asteroid4.tileengine.game.math.IntVector
import asteroid4.tileengine.game.world.Tile
import asteroid4.tileengine.game.world.World
import java.awt.Image

class GameManager() {
    var currentWorld: World? = null
    private var velocity = FloatVector(0f, 0f)

    init {
        ProgramData.INPUT_MANAGER.register('w') {
            velocity += FloatVector(0f, 0.01f)
        }
        ProgramData.INPUT_MANAGER.register('a') {
            velocity += FloatVector(-0.01f, 0f)
        }
        ProgramData.INPUT_MANAGER.register('s') {
            velocity += FloatVector(0f, -0.01f)
        }
        ProgramData.INPUT_MANAGER.register('d') {
            velocity += FloatVector(0.01f, 0f)
        }
    }

    fun tick() {
        if (currentWorld == null) return
        ProgramData.LOGGER.print(velocity.toString())
        ProgramData.LOGGER.print(currentWorld!!.player.position.toString())
        ProgramData.LOGGER.newLine()
        currentWorld!!.player.position += velocity
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