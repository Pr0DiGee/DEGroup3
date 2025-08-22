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

class HelpScreen(private val game: DE_Diner) : Screen {
    private val batch: SpriteBatch = game.batch
    private val stage: Stage = Stage(ScreenViewport(), batch)
    private val skin: Skin = Skin(Gdx.files.internal("uiskin.json"))

    override fun show() {
        Gdx.input.inputProcessor = stage

        val table = Table()
        table.setFillParent(true)
        table.defaults().pad(10f)

        // Title
        val titleLabel = Label("How to Play DE Diner", skin)
        titleLabel.setFontScale(1.5f)
        table.add(titleLabel).colspan(2).row()

        // Game instructions
        val instructions = """
            • Move left/right to dodge bad food and collect fruits
            • Desktop: Use arrow keys
            • Mobile: Swipe left/right

            Good Fruits:
            • Apple: +1 life
            • Banana: Double points for 10 seconds
            • Orange: Shield for 1 hit
            • Pineapple: Magnet effect for 5 seconds

            Bad Foods:
            • Burger/Pizza/Donut: -1 life
            • Poison: -3 lives

            Start with 5 lives. Game ends when lives reach 0.

            Every 30 seconds: Speed increases
            Every 60 seconds: Chaos Mode (5 seconds)
        """.trimIndent()

        val instructionsLabel = Label(instructions, skin)
        instructionsLabel.setWrap(true)
        table.add(instructionsLabel).width(Gdx.graphics.width * 0.8f).colspan(2).row()

        // Back button
        val backButton = TextButton("Back to Menu", skin)
        backButton.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                game.setScreen(MenuScreen(game))
            }
        })

        table.add(backButton).colspan(2).row()

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
