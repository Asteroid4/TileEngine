package asteroid4.tileengine.modloader

abstract class ModListener {
    abstract fun getPreferredApiVersion(): Int

    abstract fun loadMod(api: TileEngineApi?)
}