package asteroid4.tileengine.game

data class IntPosition(val x: Int, val y: Int) {
    operator fun plus(pos2: IntPosition): IntPosition {
        return IntPosition(this.x + pos2.x, this.y + pos2.y)
    }

    operator fun plus(pos2: FloatPosition): FloatPosition {
        return FloatPosition(this.x + pos2.x, this.y + pos2.y)
    }

    operator fun minus(pos2: IntPosition): IntPosition {
        return IntPosition(this.x - pos2.x, this.y - pos2.y)
    }

    operator fun minus(pos2: FloatPosition): FloatPosition {
        return FloatPosition(this.x - pos2.x, this.y - pos2.y)
    }

    operator fun times(scalar: Int): IntPosition {
        return IntPosition(this.x * scalar, this.y * scalar)
    }

    operator fun times(scalar: Float): FloatPosition {
        return FloatPosition(this.x * scalar, this.y * scalar)
    }

    operator fun div(scalar: Int): IntPosition {
        return IntPosition(this.x / scalar, this.y / scalar)
    }

    operator fun div(scalar: Float): FloatPosition {
        return FloatPosition(this.x / scalar, this.y / scalar)
    }

    fun dot(pos2: IntPosition): Int {
        return this.x * pos2.x + this.y * pos2.y
    }

    fun magnitude(): Float {
        return kotlin.math.sqrt((this.x * this.x + this.y * this.y).toDouble()).toFloat()
    }

    fun normalize(): FloatPosition {
        return this / this.magnitude()
    }

    fun untruncate(): FloatPosition {
        return FloatPosition(this.x.toFloat(), this.y.toFloat())
    }
}