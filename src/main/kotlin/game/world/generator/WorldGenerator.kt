package asteroid4.game.world.generator

import asteroid4.game.FloatPosition
import asteroid4.game.IntPosition
import asteroid4.game.world.chunk.HashMapChunk

interface WorldGenerator {
    fun generateChunk(worldSeed: Int, chunkPos : IntPosition) : HashMapChunk

    fun getPlayerStartLocation(worldSeed: Int) : FloatPosition
}