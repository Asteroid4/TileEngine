package asteroid4.tileengine

import asteroid4.tileengine.game.GameManager
import asteroid4.tileengine.game.world.Tile
import asteroid4.tileengine.game.world.generator.NullWorldGenerator
import asteroid4.tileengine.game.world.generator.WorldGenerator
import asteroid4.tileengine.input.InputManager
import asteroid4.tileengine.modloader.ModLoader
import asteroid4.tileengine.registry.Registry
import asteroid4.tileengine.registry.RegistryKey
import asteroid4.tileengine.screen.ScreenManager
import java.awt.image.BufferedImage
import java.io.IOException
import javax.imageio.ImageIO
import kotlin.random.Random

object ProgramData {
    const val PROGRAM_NAME = "Tile Engine"
    val WORKING_DIR: String = System.getProperty("user.dir")
    const val MAX_FPS = 60
    const val MAX_TPS = 20
    const val STARTING_WIDTH = 768
    const val STARTING_HEIGHT = 512
    const val TILE_SIZE = 64
    const val MAX_HEALTH = 100f
    const val PLAYER_SPEED = 0.1f
    val RNG = Random.Default
}

object Registries {
    val IMAGE_REGISTRY: Registry<BufferedImage>
    val TILE_REGISTRY = Registry(Tile(false))
    val WORLD_GENERATOR_REGISTRY = Registry<WorldGenerator>(NullWorldGenerator())

    init {
        var img: BufferedImage? = null;
        try {
            img = ImageIO.read(ClassLoader.getSystemResource("dev_tile.png"))
        } catch (_: IOException) {
        }
        IMAGE_REGISTRY = Registry(img!!)
        TILE_REGISTRY.register(RegistryKey("required", "tile", "air"), Tile(true))
    }
}

object KeyBinds {
    const val PLAYER_JUMP = 'w'
    const val PLAYER_LEFT = 'a'
    const val PLAYER_RIGHT = 'd'
    const val PLAYER_CROUCH = 's'
}