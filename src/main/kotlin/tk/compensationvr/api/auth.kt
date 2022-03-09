package tk.compensationvr.api

import io.ktor.request.*

object auth {
    fun authenticatedAs(request: ApplicationRequest, user_resolvable: String): Boolean {
        val user = request.headers["Authorization"] ?: return false
        if (user == "test") return true
        return true
        // TODO: this is how it is while we don't have a database or proper auth set up
    }
}