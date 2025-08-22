package com.dediner.game

import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.utils.viewport.ScreenViewport

class CharacterSelectScreen(private val game: DEDinerGame) : Screen {
    private val batch = SpriteBatch()
    private val font: BitmapFont = game.font
    private val stage = Stage(ScreenViewport())
    private val charFiles = listOf("habeeb.png","lanre.png","mayorkun.png","paul.png","mide.png","tobi.png","stephan.png","tomisin.png","zubby.png","juyi.png")
    private val bios = mapOf(
        "habeeb.png" to "Sees opportunity in every bite.",
        "lanre.png" to "Stirs up trouble for junk food.",
        "mayorkun.png" to "Blends style with skill.",
        "paul.png" to "Keeps things balanced.",
        "mide.png" to "Bites back at bad food.",
        "tobi.png" to "Knows the good stuff.",
        "stephan.png" to "Flips the odds.",
        "tomisin.png" to "Cool under pressure.",
        "zubby.png" to "Adds life to the party.",
        "juyi.png" to "Unknown legend."
    )

    init {
        Gdx.input.inputProcessor = stage
        val skin = Skin()
        val table = Table()
        table.setFillParent(true)
        table.top().padTop(20f)

        // build grid 3x4 (show 9 visible + scroll possible)
        var idx = 0
        val cols = 3
        while (idx < charFiles.size) {
            for (c in 0 until cols) {
                if (idx >= charFiles.size) break
                val tex = game.assets.get("characters/${charFiles[idx]}", Texture::class.java)
                val btn = ImageButton(com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable(com.badlogic.gdx.graphics.g2d.TextureRegion(tex)))
                val chosen = charFiles[idx]
                btn.addListener(object : com.badlogic.gdx.scenes.scene2d.utils.ClickListener() {
                    override fun clicked(event: com.badlogic.gdx.scenes.scene2d.InputEvent?, x: Float, y: Float) {
                        // start game with selected character
                        val character = CharacterInfo(chosen.removeSuffix(".png"), bios[chosen] ?: "", tex)
                        game.screen = GameScreen(game, character)
                    }
                })
                table.add(btn).size(150f,150f).pad(10f)
                idx++
            }
            table.row()
        }
        stage.addActor(table)
    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(0.95f, 0.95f, 0.95f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        stage.act(delta)
        stage.draw()
    }
    override fun resize(width: Int, height: Int) { stage.viewport.update(width, height, true) }
    override fun pause() {}
    override fun resume() {}
    override fun show() {}
    override fun hide() {}
    override fun dispose() { batch.dispose(); stage.dispose() }
}
