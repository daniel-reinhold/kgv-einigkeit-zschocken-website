package de.kgveinigkeitschocken.db

import de.kgveinigkeitschocken.core.extensions.getEnvironmentVariable
import de.kgveinigkeitschocken.core.helper.PasswordHelper
import de.kgveinigkeitschocken.db.entity.UserEntity
import de.kgveinigkeitschocken.db.entity.UserTable
import io.ktor.server.application.Application
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.initDB() {
    val dbHost = getEnvironmentVariable("ktor.db.host")
    val dbPort = getEnvironmentVariable("ktor.db.port")
    val dbUser = getEnvironmentVariable("ktor.db.user")
    val dbPassword = getEnvironmentVariable("ktor.db.password")
    val dbName = getEnvironmentVariable("ktor.db.name")

    Database.connect(
        url = "jdbc:postgresql://$dbHost:$dbPort/$dbName",
        driver = "org.postgresql.Driver",
        user = dbUser,
        password = dbPassword
    )

    transaction {
        SchemaUtils.create(
            UserTable
        )
    }

    transaction {
        if (UserEntity.find { UserTable.isAdmin eq true }.count() == 0L) {
            UserEntity.new {
                firstName = getEnvironmentVariable("ktor.super_admin.first_name")
                lastName = getEnvironmentVariable("ktor.super_admin.last_name")
                emailAddress = getEnvironmentVariable("ktor.super_admin.email_address")
                postalCode = getEnvironmentVariable("ktor.super_admin.postal_code")
                city = getEnvironmentVariable("ktor.super_admin.city")
                street = getEnvironmentVariable("ktor.super_admin.street")
                houseNumber = getEnvironmentVariable("ktor.super_admin.house_number")
                password = PasswordHelper.generatePasswordHash(
                    getEnvironmentVariable("ktor.super_admin.password")
                )
                isAdmin = true
            }
        }
    }
}