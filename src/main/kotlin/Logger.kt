package asteroid4.tileengine

object Logger {
    fun print(str : String) {
        println("[TileEngine/${Thread.currentThread().name}]$str")
    }

    fun printErr(str : String) {
        System.err.println("[TileEngine/${Thread.currentThread().name}]$str")
    }

    fun print(namespace: String, str : String) {
        println("[TileEngine/$namespace/${Thread.currentThread().name}]$str")
    }

    fun printErr(namespace: String, str : String) {
        System.err.println("[TileEngine/$namespace/${Thread.currentThread().name}]$str")
    }

    fun newLine() {
        println()
    }
}