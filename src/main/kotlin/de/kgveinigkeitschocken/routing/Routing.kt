package de.kgveinigkeitschocken.routing

import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.pebble.PebbleContent
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.routing

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respond(
                PebbleContent("index.html", mapOf())
            )
        }

        userRoute()
    }
}