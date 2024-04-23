package asteroid4.tileengine.game.world.generator

import asteroid4.tileengine.game.vector.FloatVector
import asteroid4.tileengine.game.vector.IntVector
import asteroid4.tileengine.game.world.chunk.HashMapChunk
import asteroid4.tileengine.registry.RegistryKey

class NullWorldGenerator : WorldGenerator {
    override fun generateChunk(worldSeed: Int, chunkPos: IntVector): HashMapChunk {
        if (chunkPos.y >= 0) return HashMapChunk(RegistryKey("required", "tile", "air"))
        return HashMapChunk(RegistryKey("required", "tile", "unraveling_fabric"))
    }

    override fun getPlayerStartLocation(worldSeed: Int): FloatVector {
        return FloatVector(0f, 0f)
    }
}