package asteroid4.tileengine.game.world.vessel

import asteroid4.tileengine.ProgramData
import asteroid4.tileengine.game.math.FloatVector
import asteroid4.tileengine.game.world.vessel.controller.Controller

open class Vessel(var position: FloatVector, private val controller: Controller) {
    var health = 0f
        set(value) {
            if (value > ProgramData.MAX_HEALTH) return
            field = value
        }

    var isDead : Boolean
        get() = health <= 0f
        private set(_) {}

    fun tick() {
        val controllerOutput = controller.update()
        position += controllerOutput
    }
}