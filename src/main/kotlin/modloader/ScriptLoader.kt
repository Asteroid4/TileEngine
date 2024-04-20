package asteroid4.modloader

import asteroid4.ProgramData
import java.io.File

class ScriptLoader {
    data class ActionResult(val warn: String? = null, val err: String? = null)

    private val loadedMods = ArrayList<String>(0)

    fun refresh() {
        loadedMods.clear()
        File(ProgramData.WORKING_DIR + File.separatorChar + "TileEngineData" + File.separatorChar + "mods").listFiles().forEach {
            if (it.extension == "tes" && it.isFile) {
                val lines = it.readLines()
                for (lineNumber in 1..<lines.size) {
                    val line = lines[lineNumber]
                    val output = parseAndRun(line, lineNumber)
                    if (output.warn != null) {
                        ProgramData.LOGGER.printErr("Warning in script ${it.name} on line $lineNumber: ${output.warn}")
                    }
                    if (output.err != null) {
                        ProgramData.LOGGER.printErr("Error in script ${it.name} on line $lineNumber: ${output.err}")
                    }
                }
                loadedMods += it.name
            }
        }
    }

    fun parseAndRun(line: String, lineNumber: Int): ActionResult {
        val functionParts = line.split(' ')
        return when (functionParts[0]) {
            "if" -> {
                return ActionResult()
            }
            else -> ActionResult()
        }
    }
}