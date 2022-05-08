package de.kgveinigkeitschocken.routing

import de.kgveinigkeitschocken.core.di
import de.kgveinigkeitschocken.db.service.UserService
import io.ktor.resources.Resource
import io.ktor.server.application.call
import io.ktor.server.pebble.PebbleContent
import io.ktor.server.resources.get
import io.ktor.server.resources.post
import io.ktor.server.resources.put
import io.ktor.server.resources.delete
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import kotlinx.serialization.Serializable
import org.kodein.di.instance

@Serializable
@Resource("/users")
private class UserRouting {

    @Serializable
    @Resource("create")
    class Create(val parent: UserRouting = UserRouting())

    @Serializable
    @Resource("{id}")
    class Id(val parent: UserRouting = UserRouting(), val id: Int) {

        @Serializable
        @Resource("update")
        class Update(val parent: Id)

        @Serializable
        @Resource("delete")
        class Delete(val parent: Id)

    }
}

fun Route.userRoute() {
    val userService: UserService by di.instance()

    get<UserRouting> {
        val users = userService.getAllUsers()
        call.respond(
            PebbleContent(
                template = "user/index.peb",
                model = mapOf(
                    "users" to users
                )
            )
        )
    }

    get<UserRouting.Id> { resource ->
        // TODO: Show UI for creating new User
        call.respond(
            PebbleContent(
                template = "user/show.peb",
                model = mapOf(
                    "userId" to resource.id
                )
            )
        )
    }

    get<UserRouting.Create> {
        // TODO: Show UI for creating new User
        call.respond(
            PebbleContent("user/new.peb", mapOf())
        )
    }

    post<UserRouting.Create> {
        // TODO: Create user
    }

    get<UserRouting.Id.Update> { resource ->
        // TODO: Show UI for editing user
        call.respond(
            PebbleContent(
                template = "user/edit.peb",
                model = mapOf(
                    "userId" to resource.parent.id
                )
            )
        )
    }

    put<UserRouting.Id.Update> {
        // TODO: Update user
    }

    delete<UserRouting.Id.Delete> {
        // TODO: Delete user
    }
}