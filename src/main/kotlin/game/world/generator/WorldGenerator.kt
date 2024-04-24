package asteroid4.tileengine.game.world.generator

import asteroid4.tileengine.game.math.FloatVector
import asteroid4.tileengine.game.math.IntVector
import asteroid4.tileengine.game.world.chunk.Chunk

interface WorldGenerator {
    fun generateChunk(worldSeed: Int, chunkPos : IntVector) : Chunk

    fun getPlayerStartLocation(worldSeed: Int) : FloatVector
}