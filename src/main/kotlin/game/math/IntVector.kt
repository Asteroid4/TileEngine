package asteroid4.tileengine.game.math

data class IntVector(val x: Int, val y: Int) {
    operator fun plus(pos2: IntVector) = IntVector(this.x + pos2.x, this.y + pos2.y)

    operator fun plus(pos2: FloatVector) = FloatVector(this.x + pos2.x, this.y + pos2.y)

    operator fun minus(pos2: IntVector) = IntVector(this.x - pos2.x, this.y - pos2.y)

    operator fun minus(pos2: FloatVector) = FloatVector(this.x - pos2.x, this.y - pos2.y)

    operator fun times(scalar: Int) = IntVector(this.x * scalar, this.y * scalar)

    operator fun times(scalar: Float) = FloatVector(this.x * scalar, this.y * scalar)

    operator fun div(scalar: Int) = IntVector(this.x / scalar, this.y / scalar)

    operator fun div(scalar: Float) = FloatVector(this.x / scalar, this.y / scalar)

    fun dot(pos2: IntVector) = this.x * pos2.x + this.y * pos2.y

    fun magnitude() = kotlin.math.sqrt((this.x * this.x + this.y * this.y).toDouble()).toFloat()

    fun normalize() = this / this.magnitude()

    fun untruncate() = FloatVector(this.x.toFloat(), this.y.toFloat())

    fun flipX() = IntVector(-this.x, this.y)

    fun flipY() = IntVector(this.x, -this.y)
}