package asteroid4.tileengine.game.world.chunk

import asteroid4.tileengine.game.math.IntVector
import asteroid4.tileengine.registry.RegistryKey

interface Chunk {
    operator fun get(pos: IntVector): RegistryKey?

    operator fun set(pos: IntVector, key: RegistryKey)
}