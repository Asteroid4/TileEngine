package asteroid4.screen

import asteroid4.ProgramData
import java.awt.Dimension
import java.awt.event.ActionEvent
import javax.swing.*
import kotlin.system.exitProcess

class ScreenManager() {
    enum class Screen {
        MAIN_MENU,
        MODS_MENU,
        SETTINGS_MENU,
        IN_GAME,
        EXIT
    }

    private val width = 512
    private val height = 512

    private var frame = JFrame(ProgramData.PROGRAM_NAME)
    private var mainMenu = JPanel()
    private var modsMenu = JPanel()
    private var settingsMenu = JPanel()
    var currentScreen = Screen.MAIN_MENU
        set(value) {
            if (value == Screen.EXIT) exitProcess(exitCode)
            field = value
        }
    private var exitCode = 0

    init {
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.size = Dimension(width, height)

        mainMenu.name = "mainMenu"
        modsMenu.name = "modsMenu"
        settingsMenu.name = "settingsMenu"

        val title = JLabel(ProgramData.PROGRAM_NAME)
        mainMenu.add(title)

        val singleplayerButton = JButton()
        singleplayerButton.action = screenChangeAction("Singleplayer", Screen.EXIT)
        singleplayerButton.isEnabled = false //Remove when implemented
        mainMenu.add(singleplayerButton)

        val multiplayerButton = JButton()
        multiplayerButton.action = screenChangeAction("Multiplayer", Screen.EXIT)
        multiplayerButton.isEnabled = false //Remove when implemented
        mainMenu.add(multiplayerButton)

        val modsButton = JButton()
        modsButton.action = screenChangeAction("Mods", Screen.MODS_MENU)
        modsButton.isEnabled = false //Remove when implemented
        mainMenu.add(modsButton)

        val settingsButton = JButton()
        settingsButton.action = screenChangeAction("Settings", Screen.SETTINGS_MENU)
        settingsButton.isEnabled = false //Remove when implemented
        mainMenu.add(settingsButton)

        val quitButton = JButton()
        quitButton.action = screenChangeAction("Quit", Screen.EXIT)
        mainMenu.add(quitButton)

        frame.contentPane = mainMenu
        frame.isVisible = true
    }

    fun tick() {
    }

    fun screenChangeAction(name : String, newScreen : Screen) : AbstractAction {
        return object: AbstractAction(name) {
            override fun actionPerformed(e: ActionEvent?) {
                currentScreen = newScreen
            }
        }
    }
}