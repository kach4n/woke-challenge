package com.example.handlers

import com.example.LoginData
import com.example.LoginResponse
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import io.ktor.http.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString

class LoginHandler {
    suspend fun handle(call: ApplicationCall) {
        val parameters = call.receive<LoginData>()
        println("Received login data: $parameters")
        val username = parameters.username
        val password = parameters.password

        // Placeholder logic for authentication
        if (username == "user" && password == "password") {
            val response = LoginResponse("Login successful")
            call.respondText(Json.encodeToString(response), ContentType.Application.Json)
        } else {
            val response = LoginResponse("Invalid credentials")
            call.respondText(Json.encodeToString(response), ContentType.Application.Json)
        }
    }
}