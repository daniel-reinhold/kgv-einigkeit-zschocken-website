package de.kgveinigkeitschocken.core

import de.kgveinigkeitschocken.db.service.UserService
import org.kodein.di.DI
import org.kodein.di.bindSingleton

val di = DI {
    bindSingleton { UserService() }
}