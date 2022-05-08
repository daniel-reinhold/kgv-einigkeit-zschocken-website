package de.kgveinigkeitschocken.db

import de.kgveinigkeitschocken.core.extensions.getEnvironmentVariable
import io.ktor.server.application.Application
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.initDB() {
    val host = getEnvironmentVariable("ktor.db.host")
    val port = getEnvironmentVariable("ktor.db.port")
    val user = getEnvironmentVariable("ktor.db.user")
    val password = getEnvironmentVariable("ktor.db.password")
    val name = getEnvironmentVariable("ktor.db.name")

    Database.connect(
        url = "jdbc:postgresql://$host:$port/$name",
        driver = "org.postgresql.driver",
        user = user,
        password = password
    )

    transaction {
        // TODO: Create schemas
    }

    transaction {
        // TODO: Create Superuser if not already exists
    }
}