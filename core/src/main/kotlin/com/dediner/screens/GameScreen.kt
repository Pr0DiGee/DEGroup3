package com.dediner.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.Array
import com.dediner.DE_Diner
import com.dediner.models.BadFoodItem
import com.dediner.models.FruitItem
import com.dediner.models.Player
import kotlin.random.Random

class GameScreen(private val game: DE_Diner, private val characterName: String) : Screen {
    private val batch: SpriteBatch = game.batch
    private lateinit var player: Player
    private val items = Array<com.dediner.models.Item>()
    private var gameTime: Float = 0f
    private var spawnTimer: Float = 0f
    private var spawnInterval: Float = 1f
    private var baseSpeed: Float = 100f
    private var chaosMode: Boolean = false
    private var chaosTimer: Float = 0f

    override fun show() {
        player = Player(characterName, game.characterTextures[characterName]!!)
        game.bgMusic.play()
    }

    override fun render(delta: Float) {
        update(delta)

        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        batch.begin()

        // Draw background
        batch.draw(game.gameBg, 0f, 0f, Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())

        // Draw player
        batch.draw(player.texture, player.x, player.y, player.width, player.height)

        // Draw items
        items.forEach { item ->
            when (item) {
                is FruitItem -> batch.draw(item.texture, item.position.x, item.position.y, item.width, item.height)
                is BadFoodItem -> batch.draw(item.texture, item.position.x, item.position.y, item.width, item.height)
            }
        }

        // Draw HUD
        game.font.draw(batch, "Lives: ${player.lives}", 20f, Gdx.graphics.height - 20f)
        game.font.draw(batch, "Score: ${player.score}", Gdx.graphics.width - 120f, Gdx.graphics.height - 20f)
        game.font.draw(batch, "Time: ${gameTime.toInt()}s", Gdx.graphics.width / 2f - 40f, Gdx.graphics.height - 20f)

        // Draw active power-ups
        if (player.doublePoints) {
            game.font.draw(batch, "2x Points!", 20f, Gdx.graphics.height - 50f)
        }
        if (player.hasShield) {
            game.font.draw(batch, "Shield Active!", 20f, Gdx.graphics.height - 80f)
        }
        if (player.magnetActive) {
            game.font.draw(batch, "Magnet Active!", 20f, Gdx.graphics.height - 110f)
        }
        if (chaosMode) {
            game.font.draw(batch, "CHAOS MODE!", Gdx.graphics.width / 2f - 50f, 50f)
        }

        batch.end()
    }

    private fun update(delta: Float) {
        // Handle input
        handleInput(delta)

        // Update player
        player.update(delta)

        // Update game time
        gameTime += delta

        // Check for difficulty increases
        if (gameTime % 30f < delta && gameTime > 30f) {
            baseSpeed += 20f
            spawnInterval = MathUtils.clamp(spawnInterval - 0.1f, 0.3f, 1f)
        }

        // Check for chaos mode
        if (gameTime % 60f < delta && gameTime > 60f) {
            activateChaosMode()
        }

        if (chaosMode) {
            chaosTimer -= delta
            if (chaosTimer <= 0) {
                chaosMode = false
                baseSpeed -= 100f
            }
        }

        // Spawn items
        spawnTimer += delta
        if (spawnTimer >= spawnInterval) {
            spawnTimer = 0f
            spawnRandomItem()
        }

        // Update items
        val iterator = items.iterator()
        while (iterator.hasNext()) {
            val item = iterator.next()
            item.update(delta)

            // Check for collisions
            if (item.bounds.overlaps(player.bounds)) {
                handleCollision(item)
                iterator.remove()
                continue
            }

            // Remove off-screen items
            if (item.isOffScreen) {
                iterator.remove()
            }
        }
    }

    private fun handleInput(delta: Float) {
        // Desktop controls
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            player.x = MathUtils.clamp(player.x - 300f * delta, 0f, Gdx.graphics.width - player.width)
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            player.x = MathUtils.clamp(player.x + 300f * delta, 0f, Gdx.graphics.width - player.width)
        }

        // Mobile touch controls (simplified)
        if (Gdx.input.isTouched) {
            val touchX = Gdx.input.x.toFloat()
            if (touchX < Gdx.graphics.width / 2f) {
                player.x = MathUtils.clamp(player.x - 300f * delta, 0f, Gdx.graphics.width - player.width)
            } else {
                player.x = MathUtils.clamp(player.x + 300f * delta, 0f, Gdx.graphics.width - player.width)
            }
        }
    }

    private fun spawnRandomItem() {
        val x = Random.nextFloat() * (Gdx.graphics.width - 64f)
        val position = Vector2(x, Gdx.graphics.height.toFloat())

        val itemSpeed = if (chaosMode) baseSpeed + 100f else baseSpeed

        when (Random.nextInt(10)) {
            0 -> { // Apple - +1 life
                items.add(FruitItem(game.fruitTextures["apple"]!!, "apple", "life", position, itemSpeed))
            }
            1 -> { // Banana - Double points
                items.add(FruitItem(game.fruitTextures["banana"]!!, "banana", "double", position, itemSpeed))
            }
            2 -> { // Orange - Shield
                items.add(FruitItem(game.fruitTextures["orange"]!!, "orange", "shield", position, itemSpeed))
            }
            3 -> { // Pineapple - Magnet
                items.add(FruitItem(game.fruitTextures["pineapple"]!!, "pineapple", "magnet", position, itemSpeed))
            }
            4, 5, 6 -> { // Burger, Pizza, Donut - -1 life
                val badFoods = listOf("burger", "pizza", "donut")
                val foodType = badFoods[Random.nextInt(badFoods.size)]
                items.add(BadFoodItem(game.badFoodTextures[foodType]!!, foodType, 1, position, itemSpeed))
            }
            7 -> { // Poison - -3 lives
                items.add(BadFoodItem(game.badFoodTextures["poison"]!!, "poison", 3, position, itemSpeed))
            }
            else -> { // Regular fruits for points
                val fruits = listOf("apple", "banana", "orange", "pineapple")
                val fruitType = fruits[Random.nextInt(fruits.size)]
                items.add(FruitItem(game.fruitTextures[fruitType]!!, fruitType, "points", position, itemSpeed))
            }
        }
    }

    private fun handleCollision(item: com.dediner.models.Item) {
        when (item) {
            is FruitItem -> {
                when (item.effect) {
                    "life" -> {
                        player.lives++
                        game.eatSound.play()
                    }
                    "double" -> {
                        player.activateDoublePoints()
                        game.powerupSound.play()
                    }
                    "shield" -> {
                        player.activateShield()
                        game.powerupSound.play()
                    }
                    "magnet" -> {
                        player.activateMagnet()
                        game.powerupSound.play()
                    }
                    "points" -> {
                        player.addScore(10)
                        game.eatSound.play()
                    }
                }
            }
            is BadFoodItem -> {
                val gameOver = player.takeDamage(item.damage)
                game.badHitSound.play()

                if (gameOver) {
                    game.setScreen(GameOverScreen(game, player.score))
                }
            }
        }
    }

    private fun activateChaosMode() {
        chaosMode = true
        chaosTimer = 5f
        baseSpeed += 100f
    }

    override fun resize(width: Int, height: Int) {
        // Handle screen resize if needed
    }

    override fun pause() {}
    override fun resume() {}
    override fun hide() {}
    override fun dispose() {
        // Clean up if needed
    }
}
