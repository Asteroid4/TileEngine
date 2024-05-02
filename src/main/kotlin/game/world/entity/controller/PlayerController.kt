package asteroid4.tileengine.game.world.entity.controller

import asteroid4.tileengine.KeyBinds
import asteroid4.tileengine.ProgramData
import asteroid4.tileengine.game.math.FloatVector
import asteroid4.tileengine.input.InputManager

class PlayerController : Controller() {
    init {
        InputManager.listen(KeyBinds.PLAYER_JUMP)
        InputManager.listen(KeyBinds.PLAYER_LEFT)
        InputManager.listen(KeyBinds.PLAYER_RIGHT)
        InputManager.listen(KeyBinds.PLAYER_CROUCH)
    }

    override fun update(): FloatVector {
        var velocity = FloatVector(0f, 0f)
        if (InputManager.isKeyPressed(KeyBinds.PLAYER_JUMP)) velocity += FloatVector(0f, ProgramData.PLAYER_SPEED)
        if (InputManager.isKeyPressed(KeyBinds.PLAYER_LEFT)) velocity += FloatVector(-ProgramData.PLAYER_SPEED, 0f)
        if (InputManager.isKeyPressed(KeyBinds.PLAYER_RIGHT)) velocity += FloatVector(ProgramData.PLAYER_SPEED, 0f)
        if (InputManager.isKeyPressed(KeyBinds.PLAYER_CROUCH)) velocity += FloatVector(0f, -ProgramData.PLAYER_SPEED)
        return velocity
    }
}