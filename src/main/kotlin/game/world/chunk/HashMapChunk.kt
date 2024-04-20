package asteroid4.game.world.chunk

import asteroid4.game.IntPosition
import asteroid4.registry.RegistryKey

class HashMapChunk(var tiles: HashMap<IntPosition, RegistryKey>?) : AbstractChunk {
    private var default: RegistryKey? = null

    constructor(newDefault: RegistryKey) : this(null) {
        default = newDefault
    }

    override operator fun get(pos: IntPosition): RegistryKey? {
        return when (tiles) {
            null -> default
            else -> tiles!![pos]
        }
    }

    override operator fun set(pos: IntPosition, key: RegistryKey) {
        when (tiles) {
            null -> tiles = HashMap()
            else -> tiles!![pos] = key
        }
    }
}