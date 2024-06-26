package asteroid4.tileengine.screen

import asteroid4.tileengine.ProgramData
import java.awt.Dimension
import javax.swing.JFrame

object ScreenManager {
    private var frame = JFrame(ProgramData.PROGRAM_NAME)
    val screen = Screen(ScreenType.MAIN_MENU)

    init {
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.size = Dimension(ProgramData.STARTING_WIDTH, ProgramData.STARTING_HEIGHT)

        frame.contentPane = screen
        frame.isVisible = true
    }

    fun shouldBeInUnpausedGame(): Boolean = screen.currentScreen == ScreenType.IN_GAME
}