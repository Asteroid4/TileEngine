package asteroid4.game.world

import asteroid4.Registries
import asteroid4.registry.RegistryKey
import kotlinx.serialization.Serializable
import java.awt.Image

@Serializable
open class Tile(val isInvisible: Boolean) {
    fun interact() {}

    fun destroy() {}
}