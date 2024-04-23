package asteroid4.tileengine.game.world.entity

import asteroid4.tileengine.ProgramData
import asteroid4.tileengine.game.FloatPosition

abstract class Entity(val position: FloatPosition) {
    var health = 0f
        set(value) {
            if (value > ProgramData.MAX_HEALTH) return
            field = value
        }

    var isDead : Boolean
        get() = health <= 0f
        private set(_) {}
}