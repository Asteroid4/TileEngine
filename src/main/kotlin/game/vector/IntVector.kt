package asteroid4.tileengine.game.vector

data class IntVector(val x: Int, val y: Int) {
    operator fun plus(pos2: IntVector): IntVector {
        return IntVector(this.x + pos2.x, this.y + pos2.y)
    }

    operator fun plus(pos2: FloatVector): FloatVector {
        return FloatVector(this.x + pos2.x, this.y + pos2.y)
    }

    operator fun minus(pos2: IntVector): IntVector {
        return IntVector(this.x - pos2.x, this.y - pos2.y)
    }

    operator fun minus(pos2: FloatVector): FloatVector {
        return FloatVector(this.x - pos2.x, this.y - pos2.y)
    }

    operator fun times(scalar: Int): IntVector {
        return IntVector(this.x * scalar, this.y * scalar)
    }

    operator fun times(scalar: Float): FloatVector {
        return FloatVector(this.x * scalar, this.y * scalar)
    }

    operator fun div(scalar: Int): IntVector {
        return IntVector(this.x / scalar, this.y / scalar)
    }

    operator fun div(scalar: Float): FloatVector {
        return FloatVector(this.x / scalar, this.y / scalar)
    }

    fun dot(pos2: IntVector): Int {
        return this.x * pos2.x + this.y * pos2.y
    }

    fun magnitude(): Float {
        return kotlin.math.sqrt((this.x * this.x + this.y * this.y).toDouble()).toFloat()
    }

    fun normalize(): FloatVector {
        return this / this.magnitude()
    }

    fun untruncate(): FloatVector {
        return FloatVector(this.x.toFloat(), this.y.toFloat())
    }
}