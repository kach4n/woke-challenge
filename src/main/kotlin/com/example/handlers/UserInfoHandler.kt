package com.example.handlers

import io.ktor.server.application.*
import io.ktor.server.response.*

class UserInfoHandler {
    suspend fun handle(call: ApplicationCall) {
        val response = mapOf("message" to "Hello, JSON!", "status" to "success")
        call.respond(response)
    }
}