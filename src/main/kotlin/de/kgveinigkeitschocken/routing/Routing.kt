package de.kgveinigkeitschocken.routing

import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.http.content.files
import io.ktor.server.http.content.static
import io.ktor.server.http.content.staticRootFolder
import io.ktor.server.pebble.PebbleContent
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import java.io.File

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respond(
                PebbleContent("index.peb", mapOf())
            )
        }

        userRoute()

        static("assets") {
            staticRootFolder = File("assets")
            files(".")
        }
    }
}