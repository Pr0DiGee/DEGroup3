package com.dediner.game

import com.badlogic.gdx.Game
import com.badlogic.gdx.graphics.g2d.SpriteBatch import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Texture

class DEDinerGame : Game() {

    lateinit var batch: SpriteBatch
    lateinit var assets: AssetManager

    override fun create() {
        batch = SpriteBatch()
        assets = AssetManager()

        // Preload basic textures
        assets.load("characters/habeeb.png", Texture::class.java)
        assets.load("fruits/apple.png", Texture::class.java)
        assets.load("badfoods/burger.png", Texture::class.java)
        assets.finishLoading()

        // Start game
        this.setScreen(GameScreen(this))
    }

    override fun dispose() {
        batch.dispose()
        assets.dispose()
        super.dispose()
    }
}

