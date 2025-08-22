package com.dediner.models

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2

sealed class Item(val texture: Texture, val type: String) {
    abstract val bounds: Rectangle
    abstract fun update(delta: Float)
    abstract val isOffScreen: Boolean
}

class FruitItem(
    texture: Texture,
    type: String,
    val effect: String,
    initialPosition: Vector2,
    var speed: Float = 100f
) : Item(texture, type) {
    var position: Vector2 = initialPosition.cpy()
    var width: Float = 64f
    var height: Float = 64f

    override val bounds: Rectangle
        get() = Rectangle(position.x, position.y, width, height)

    override fun update(delta: Float) {
        position.y -= speed * delta
    }

    override val isOffScreen: Boolean
        get() = position.y + height < 0
}

class BadFoodItem(
    texture: Texture,
    type: String,
    val damage: Int,
    initialPosition: Vector2,
    var speed: Float = 100f
) : Item(texture, type) {
    var position: Vector2 = initialPosition.cpy()
    var width: Float = 64f
    var height: Float = 64f

    override val bounds: Rectangle
        get() = Rectangle(position.x, position.y, width, height)

    override fun update(delta: Float) {
        position.y -= speed * delta
    }

    override val isOffScreen: Boolean
        get() = position.y + height < 0
}
