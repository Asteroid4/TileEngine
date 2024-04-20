package asteroid4.registry

data class RegistryKey(val namespace : String, val type : String, val key : String) {
    override fun toString() : String {
        return "$namespace:$type/$key"
    }

    fun fromString(str : String) {}
}