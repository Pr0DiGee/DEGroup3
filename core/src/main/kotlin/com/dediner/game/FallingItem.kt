package com.dediner.game

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2

class FallingItem(val type: ItemType, val tex: Texture, x: Float, y: Float, var speed: Float) {
    val pos = Vector2(x, y)
    val width = 64f
    val height = 64f
    val bounds = Rectangle(pos.x, pos.y, width, height)

    fun update(delta: Float) {
        pos.y -= speed * delta
        bounds.setPosition(pos.x, pos.y)
    }
}
