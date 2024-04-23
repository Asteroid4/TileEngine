package asteroid4.tileengine.input

import java.awt.event.KeyEvent
import java.awt.event.KeyListener

class InputManager() : KeyListener {
    private val inputListeners = HashMap<Char, ArrayList<InputListener>>()

    override fun keyPressed(e: KeyEvent?) {
        if (e != null) {
            if (e.keyChar != null) {
                if (inputListeners[e.keyChar] == null) {
                    inputListeners[e.keyChar] = ArrayList()
                }
                inputListeners[e.keyChar]!!.forEach {
                    it.keyPressed()
                }
            }
        }
    }

    override fun keyTyped(e: KeyEvent?) {}

    override fun keyReleased(e: KeyEvent?) {}

    fun register(char : Char, listener : InputListener) {
        if (inputListeners[char] == null) {
            inputListeners[char] = ArrayList()
        }
        inputListeners[char]!! += listener
    }
}