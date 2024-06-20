package com.example.handlers

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import io.ktor.http.*

class LoginHandler {
    suspend fun handle(call: ApplicationCall) {
        val parameters = call.receiveParameters()
        val username = parameters["username"] ?: return call.respondText("Missing username", status = HttpStatusCode.BadRequest)
        val password = parameters["password"] ?: return call.respondText("Missing password", status = HttpStatusCode.BadRequest)

        // Placeholder logic for authentication
        if (username == "user" && password == "password") {
            call.respondText("Login successful")
        } else {
            call.respondText("Invalid credentials", status = HttpStatusCode.Unauthorized)
        }
    }
}