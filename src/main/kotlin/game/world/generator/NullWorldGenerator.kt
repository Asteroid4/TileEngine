package asteroid4.game.world.generator

import asteroid4.game.FloatPosition
import asteroid4.game.IntPosition
import asteroid4.game.world.chunk.HashMapChunk
import asteroid4.registry.RegistryKey

class NullWorldGenerator : WorldGenerator {
    override fun generateChunk(worldSeed: Int, chunkPos: IntPosition): HashMapChunk {
        if (chunkPos.y >= 0) return HashMapChunk(RegistryKey("required", "tile", "air"))
        return HashMapChunk(RegistryKey("required", "tile", "unraveling_fabric"))
    }

    override fun getPlayerStartLocation(worldSeed: Int): FloatPosition {
        return FloatPosition(0f, 0f)
    }
}