package de.kgveinigkeitschocken

import de.kgveinigkeitschocken.db.initDB
import io.ktor.server.application.*
import de.kgveinigkeitschocken.plugins.*
import de.kgveinigkeitschocken.routing.configureRouting
import io.ktor.server.resources.Resources

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
    install(Resources)

    initDB()
    configureRouting()
    configureTemplating()
    configureMonitoring()
}
