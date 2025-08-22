package com.dediner.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.viewport.ScreenViewport
import com.dediner.DE_Diner

class CharacterSelectScreen(private val game: DE_Diner) : Screen {
    private val batch: SpriteBatch = game.batch
    private val stage: Stage = Stage(ScreenViewport(), batch)
    private val skin: Skin = Skin(Gdx.files.internal("uiskin.json"))

    private val characters = listOf(
        "habeeb" to "Chef Habeeb - Sees opportunity in every bite. Bonus for rare fruits.",
        "lanre" to "Lanre the Ladle - Stirs up trouble for junk food. Faster after power-ups.",
        "mayorkun" to "Mayorkun the Mixer - Blends style with skill. Double points in streaks.",
        "paul" to "Paul the Plate - Keeps things balanced. Has a magnet effect.",
        "mide" to "Mide the Muncher - Bites back at bad food. Slows less when hit.",
        "tobi" to "Tobi the Taster - Knows the good stuff. Gets surprise power-ups.",
        "stephan" to "Stephan the Spatula - Flips the odds. Shield lasts longer.",
        "tomisin" to "Tomisin the Tomato - Cool under pressure. Bonus during danger.",
        "zubby" to "Zubby the Zest - Adds life to the party. Extra life spawns more often."
    )

    override fun show() {
        Gdx.input.inputProcessor = stage

        val table = Table()
        table.setFillParent(true)
        table.defaults().pad(10f)

        // Create 3x3 grid of characters
        var row = 0
        var col = 0
        val gridTable = Table()

        characters.forEachIndexed { index, (name, bio) ->
            val characterImage = Image(game.characterTextures[name])
            characterImage.addListener(object : ClickListener() {
                override fun clicked(event: InputEvent?, x: Float, y: Float) {
                    game.setScreen(GameScreen(game, name))
                }
            })

            gridTable.add(characterImage).size(128f, 128f).pad(5f)

            col++
            if (col == 3) {
                gridTable.row()
                col = 0
                row++
            }
        }

        table.add(gridTable).colspan(3).row()

        // Back button
        val backButton = TextButton("Back", skin)
        backButton.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                game.setScreen(MenuScreen(game))
            }
        })

        table.add(backButton).align(Align.left)

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
