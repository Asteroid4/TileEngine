package asteroid4.game.world

import asteroid4.game.IntPosition
import asteroid4.game.world.chunk.AbstractChunk
import asteroid4.game.world.entity.ControllableEntity
import asteroid4.game.world.generator.WorldGenerator

class World(private val seed: Int, private val generator : WorldGenerator) {
    val chunks : HashMap<IntPosition, AbstractChunk> = HashMap()
    var player = ControllableEntity(generator.getPlayerStartLocation(seed))

    fun generateChunk(chunkPos : IntPosition): AbstractChunk {
        val chunk = generator.generateChunk(seed, chunkPos)
        chunks[chunkPos] = chunk
        return chunk
    }
}