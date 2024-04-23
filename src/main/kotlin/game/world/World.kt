package asteroid4.tileengine.game.world

import asteroid4.tileengine.game.IntPosition
import asteroid4.tileengine.game.world.chunk.Chunk
import asteroid4.tileengine.game.world.entity.ControllableEntity
import asteroid4.tileengine.game.world.generator.WorldGenerator

class World(private val seed: Int, private val generator : WorldGenerator) {
    val chunks : HashMap<IntPosition, Chunk> = HashMap()
    var player = ControllableEntity(generator.getPlayerStartLocation(seed))

    fun generateChunk(chunkPos : IntPosition): Chunk {
        val chunk = generator.generateChunk(seed, chunkPos)
        chunks[chunkPos] = chunk
        return chunk
    }
}