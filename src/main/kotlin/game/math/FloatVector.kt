package asteroid4.tileengine.game.math

data class FloatVector(val x: Float, val y: Float) {
    operator fun plus(pos2: FloatVector): FloatVector {
        return FloatVector(this.x + pos2.x, this.y + pos2.y)
    }

    operator fun minus(pos2: FloatVector): FloatVector {
        return FloatVector(this.x - pos2.x, this.y - pos2.y)
    }

    operator fun minus(pos2: IntVector): FloatVector {
        return FloatVector(this.x - pos2.x, this.y - pos2.y)
    }

    operator fun times(scalar: Float): FloatVector {
        return FloatVector(this.x * scalar, this.y * scalar)
    }

    operator fun times(scalar: Int): FloatVector {
        return FloatVector(this.x * scalar, this.y * scalar)
    }

    operator fun div(scalar: Float): FloatVector {
        return FloatVector(this.x / scalar, this.y / scalar)
    }

    operator fun div(scalar: Int): FloatVector {
        return FloatVector(this.x / scalar, this.y / scalar)
    }

    fun dot(pos2: FloatVector): Float {
        return this.x * pos2.x + this.y * pos2.y
    }

    fun magnitude(): Float {
        return kotlin.math.sqrt(this.x * this.x + this.y * this.y)
    }

    fun normalize(): FloatVector {
        return this / this.magnitude()
    }

    fun truncate(): IntVector {
        return IntVector(this.x.toInt(), this.y.toInt())
    }
}