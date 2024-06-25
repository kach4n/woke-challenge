package com.example.handlers

import com.example.UserInfoResponse
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.http.HttpStatusCode

class UserInfoHandler {
    suspend fun handle(call: ApplicationCall) {
        val principal = call.authentication.principal<JWTPrincipal>()

        if (principal != null) {
            val userId = principal.payload.getClaim("userId").asInt()
            val name = principal.payload.getClaim("name").asString()
            val email = principal.payload.getClaim("email").asString()
            val phoneNumber = principal.payload.getClaim("phoneNumber").asString()
            val birthDate = principal.payload.getClaim("birthDate").asString()

            val response = UserInfoResponse(
                id = userId,
                name = name,
                email = email,
                phoneNumber = phoneNumber,
                birthDate = birthDate
            )

            call.respond(response)
        } else {
            call.respond(HttpStatusCode.Unauthorized, "Unauthorized")
        }
    }
}