package asteroid4.tileengine.game.world.chunk

import asteroid4.tileengine.game.IntPosition
import asteroid4.tileengine.registry.RegistryKey

interface Chunk {
    operator fun get(pos: IntPosition): RegistryKey?

    operator fun set(pos: IntPosition, key: RegistryKey)
}