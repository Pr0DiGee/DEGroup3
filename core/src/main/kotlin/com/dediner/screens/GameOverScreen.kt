package com.dediner.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.viewport.ScreenViewport
import com.dediner.DE_Diner

class GameOverScreen(private val game: DE_Diner, private val finalScore: Int) : Screen {
    private val batch: SpriteBatch = game.batch
    private val stage: Stage = Stage(ScreenViewport(), batch)
    private val skin: Skin = Skin(Gdx.files.internal("uiskin.json"))

    override fun show() {
        Gdx.input.inputProcessor = stage
        game.gameoverSound.play()
        game.bgMusic.stop()

        val table = Table()
        table.setFillParent(true)
        table.defaults().pad(10f)

        // Game Over title
        val titleLabel = Label("GAME OVER", skin)
        titleLabel.setFontScale(2f)
        table.add(titleLabel).colspan(2).row()

        // Final score
        val scoreLabel = Label("Final Score: $finalScore", skin)
        scoreLabel.setFontScale(1.2f)
        table.add(scoreLabel).colspan(2).row()

        // Play Again button
        val playAgainButton = TextButton("Play Again", skin)
        playAgainButton.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                game.setScreen(CharacterSelectScreen(game))
            }
        })
        table.add(playAgainButton).width(200f).height(60f).colspan(2).row()

        // Main Menu button
        val menuButton = TextButton("Main Menu", skin)
        menuButton.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                game.setScreen(MenuScreen(game))
            }
        })
        table.add(menuButton).width(200f).height(60f).colspan(2).row()

        stage.addActor(table)
    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        stage.act(delta)
        stage.draw()
    }

    override fun resize(width: Int, height: Int) {
        stage.viewport.update(width, height, true)
    }

    override fun pause() {}
    override fun resume() {}
    override fun hide() {}
    override fun dispose() {
        stage.dispose()
    }
}
