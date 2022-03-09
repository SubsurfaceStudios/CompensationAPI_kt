import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.http.cio.websocket.*
import io.ktor.http.content.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.*
import io.ktor.server.engine.*
import io.ktor.server.tomcat.*
import kotlinx.serialization.*
import tk.compensationvr.api.auth
import tk.compensationvr.api.serializables.Outfit
import tk.compensationvr.api.serializables.Version
import tk.compensationvr.api.db;
import java.io.File


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
            }

// THIS **STAYS**
//            post("/run_asm") {
//                call.receiveMultipart().let {
//                    val file = it.readAllParts().first { it is PartData.FileItem } as PartData.FileItem
//                    val bytes = file.streamProvider().readBytes()
//                    val out = File.createTempFile("asm", ".asm")
//                    out.writeBytes(bytes)
//                    out.deleteOnExit()
//                    val p = ProcessBuilder("/usr/bin/nasm", "-f", "bin", out.absolutePath).start()
//                    p.waitFor()
//                    val out2 = File.createTempFile("asm", ".bin")
//                    out2.writeBytes(p.inputStream.readBytes())
//                    out2.deleteOnExit()
//                    call.respond(out2.readBytes())
//
//                }
//            }

        }

    }.start(wait = true)

}