package asteroid4.tileengine.game.world

import asteroid4.tileengine.game.math.IntVector
import asteroid4.tileengine.game.world.chunk.Chunk
import asteroid4.tileengine.game.world.vessel.Vessel
import asteroid4.tileengine.game.world.vessel.controller.PlayerController
import asteroid4.tileengine.game.world.generator.WorldGenerator

class World(private val seed: Int, private val generator : WorldGenerator) {
    val chunks : HashMap<IntVector, Chunk> = HashMap()
    var player = Vessel(generator.getPlayerStartLocation(seed), PlayerController())

    fun generateChunk(chunkPos : IntVector): Chunk {
        val chunk = generator.generateChunk(seed, chunkPos)
        chunks[chunkPos] = chunk
        return chunk
    }
}