package asteroid4.tileengine.game.world.chunk

import asteroid4.tileengine.game.vector.IntVector
import asteroid4.tileengine.registry.RegistryKey

class HashMapChunk(private var tiles: HashMap<IntVector, RegistryKey>?) : Chunk {
    private var default: RegistryKey? = null

    constructor(newDefault: RegistryKey) : this(null) {
        default = newDefault
    }

    override operator fun get(pos: IntVector): RegistryKey? {
        return when (tiles) {
            null -> default
            else -> tiles!![pos]
        }
    }

    override operator fun set(pos: IntVector, key: RegistryKey) {
        when (tiles) {
            null -> tiles = HashMap()
            else -> tiles!![pos] = key
        }
    }
}