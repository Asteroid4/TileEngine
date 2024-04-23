package asteroid4.tileengine

class Logger {
    fun print(str : String) {
        println("[TileEngine/${Thread.currentThread().name}]$str")
    }

    fun printErr(str : String) {
        System.err.println("[TileEngine/${Thread.currentThread().name}]$str")
    }
}