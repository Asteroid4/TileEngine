package asteroid4.game.world

data class Chunk(val tiles : Array<Array<Tile>>) {
    init {
        require(tiles.size == 16 && tiles[0].size == 16)
    }

    operator fun get(x : Int, y : Int) = tiles[x][y]

    operator fun set(x : Int, y : Int, t : Tile) { tiles[x][y] = t }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Chunk

        return tiles.contentDeepEquals(other.tiles)
    }

    override fun hashCode(): Int {
        return tiles.contentDeepHashCode()
    }
}