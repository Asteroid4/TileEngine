package asteroid4.modloader

import kotlinx.serialization.Serializable

@Serializable
data class ModInfo(val modLoaderVersion: Int, val name: String, val version : String)