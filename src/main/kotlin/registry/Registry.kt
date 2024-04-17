package asteroid4.registry

class Registry<T>() {
    private val contents = HashMap<RegistryKey, T>()

    constructor(key : RegistryKey, value : T) : this() {
        register(key, value)
    }

    fun register(key: RegistryKey, value: T) {
        contents[key] = value
    }
}