package asteroid4.tileengine.screen

import asteroid4.tileengine.ProgramData
import asteroid4.tileengine.Registries
import asteroid4.tileengine.game.GameManager
import asteroid4.tileengine.game.math.IntVector
import asteroid4.tileengine.game.world.World
import asteroid4.tileengine.game.world.generator.NullWorldGenerator
import asteroid4.tileengine.input.InputManager
import asteroid4.tileengine.registry.RegistryKey
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.event.ActionEvent
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
                    GameManager.currentWorld = World(0, NullWorldGenerator())
                }

                ScreenType.EXIT -> exitProcess(0)
            }
            this.validate()
            this.repaint()
            field = value
        }

    init {
        currentScreen = startingScreen
        addKeyListener(InputManager)
        isFocusable = true
    }

    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        if (currentScreen == ScreenType.IN_GAME) {
            val g2 = g as Graphics2D
            GameManager.currentWorld?.getLoadedChunks()?.forEach { chunkPos -> renderChunk(g2, chunkPos.x, chunkPos.y) }
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
            null
        )
        g2.drawString(GameManager.currentWorld?.player?.position.toString(), 10, 10)
    }

    private fun renderChunk(g2: Graphics2D, chunkX: Int, chunkY: Int) {
        for (x in 0..15) {
            for (y in 0..15) {
                val tilePosition = IntVector((chunkX * 16) + x, (chunkY * 16) + y)
                if (GameManager.getTile(tilePosition)?.invisible == false) {
                    val tileRenderingPosition = getRenderLocation(tilePosition.untruncate())
                    g2.drawImage(
                        GameManager.getTileImage(tilePosition),
                        tileRenderingPosition.x,
                        tileRenderingPosition.y,
                        ProgramData.TILE_SIZE,
                        ProgramData.TILE_SIZE,
                        null
                    )
                }
            }
        }
    }

    private fun screenChangeButton(name: String, newScreen: ScreenType): JButton {
        return JButton(object : AbstractAction(name) {
            override fun actionPerformed(e: ActionEvent?) {
                currentScreen = newScreen
            }
        })
    }

    private fun getScreenCenter(): asteroid4.tileengine.game.math.IntVector {
        return IntVector(width / 2, height / 2)
    }

    //TODO: Make better name
    fun getScreenCornerGlobalPositions(): Array<asteroid4.tileengine.game.math.FloatVector> {
        val corners = arrayOf(IntVector(0, 0), IntVector(width, height))
        return corners.map { corner -> getGlobalLocation(corner) }.toTypedArray()
    }

    private fun getRenderLocation(globalLocation: asteroid4.tileengine.game.math.FloatVector) =
        ((globalLocation - GameManager.currentWorld?.player?.position!!) * ProgramData.TILE_SIZE).truncate()

    private fun getGlobalLocation(renderLocation: asteroid4.tileengine.game.math.IntVector) =
        (renderLocation.untruncate() / ProgramData.TILE_SIZE) + GameManager.currentWorld?.player?.position!!
}
