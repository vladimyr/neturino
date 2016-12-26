package sample

import javafx.application.Application
import javafx.geometry.HPos
import javafx.geometry.VPos
import javafx.scene.Scene
import javafx.scene.layout.Region
import javafx.scene.paint.Color
import javafx.scene.web.WebView
import javafx.stage.Stage

const val WIDTH = 750.0
const val HEIGHT = 500.0

class Main : Application() {
    override fun start(stage: Stage) {
        stage.title = "Neutrino"
        stage.scene = Scene(Browser(), WIDTH, HEIGHT, Color.web("#666970"))
        stage.show()
    }
}

class Browser() : Region() {
    private val browser: WebView

    init {
        browser = WebView()
        val url = javaClass.classLoader.getResource("index.html")
        browser.engine.load(url.toExternalForm())
        children.add(browser)
    }

    override fun layoutChildren() {
        layoutInArea(browser, 0.0, 0.0, width, height,
                0.0, HPos.CENTER, VPos.CENTER)
    }

    override fun computePrefWidth(height: Double): Double {
        return WIDTH
    }

    override fun computePrefHeight(width: Double): Double {
        return HEIGHT
    }
}

fun main(args: Array<String>) {
    Application.launch(Main::class.java, *args)
}