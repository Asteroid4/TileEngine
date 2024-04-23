package asteroid4.tileengine.registry

class Registry<T>(private val default: T) {
    private val contents = HashMap<RegistryKey, T>()

    fun register(key: RegistryKey, value: T) {
        contents[key] = value
    }

    operator fun get(key: RegistryKey?): T = contents.getOrDefault(key, default)

    fun getOptional(key: RegistryKey?): T? = contents[key]
}