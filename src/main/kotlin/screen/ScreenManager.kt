package asteroid4.tileengine.screen

import asteroid4.tileengine.ProgramData
import java.awt.Dimension
import javax.swing.*

class ScreenManager(startingWidth: Int, startingHeight: Int) {
    private var frame = JFrame(ProgramData.PROGRAM_NAME)
    private var screen = Screen(ScreenType.MAIN_MENU)

    init {
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.size = Dimension(startingWidth, startingHeight)

        frame.contentPane = screen
        frame.isVisible = true
    }

    fun shouldBeInUnpausedGame(): Boolean = screen.currentScreen == ScreenType.IN_GAME

    fun frame() = screen.repaint()
}