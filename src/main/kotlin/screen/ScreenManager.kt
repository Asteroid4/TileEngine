package asteroid4.screen

import asteroid4.ProgramData
import java.awt.Dimension
import javax.swing.*

class ScreenManager(startingWidth : Int, startingHeight : Int) {
    private var frame = JFrame(ProgramData.PROGRAM_NAME)
    private var screen = Screen(ScreenType.MAIN_MENU)

    init {
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.size = Dimension(startingWidth, startingHeight)
        frame.contentPane = screen
        frame.isVisible = true
    }

    fun shouldBeInUnpausedGame() : Boolean {
        return screen.currentScreen == ScreenType.IN_GAME
    }
}