package asteroid4.tileengine.game.world.generator

import asteroid4.tileengine.game.FloatPosition
import asteroid4.tileengine.game.IntPosition
import asteroid4.tileengine.game.world.chunk.Chunk

interface WorldGenerator {
    fun generateChunk(worldSeed: Int, chunkPos : IntPosition) : Chunk

    fun getPlayerStartLocation(worldSeed: Int) : FloatPosition
}