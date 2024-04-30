package asteroid4.tileengine.game.world.vessel.controller

import asteroid4.tileengine.game.math.FloatVector
import asteroid4.tileengine.input.InputManager

class PlayerController(): Controller() {
    private var velocity = FloatVector(0f, 0f)

    init {
        InputManager.register('w') {
            velocity = FloatVector(0f, -0.1f)
        }
        InputManager.register('a') {
            velocity = FloatVector(-0.1f, 0f)
        }
        InputManager.register('s') {
            velocity = FloatVector(0f, 0.1f)
        }
        InputManager.register('d') {
            velocity = FloatVector(0.1f, 0f)
        }
    }

    override fun update(): FloatVector {
        // Please change this at some point, needs a fix!
        val newVelocity = velocity
        velocity = FloatVector(0f, 0f)
        return newVelocity
    }
}