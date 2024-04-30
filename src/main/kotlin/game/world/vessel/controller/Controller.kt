package asteroid4.tileengine.game.world.vessel.controller

import asteroid4.tileengine.game.math.FloatVector

abstract class Controller {
    abstract fun update(): FloatVector
}