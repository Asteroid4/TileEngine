package asteroid4.modloader

import asteroid4.Registries
import asteroid4.game.FloatPosition
import asteroid4.game.IntPosition
import asteroid4.game.world.Tile
import asteroid4.game.world.chunk.HashMapChunk
import asteroid4.game.world.generator.WorldGenerator
import asteroid4.registry.RegistryKey
import org.luaj.vm2.LuaValue
import org.luaj.vm2.Varargs
import org.luaj.vm2.lib.TwoArgFunction
import org.luaj.vm2.lib.VarArgFunction
import java.awt.image.BufferedImage
import java.io.IOException
import javax.imageio.ImageIO

class TileEngineLibrary() : TwoArgFunction() {
    override fun call(modName: LuaValue?, env: LuaValue?): LuaValue {
        val library = tableOf()
        library.set("tile", TileFunction())
        library.set("worldgenerator", WorldGeneratorFunction())
        env?.set("tile-engine", library)
        return library
    }

    class TileFunction() : VarArgFunction() {
        override fun invoke(args: Varargs): LuaValue {
            val tileName = args.checkjstring(1)
            val isInvisible = args.checkboolean(2)
            val key = RegistryKey("debug", "tile", tileName)
            if (Registries.TILE_REGISTRY.getOptional(key) == null) {
                Registries.TILE_REGISTRY.register(key, Tile(isInvisible))
                if (!isInvisible) {
                    var img: BufferedImage? = null;
                    try {
                        img = ImageIO.read(ClassLoader.getSystemResource("$tileName.png"))
                    } catch (_: IOException) {}
                    if (img != null) {
                        Registries.IMAGE_REGISTRY.register(key, img)
                    }
                }
                return LuaValue.TRUE
            }
            return LuaValue.FALSE
        }
    }

    class WorldGeneratorFunction() : VarArgFunction() {
        override fun invoke(args: Varargs): LuaValue {
            val generatorName = args.checkjstring(1)
            val generateChunk = args.checkfunction(2)
            val getPlayerStartPosition = args.checkfunction(3)
            val key = RegistryKey("debug", "worldgen", generatorName)
            if (Registries.WORLD_GENERATOR_REGISTRY.getOptional(key) == null) {
                Registries.WORLD_GENERATOR_REGISTRY.register(key, object: WorldGenerator {
                    override fun generateChunk(worldSeed: Int, chunkPos: IntPosition): HashMapChunk {
                        //return generateChunk.invoke(LuaValue.varargsOf(LuaValue.valueOf(worldSeed), LuaValue.valueOf(chunkPos.x), LuaValue.valueOf(chunkPos.y)))
                        TODO("Figure out some way to do this")
                    }

                    override fun getPlayerStartLocation(worldSeed: Int): FloatPosition {
                        val result = getPlayerStartPosition.invoke(LuaValue.valueOf(worldSeed))
                        return FloatPosition(result.checkdouble(1).toFloat(), result.checkdouble(2).toFloat())
                    }
                })
                return LuaValue.TRUE
            }
            return LuaValue.FALSE
        }
    }
}