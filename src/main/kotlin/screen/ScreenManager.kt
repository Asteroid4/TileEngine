package asteroid4.screen

import asteroid4.ProgramData
import java.awt.Dimension
import java.awt.Graphics
import java.awt.event.ActionEvent
import javax.swing.*
import kotlin.system.exitProcess

class ScreenManager(startingWidth : Int, startingHeight : Int) {
    private var frame = JFrame(ProgramData.PROGRAM_NAME)
    private var screen = GameScreen(ScreenType.MAIN_MENU)

    init {
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.size = Dimension(startingWidth, startingHeight)
        frame.contentPane = screen
        frame.isVisible = true
    }

    fun isInUnpausedGame() : Boolean {
        return screen.currentScreen == ScreenType.IN_GAME
    }
}