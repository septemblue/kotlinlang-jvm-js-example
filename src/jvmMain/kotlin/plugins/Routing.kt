package plugins

import ShoppingListItem
import Store
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    val shoppingList = mutableListOf(
        ShoppingListItem("Cucumbers 🥒", 1),
        ShoppingListItem("Tomatoes 🍅", 2),
        ShoppingListItem("Orange Juice 🍊", 3)
    )

    routing {
        get("/") {
            call.respondText { "Hello World" }
        }
        /* Routes are grouped based on a common path. You don't have to specify the route path as a String.
        Instead, the path from the ShoppingListItem model is used
        */
        route(ShoppingListItem.path) {
            get {
                call.respond(shoppingList)
            }
            post {
                shoppingList += call.receive<ShoppingListItem>()
                call.respond(HttpStatusCode.OK)
            }
            delete("/{id}") {
                val id = call.parameters["id"]?.toInt() ?: error("Invalid delete request")
                shoppingList.removeIf { it.id == id}
                call.respond(HttpStatusCode.OK)
            }
        }
    }
}