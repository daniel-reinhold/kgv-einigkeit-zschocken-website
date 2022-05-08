package de.kgveinigkeitschocken.core.helper

import at.favre.lib.crypto.bcrypt.BCrypt

object PasswordHelper {

    private const val HASH_COST = 12

    fun generatePasswordHash(password: String): String {
        return BCrypt.withDefaults().hashToString(HASH_COST, password.toCharArray())
    }

    @Suppress("unused")
    fun matches(password: String, hash: String): Boolean {
        return BCrypt.verifyer().verify(
            password.toCharArray(),
            hash.toCharArray()
        ).verified
    }

}