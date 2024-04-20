package asteroid4.screen

import asteroid4.ProgramData
import asteroid4.Registries
import asteroid4.game.FloatPosition
import asteroid4.game.IntPosition
import asteroid4.game.world.World
import asteroid4.game.world.generator.NullWorldGenerator
import asteroid4.registry.RegistryKey
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.Image
import java.awt.event.ActionEvent
import java.awt.image.ImageObserver
import javax.swing.AbstractAction
import javax.swing.JButton
import javax.swing.JLabel
import javax.swing.JPanel
import kotlin.system.exitProcess

class Screen(startingScreen: ScreenType): JPanel() {
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

                    val inGameButton = screenChangeButton("IN_GAME", ScreenType.IN_GAME)
                    this.add(inGameButton)

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

                    val refreshButton = JButton(object: AbstractAction("refresh") {
                        override fun actionPerformed(e: ActionEvent?) {
                            ProgramData.SCRIPT_LOADER.refresh()
                        }
                    })
                    this.add(refreshButton)

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
                    ProgramData.GAME_MANAGER.currentWorld = World(0, NullWorldGenerator())
                }

                ScreenType.EXIT -> exitProcess(0)
            }
            this.validate()
            this.repaint()
            field = value
        }

    init {
        currentScreen = startingScreen
    }

    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        if (currentScreen == ScreenType.IN_GAME) {
            val g2 = g as Graphics2D
            val playerIntPos = ProgramData.GAME_MANAGER.currentWorld?.player?.position?.truncate()
            if (playerIntPos != null) {
                for (chunkX in (playerIntPos.x-2)..(playerIntPos.x+2)) {
                    for (chunkY in (playerIntPos.y-2)..(playerIntPos.y+2)) {
                        renderChunk(g2, chunkX, chunkY)
                    }
                }
            }
            renderPlayer(g2)
        }
    }

    private fun renderPlayer(g2: Graphics2D) {
        g2.drawImage(
            Registries.IMAGE_REGISTRY[RegistryKey("required", "entity", "player")],
            getScreenCenter().x - ProgramData.TILE_SIZE / 2,
            getScreenCenter().y - ProgramData.TILE_SIZE,
            ProgramData.TILE_SIZE,
            ProgramData.TILE_SIZE * 2,
            ImageObserver(fun(_: Image, _: Int, _: Int, _: Int, _: Int, _: Int): Boolean { return false })
        )
    }

    private fun renderChunk(g2: Graphics2D, chunkX: Int, chunkY: Int) {
        for (x in 0..15) {
            for (y in 0..15) {
                val tilePosition = IntPosition((chunkX * 16) + x, (chunkY * 16) + y)
                if (ProgramData.GAME_MANAGER.getTile(tilePosition)?.isInvisible == false) {
                    val tileRenderingPosition = getTileRenderLocation(tilePosition)
                    g2.drawImage(
                        ProgramData.GAME_MANAGER.getTileImage(tilePosition),
                        tileRenderingPosition.x + 100,
                        tileRenderingPosition.y + 100,
                        ProgramData.TILE_SIZE,
                        ProgramData.TILE_SIZE,
                        ImageObserver(fun(_: Image, _: Int, _: Int, _: Int, _: Int, _: Int): Boolean { return false })
                    )
                }
            }
        }
    }

    private fun screenChangeButton(name: String, newScreen: ScreenType): JButton {
        return JButton(object: AbstractAction(name) {
            override fun actionPerformed(e: ActionEvent?) {
                currentScreen = newScreen
            }
        })
    }

    private fun getScreenCenter(): IntPosition {
        return IntPosition(width / 2, height / 2)
    }

    private fun getTileRenderLocation(literalLocation: IntPosition): IntPosition {
        return literalLocation
    }
}