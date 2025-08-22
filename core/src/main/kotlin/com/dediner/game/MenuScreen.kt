package com.dediner.game

import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.utils.viewport.ScreenViewport

class MenuScreen(private val game: DEDinerGame) : Screen {
    private val batch = SpriteBatch()
    private val bg = game.assets.get("bg/menu_bg.png", Texture::class.java)
    private val stage = Stage(ScreenViewport())

    init {
        Gdx.input.inputProcessor = stage
        val skin = Skin() // minimal — you should put a skin.json for production
        val table = Table()
        table.setFillParent(true)

        val play = TextButton("Play", TextButton.TextButtonStyle()) // replace with proper style
        val help = TextButton("Help", TextButton.TextButtonStyle())
        val quit = TextButton("Quit", TextButton.TextButtonStyle())

        play.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                game.screen = CharacterSelectScreen(game)
            }
        })
        help.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                game.screen = HelpScreen(game)
            }
        })
        quit.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                Gdx.app.exit()
            }
        })

        // quick table layout
        table.add(play).pad(10f).row()
        table.add(help).pad(10f).row()
        table.add(quit).pad(10f)
        stage.addActor(table)
    }

    override fun render(delta: Float) {
        batch.begin()
        batch.draw(bg, 0f, 0f, Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
        batch.end()
        stage.act(delta)
        stage.draw()

        // keyboard shortcuts
        if (Gdx.input.isKeyJustPressed(Input.Keys.P)) game.screen = CharacterSelectScreen(game)
        if (Gdx.input.isKeyJustPressed(Input.Keys.H)) game.screen = HelpScreen(game)
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) Gdx.app.exit()
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
