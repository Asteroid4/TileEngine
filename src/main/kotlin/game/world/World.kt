package asteroid4.tileengine.game.world

import asteroid4.tileengine.ProgramData
import asteroid4.tileengine.game.math.FloatVector
import asteroid4.tileengine.game.math.IntVector
import asteroid4.tileengine.game.world.chunk.Chunk
import asteroid4.tileengine.game.world.entity.Vessel
import asteroid4.tileengine.game.world.entity.controller.PlayerController
import asteroid4.tileengine.game.world.generator.WorldGenerator
import asteroid4.tileengine.screen.ScreenManager
import kotlin.math.max
import kotlin.math.min

class World(private val seed: Int, private val generator: WorldGenerator) {
    val chunks = HashMap<IntVector, Chunk>()
    private val toBeGenerated = ArrayList<IntVector>()
    var player = Vessel(generator.getPlayerStartLocation(seed), PlayerController())

    fun tick() {
        toBeGenerated.forEach { chunkPos ->
            if (chunks[chunkPos] == null) generateChunk(chunkPos)
        }
        toBeGenerated.clear()
        player.tick()
    }

    private fun generateChunk(chunkPos: IntVector): Chunk {
        val chunk = generator.generateChunk(seed, chunkPos)
        chunks[chunkPos] = chunk
        return chunk
    }

    fun getLoadedChunks(): Array<IntVector> {
        val loadedChunks = ArrayList<Pair<IntVector, Chunk?>>()
        val chunkBoundaries = ScreenManager.screen.getScreenCornerGlobalPositions().map { corner -> getChunkPos(corner) }
        for (chunkX in min(chunkBoundaries[0].x, chunkBoundaries[1].x) - 1..max(chunkBoundaries[0].x, chunkBoundaries[1].x)) {
            for (chunkY in min(chunkBoundaries[0].y, chunkBoundaries[1].y) - 1..max(chunkBoundaries[0].y, chunkBoundaries[1].y)) {
                val chunkPos = IntVector(chunkX, chunkY)
                loadedChunks += Pair(chunkPos, chunks[chunkPos])
            }
        }
        val generatedChunks = ArrayList<IntVector>()
        loadedChunks.forEach { (chunkPos, chunk) ->
            if (chunk == null) toBeGenerated += chunkPos
            else generatedChunks += chunkPos
        }
        return generatedChunks.toTypedArray()
    }

    fun getChunkPos(tilePos: FloatVector) = tilePos.truncate() / 16
}