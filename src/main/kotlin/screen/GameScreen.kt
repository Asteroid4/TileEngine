package asteroid4.screen

import asteroid4.ProgramData
import java.awt.event.ActionEvent
import javax.swing.AbstractAction
import javax.swing.JButton
import javax.swing.JLabel
import javax.swing.JPanel
import kotlin.system.exitProcess

class GameScreen(startingScreen : ScreenType) : JPanel() {
    var currentScreen = startingScreen
        set(value) {
            this.removeAll()
            when (value) {
                ScreenType.MAIN_MENU -> {
                    val title = JLabel(ProgramData.PROGRAM_NAME)
                    this.add(title)

                    val singleplayerButton = screenChangeButton("Singleplayer", ScreenType.SINGLEPLAYER_MENU)
                    this.add(singleplayerButton)

                    val multiplayerButton = screenChangeButton("Multiplayer", ScreenType.MULTIPLAYER_MENU)
                    this.add(multiplayerButton)

                    val modsButton = screenChangeButton("Mods", ScreenType.MODS_MENU)
                    this.add(modsButton)

                    val settingsButton = screenChangeButton("Settings", ScreenType.SETTINGS_MENU)
                    this.add(settingsButton)

                    val quitButton = screenChangeButton("Quit", ScreenType.EXIT)
                    this.add(quitButton)
                }

                ScreenType.SINGLEPLAYER_MENU -> {
                    val title = JLabel("Singleplayer")
                    this.add(title)

                    val backButton = screenChangeButton("Back", ScreenType.MAIN_MENU)
                    this.add(backButton)
                }

                ScreenType.MULTIPLAYER_MENU -> {
                    val title = JLabel("Multiplayer")
                    this.add(title)

                    val backButton = screenChangeButton("Back", ScreenType.MAIN_MENU)
                    this.add(backButton)
                }

                ScreenType.MODS_MENU -> {
                    val title = JLabel("Mods")
                    this.add(title)

                    val backButton = screenChangeButton("Back", ScreenType.MAIN_MENU)
                    this.add(backButton)
                }

                ScreenType.SETTINGS_MENU -> {
                    val title = JLabel("Settings")
                    this.add(title)

                    val backButton = screenChangeButton("Back", ScreenType.MAIN_MENU)
                    this.add(backButton)
                }

                ScreenType.IN_GAME -> {
                    exitProcess(1)
                }

                ScreenType.EXIT -> {
                    exitProcess(0)
                }
            }
            this.validate()
            this.repaint()
            field = value
        }

    init {
        currentScreen = startingScreen
    }

    private fun screenChangeButton(name : String, newScreen : ScreenType) : JButton {
        return JButton(object: AbstractAction(name) {
            override fun actionPerformed(e: ActionEvent?) {
                currentScreen = newScreen
            }
        })
    }
}