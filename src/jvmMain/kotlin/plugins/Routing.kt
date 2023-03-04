package plugins

import ShoppingListItem
import Store
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    val shoppingList = Store().shoppingList
    shoppingList.addAll(
        listOf(
            ShoppingListItem("Cucumbers 🥒", 1),
            ShoppingListItem("Tomatoes 🍅", 2),
            ShoppingListItem("Orange Juice 🍊", 3)
        )
    )
    routing {
        get("/") {
            call.respondText { "Hello World" }
        }
        route(ShoppingListItem.path) {
            get {
                call.respond(shoppingList)
            }
        }
    }
}