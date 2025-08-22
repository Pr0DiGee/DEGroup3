package com.dediner

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.dediner.screens.MenuScreen

class DE_Diner : Game() {
    lateinit var batch: SpriteBatch
    lateinit var assetManager: AssetManager
    lateinit var font: BitmapFont

    // Game assets
    lateinit var bgMusic: Music
    lateinit var eatSound: Sound
    lateinit var badHitSound: Sound
    lateinit var powerupSound: Sound
    lateinit var gameoverSound: Sound

    // Character textures
    val characterTextures = mutableMapOf<String, Texture>()

    // Fruit textures
    val fruitTextures = mutableMapOf<String, Texture>()

    // Bad food textures
    val badFoodTextures = mutableMapOf<String, Texture>()

    // Background textures
    lateinit var menuBg: Texture
    lateinit var gameBg: Texture

    override fun create() {
        batch = SpriteBatch()
        assetManager = AssetManager()
        font = BitmapFont()

        // Load assets
        loadAssets()

        // Set initial screen
        setScreen(MenuScreen(this))
    }

    private fun loadAssets() {
        // Load character textures
        val characters = listOf("habeeb", "lanre", "mayorkun", "paul", "mide", "tobi", "stephan", "tomisin", "zubby")
        characters.forEach { character ->
            assetManager.load("characters/$character.png", Texture::class.java)
        }

        // Load fruit textures
        val fruits = listOf("apple", "banana", "orange", "pineapple")
        fruits.forEach { fruit ->
            assetManager.load("fruits/$fruit.png", Texture::class.java)
        }

        // Load bad food textures
        val badFoods = listOf("burger", "pizza", "donut", "poison")
        badFoods.forEach { food ->
            assetManager.load("badfoods/$food.png", Texture::class.java)
        }

        // Load backgrounds
        assetManager.load("bg/menu_bg.png", Texture::class.java)
        assetManager.load("bg/game_bg.png", Texture::class.java)

        // Load sounds
        assetManager.load("sounds/eat.wav", Sound::class.java)
        assetManager.load("sounds/badhit.wav", Sound::class.java)
        assetManager.load("sounds/powerup.wav", Sound::class.java)
        assetManager.load("sounds/gameover.wav", Sound::class.java)

        // Load music
        assetManager.load("music/bgmusic.mp3", Music::class.java)

        // Wait for assets to load
        assetManager.finishLoading()

        // Assign loaded assets
        characters.forEach { character ->
            characterTextures[character] = assetManager.get("characters/$character.png", Texture::class.java)
        }

        fruits.forEach { fruit ->
            fruitTextures[fruit] = assetManager.get("fruits/$fruit.png", Texture::class.java)
        }

        badFoods.forEach { food ->
            badFoodTextures[food] = assetManager.get("badfoods/$food.png", Texture::class.java)
        }

        menuBg = assetManager.get("bg/menu_bg.png", Texture::class.java)
        gameBg = assetManager.get("bg/game_bg.png", Texture::class.java)

        eatSound = assetManager.get("sounds/eat.wav", Sound::class.java)
        badHitSound = assetManager.get("sounds/badhit.wav", Sound::class.java)
        powerupSound = assetManager.get("sounds/powerup.wav", Sound::class.java)
        gameoverSound = assetManager.get("sounds/gameover.wav", Sound::class.java)

        bgMusic = assetManager.get("music/bgmusic.mp3", Music::class.java)
        bgMusic.isLooping = true
    }

    override fun render() {
        super.render()
    }

    override fun dispose() {
        batch.dispose()
        font.dispose()
        assetManager.dispose()

        // Dispose all textures
        characterTextures.values.forEach { it.dispose() }
        fruitTextures.values.forEach { it.dispose() }
        badFoodTextures.values.forEach { it.dispose() }
        menuBg.dispose()
        gameBg.dispose()

        // Dispose sounds and music
        bgMusic.dispose()
    }
}

