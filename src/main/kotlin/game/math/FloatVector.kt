package asteroid4.tileengine.game.math

data class FloatVector(val x: Float, val y: Float) {
    operator fun plus(pos2: FloatVector) = FloatVector(this.x + pos2.x, this.y + pos2.y)


    operator fun minus(pos2: FloatVector) = FloatVector(this.x - pos2.x, this.y - pos2.y)


    operator fun minus(pos2: IntVector) = FloatVector(this.x - pos2.x, this.y - pos2.y)


    operator fun times(scalar: Float) = FloatVector(this.x * scalar, this.y * scalar)


    operator fun times(scalar: Int) = FloatVector(this.x * scalar, this.y * scalar)


    operator fun div(scalar: Float) = FloatVector(this.x / scalar, this.y / scalar)


    operator fun div(scalar: Int) = FloatVector(this.x / scalar, this.y / scalar)


    fun dot(pos2: FloatVector) = this.x * pos2.x + this.y * pos2.y


    fun magnitude() = kotlin.math.sqrt(this.x * this.x + this.y * this.y)


    fun normalize() = this / this.magnitude()


    fun truncate() = IntVector(this.x.toInt(), this.y.toInt())

    fun flipX() = FloatVector(-this.x, this.y)

    fun flipY() = FloatVector(this.x, -this.y)
}