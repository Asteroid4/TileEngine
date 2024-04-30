package asteroid4.tileengine

object Logger {
    fun print(str : String) {
        println("[TileEngine/${Thread.currentThread().name}]$str")
    }

    fun printErr(str : String) {
        System.err.println("[TileEngine/${Thread.currentThread().name}]$str")
    }

    fun newLine() {
        println()
    }
}