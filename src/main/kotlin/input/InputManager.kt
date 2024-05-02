package asteroid4.tileengine.input

import java.awt.event.KeyEvent
import java.awt.event.KeyListener

object InputManager : KeyListener {
    private val inputListeners = HashMap<Char, ArrayList<InputListener>>()
    private val isKeyPressed = HashMap<Char, Boolean>()

    override fun keyPressed(e: KeyEvent?) {
        if (e != null) {
            if (inputListeners[e.keyChar] == null) {
                inputListeners[e.keyChar] = ArrayList()
            }
            inputListeners[e.keyChar]!!.forEach {
                it.keyPressed()
            }
            if (isKeyPressed[e.keyChar] != null) isKeyPressed[e.keyChar] = true
        }
    }

    override fun keyTyped(e: KeyEvent?) {
        if (e != null) {
            if (inputListeners[e.keyChar] == null) {
                inputListeners[e.keyChar] = ArrayList()
            }
            inputListeners[e.keyChar]!!.forEach {
                it.keyTyped()
            }
        }
    }

    override fun keyReleased(e: KeyEvent?) {
        if (e != null) {
            if (inputListeners[e.keyChar] == null) {
                inputListeners[e.keyChar] = ArrayList()
            }
            inputListeners[e.keyChar]!!.forEach {
                it.keyReleased()
            }
            if (isKeyPressed[e.keyChar] != null) isKeyPressed[e.keyChar] = false
        }
    }

    fun isKeyPressed(c: Char) = isKeyPressed.getOrDefault(c, false)

    fun listen(c: Char) {
        if (isKeyPressed[c] == null) isKeyPressed[c] = false
    }

    fun register(c: Char, listener: InputListener) {
        if (inputListeners[c] == null) inputListeners[c] = ArrayList()
        inputListeners[c]!! += listener
    }
}