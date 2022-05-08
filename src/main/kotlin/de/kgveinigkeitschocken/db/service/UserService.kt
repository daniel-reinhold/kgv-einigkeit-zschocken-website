package de.kgveinigkeitschocken.db.service

import de.kgveinigkeitschocken.core.exceptions.EntityNotFoundException
import de.kgveinigkeitschocken.core.helper.PasswordHelper
import de.kgveinigkeitschocken.db.entity.UserEntity
import org.jetbrains.exposed.sql.transactions.transaction

class UserService {

    fun getAllUsers(): List<UserEntity> = transaction {
        UserEntity.all().toList()
    }

    fun findUser(id: Int): UserEntity = transaction {
        UserEntity.findById(id) ?: throw EntityNotFoundException()
    }

    fun createUser(
        firstName: String,
        lastName: String,
        emailAddress: String,
        postalCode: String,
        city: String,
        street: String,
        houseNumber: String,
        password: String,
        isAdmin: Boolean = false
    ): UserEntity = transaction {
        UserEntity.new {
            this.firstName = firstName
            this.lastName = lastName
            this.emailAddress = emailAddress
            this.postalCode = postalCode
            this.city = city
            this.street = street
            this.houseNumber = houseNumber
            this.password = PasswordHelper.generatePasswordHash(password)
            this.isAdmin = isAdmin
        }
    }

    fun updateUser(
        id: Int,
        firstName: String? = null,
        lastName: String? = null,
        postalCode: String? = null,
        city: String? = null,
        street: String? = null,
        houseNumber: String? = null
    ): UserEntity = transaction {
        findUser(id).apply {
            firstName?.let { this.firstName = it }
            lastName?.let { this.lastName = it }
            postalCode?.let { this.postalCode = it }
            city?.let { this.city = it }
            street?.let { this.street = it }
            houseNumber?.let { this.houseNumber = it }
        }
    }

    fun deleteUser(id: Int): UserEntity = transaction {
        findUser(id).apply {
            delete()
        }
    }

}