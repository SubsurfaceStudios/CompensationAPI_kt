import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.http.cio.websocket.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.*
import io.ktor.server.engine.*
import io.ktor.server.tomcat.*
import kotlinx.serialization.*
import tk.compensationvr.api.auth
import tk.compensationvr.api.items.ClothingItem
import tk.compensationvr.api.serializables.Outfit
import tk.compensationvr.api.serializables.Version
import tk.compensationvr.api.db;




fun main() {

    embeddedServer(Tomcat, 8080) {
        routing {
            install(ContentNegotiation) {
                json()
            }

            get("/") {
                call.respond(Version("2.0.0-BETA"))
            }




            // account
            post("/account/login") {

            }
            get("/account/{username}/catalog") {
                call.respond("all items")
                return@get
//                call.respond(db.account.catalog(username))
                // Outfit
            }
            get("/account/{username}") {
                if (auth.authenticatedAs(call.request, call.parameters["username"]!!)) {
                    call.respond(db.account.get(call.parameters["username"]!!, true) as db.account.AccountDataStructures.PrivateUserInformationPayload)
                } else {
                    call.respond(db.account.get(call.parameters["username"]!!, false) as db.account.AccountDataStructures.UserInformationPayload)
                }
            }




            // items
            get("/items/all/info") {
                call.respond("all items")
                return@get
                call.respond(db.items.all())
            }
            get("/items/{id}/info") {
                val t = ClothingItem(call.parameters["id"]!!)
                call.respond(t.serialize())
            }



        }

    }.start(wait = true)

}