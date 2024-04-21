package asteroid4.game.world.generator

import asteroid4.game.FloatPosition
import asteroid4.game.IntPosition
import asteroid4.game.world.chunk.Chunk

interface WorldGenerator {
    fun generateChunk(worldSeed: Int, chunkPos : IntPosition) : Chunk

    fun getPlayerStartLocation(worldSeed: Int) : FloatPosition
}