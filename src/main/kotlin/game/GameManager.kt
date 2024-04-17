package asteroid4.game

import asteroid4.game.world.Tile
import asteroid4.game.world.World
import java.awt.Image

class GameManager() {
    var currentWorld : World? = null

    fun tick() {
        if (currentWorld == null) return
    }

    fun getTile(x : Long, y : Long) : Tile? {
        return currentWorld?.chunks?.get(Pair(x / 16, y / 16))?.get((x % 16).toInt(), (y % 16).toInt())
    }

    fun getTileImage(x : Long, y : Long) : Image? {
        getTile(x, y).name
    }
}