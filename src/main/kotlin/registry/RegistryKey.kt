package asteroid4.tileengine.registry

data class RegistryKey(val namespace : String, val type : String, val key : String) {
    override fun toString() : String {
        return "$namespace:$type/$key"
    }
}