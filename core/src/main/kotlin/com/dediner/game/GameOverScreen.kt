package com.dediner.game

import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.utils.viewport.ScreenViewport

class GameOverScreen(private val game: DEDinerGame, private val finalScore: Int) : Screen {
    private val batch = SpriteBatch()
    private val stage = Stage(ScreenViewport())

    init {
        val table = Table()
        table.setFillParent(true)
        val skin = Skin()
        val playAgain = TextButton("Play Again", TextButton.TextButtonStyle())
        val mainMenu = TextButton("Main Menu", TextButton.TextButtonStyle())
        playAgain.addListener(object : com.badlogic.gdx.scenes.scene2d.utils.ClickListener() {
            override fun clicked(event: com.badlogic.gdx.scenes.scene2d.InputEvent?, x: Float, y: Float) {
                game.screen = CharacterSelectScreen(game)
            }
        })
        mainMenu.addListener(object : com.badlogic.gdx.scenes.scene2d.utils.ClickListener() {
            override fun clicked(event: com.badlogic.gdx.scenes.scene2d.InputEvent?, x: Float, y: Float) {
                game.screen = MenuScreen(game)
            }
        })
        table.add(playAgain).pad(10f).row()
        table.add(mainMenu).pad(10f)
        stage.addActor(table)
    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        batch.begin()
        game.font.draw(batch, "Game Over", 350f, 450f)
        game.font.draw(batch, "Final Score: $finalScore", 340f, 400f)
        batch.end()
        stage.act(delta)
        stage.draw()
    }

    override fun resize(width: Int, height: Int) { stage.viewport.update(width, height, true) }
    override fun pause() {}
    override fun resume() {}
    override fun show() {}
    override fun hide() {}
    override fun dispose() {
        batch.dispose()
        stage.dispose()
    }
}
