package com.dediner.game

import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Color

class HelpScreen(private val game: DEDinerGame) : Screen {
    private val batch = SpriteBatch()
    private val font = game.font

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        batch.begin()
        font.data.setScale(1.0f)
        font.color = Color.BLACK
        val lines = listOf(
            "How to play: Move left/right to dodge bad food and collect fruits.",
            "Desktop: Arrow keys. Mobile: swipe left/right.",
            "",
            "Fruits:",
            "Apple - +1 life. (collect it)",
            "Banana - Double points for 10s.",
            "Orange - Shield: ignore 1 hit.",
            "Pineapple - Magnet: fruits pulled for 5s.",
            "",
            "Bad food: Burger, Pizza, Donut, Poison (poison = -3 lives).",
            "Start with 5 lives. Each fruit +10 points.",
            "Every 30s: items spawn faster. Every 60s: Chaos Mode (5s).",
            "",
            "Power-ups stack logically; picking the same power-up resets its timer."
        )
        var y = Gdx.graphics.height - 30f
        for (l in lines) {
            font.draw(batch, l, 20f, y)
            y -= 24f
        }
        font.draw(batch, "Press ESC to return to menu", 20f, 40f)
        batch.end()

        if (Gdx.input.isKeyJustPressed(com.badlogic.gdx.Input.Keys.ESCAPE)) {
            game.screen = MenuScreen(game)
        }
    }

    override fun resize(width: Int, height: Int) {}
    override fun pause() {}
    override fun resume() {}
    override fun show() {}
    override fun hide() {}
    override fun dispose() { batch.dispose() }
}
