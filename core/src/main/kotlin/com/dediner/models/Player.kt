package com.dediner.models

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Rectangle

class Player(val characterName: String, val texture: Texture) {
    var x: Float = 400f
    var y: Float = 100f
    var width: Float = 64f
    var height: Float = 64f
    var lives: Int = 5
    var score: Int = 0
    var hasShield: Boolean = false
    var doublePoints: Boolean = false
    var magnetActive: Boolean = false
    var powerUpTimer: Float = 0f

    val bounds: Rectangle
        get() = Rectangle(x, y, width, height)

    fun update(delta: Float) {
        if (powerUpTimer > 0) {
            powerUpTimer -= delta
            if (powerUpTimer <= 0) {
                doublePoints = false
                magnetActive = false
            }
        }
    }

    fun activateShield() {
        hasShield = true
    }

    fun activateDoublePoints(duration: Float = 10f) {
        doublePoints = true
        powerUpTimer = duration
    }

    fun activateMagnet(duration: Float = 5f) {
        magnetActive = true
        powerUpTimer = duration
    }

    fun takeDamage(amount: Int = 1): Boolean {
        if (hasShield) {
            hasShield = false
            return false
        }

        lives -= amount
        return lives <= 0
    }

    fun addScore(points: Int) {
        score += if (doublePoints) points * 2 else points
    }
}
