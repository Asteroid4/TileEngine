package asteroid4.game.world.entity

import asteroid4.game.FloatPosition

class Player(val position: FloatPosition) {
    var health = 0f
        set(value) {
            if (field <= 0f) return
            if (value >= 100f) return
            field = value
        }

    var isDead : Boolean
        get() = health > 0f
        private set(_) {}
}