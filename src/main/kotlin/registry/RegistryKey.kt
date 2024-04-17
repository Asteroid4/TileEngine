package asteroid4.registry

data class RegistryKey(val namespace: String, val key : String) {
    override fun toString(): String {
        return "${namespace}:${key}"
    }
}
