package asteroid4.tileengine.input

import java.awt.event.KeyEvent
import java.awt.event.KeyListener

object InputManager : KeyListener {
    private val inputListeners = HashMap<Char, ArrayList<InputListener>>()

    override fun keyPressed(e: KeyEvent?) {}

    override fun keyTyped(e: KeyEvent?) {
        if (e != null) {
            if (inputListeners[e.keyChar] == null) {
                inputListeners[e.keyChar] = ArrayList()
            }
            inputListeners[e.keyChar]!!.forEach {
                it.keyPressed()
            }
        }
    }

    override fun keyReleased(e: KeyEvent?) {}

    fun register(char: Char, listener: InputListener) {
        if (inputListeners[char] == null) {
            inputListeners[char] = ArrayList()
        }
        inputListeners[char]!! += listener
    }
}