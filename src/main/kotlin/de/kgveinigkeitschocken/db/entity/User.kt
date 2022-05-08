package de.kgveinigkeitschocken.db.entity

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object UserTable : IntIdTable(
    name = "users"
) {
    val firstName = varchar("first_name", 128)
    val lastName = varchar("last_name", 128)
    val emailAddress = varchar("email_address", 128)
    val postalCode = varchar("postal_code", 5)
    val city = varchar("city", 128)
    val street = varchar("street", 128)
    val houseNumber = varchar("house_number", 10)
    val password = text("password")
    val isAdmin = bool("is_admin").default(false)
}

class UserEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<UserEntity>(UserTable)

    var firstName by UserTable.firstName
    var lastName by UserTable.lastName
    var emailAddress by UserTable.emailAddress
    var postalCode by UserTable.postalCode
    var city by UserTable.city
    var street by UserTable.street
    var houseNumber by UserTable.houseNumber
    var password by UserTable.password
    var isAdmin by UserTable.isAdmin

    fun fullName(): String {
        return "$firstName $lastName"
    }
}