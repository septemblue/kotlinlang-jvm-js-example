import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.routing.*
import plugins.configureRouting

fun main() {
    embeddedServer(Netty, port = 8080, host = "localhost", module = Application::module).start(wait = true)
}

fun Application.module() {
    configureRouting()
}