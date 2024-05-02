package asteroid4.tileengine.game.world.entity.controller

import asteroid4.tileengine.game.math.FloatVector

class NullController() : Controller() {
    override fun update(): FloatVector = FloatVector(0f, 0f)
}