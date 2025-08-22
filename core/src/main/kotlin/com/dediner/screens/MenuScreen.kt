package com.dediner.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.utils.viewport.ScreenViewport
import com.dediner.DE_Diner

class MenuScreen(private val game: DE_Diner) : Screen {
    private val batch: SpriteBatch = game.batch
    private val stage: Stage = Stage(ScreenViewport(), batch)
    private val skin: Skin = Skin(Gdx.files.internal("uiskin.json"))

    private lateinit var playButton: TextButton
    private lateinit var helpButton: TextButton
    private lateinit var quitButton: TextButton

    override fun show() {
        Gdx.input.inputProcessor = stage

        // Create buttons
        playButton = TextButton("Play", skin)
        helpButton = TextButton("Help", skin)
        quitButton = TextButton("Quit", skin)

        // Position buttons
        playButton.setPosition(Gdx.graphics.width / 2f - 100f, Gdx.graphics.height / 2f)
        helpButton.setPosition(Gdx.graphics.width / 2f - 100f, Gdx.graphics.height / 2f - 60f)
        quitButton.setPosition(Gdx.graphics.width / 2f - 100f, Gdx.graphics.height / 2f - 120f)

        // Add button listeners
        playButton.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                game.setScreen(CharacterSelectScreen(game))
            }
        })

        helpButton.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                game.setScreen(HelpScreen(game))
            }
        })

        quitButton.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                Gdx.app.exit()
            }
        })

        // Add buttons to stage
        stage.addActor(playButton)
        stage.addActor(helpButton)
        stage.addActor(quitButton)

        // Start background music
        game.bgMusic.play()
    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        batch.begin()
        // Draw menu background
        batch.draw(game.menuBg, 0f, 0f, Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
        batch.end()

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
