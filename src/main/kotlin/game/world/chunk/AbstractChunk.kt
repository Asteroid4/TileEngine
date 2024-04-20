package asteroid4.game.world.chunk

import asteroid4.game.IntPosition
import asteroid4.registry.RegistryKey

interface AbstractChunk {
    operator fun get(pos: IntPosition): RegistryKey?

    operator fun set(pos: IntPosition, key: RegistryKey)
}