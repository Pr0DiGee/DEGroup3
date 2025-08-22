package com.dediner.desktop

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration
import com.dediner.DE_Diner

object DesktopLauncher {
    @JvmStatic
    fun main(arg: Array<String>) {
        val config = Lwjgl3ApplicationConfiguration()
        config.setForegroundFPS(60)
        config.setTitle("DE Diner")
        config.setWindowedMode(800, 600)
        config.setResizable(false)
        Lwjgl3Application(DE_Diner(), config)
    }
}
