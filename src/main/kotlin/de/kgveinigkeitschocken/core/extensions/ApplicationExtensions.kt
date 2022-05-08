package de.kgveinigkeitschocken.core.extensions

import de.kgveinigkeitschocken.core.exceptions.InitializationException
import io.ktor.server.application.Application

fun Application.getEnvironmentVariable(key: String): String {
    return environment.config.propertyOrNull(key)?.getString() ?: throw InitializationException(
        "Environment variable with key $key not found"
    )
}