package com.dediner.game

import com.badlogic.gdx.Game
import com.badlogic.gdx.graphics.g2d.SpriteBatch

class DEDinerGame : Game() {

    lateinit var batch: SpriteBatch

    override fun create() {
        batch = SpriteBatch()
        this.setScreen(GameScreen(this)) // start directly with GameScreen for testing
    }

    override fun dispose() {
        batch.dispose()
        super.dispose()
    }
}
