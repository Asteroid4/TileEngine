package asteroid4.game

data class FloatPosition(val x: Float, val y: Float) {
    operator fun plus(pos2: FloatPosition): FloatPosition {
        return FloatPosition(this.x + pos2.x, this.y + pos2.y)
    }

    operator fun minus(pos2: FloatPosition): FloatPosition {
        return FloatPosition(this.x - pos2.x, this.y - pos2.y)
    }

    operator fun times(scalar: Float): FloatPosition {
        return FloatPosition(this.x * scalar, this.y * scalar)
    }

    operator fun div(scalar: Float): FloatPosition {
        return FloatPosition(this.x / scalar, this.y / scalar)
    }

    fun dot(pos2: FloatPosition): Float {
        return this.x * pos2.x + this.y * pos2.y
    }

    fun magnitude(): Float {
        return kotlin.math.sqrt(this.x * this.x + this.y * this.y)
    }

    fun normalize(): FloatPosition {
        return this / this.magnitude()
    }

    fun truncate(): IntPosition {
        return IntPosition(this.x.toInt(), this.y.toInt())
    }
}