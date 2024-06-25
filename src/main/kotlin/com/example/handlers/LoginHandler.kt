package com.example.handlers

import com.example.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import io.ktor.http.*
import com.example.utils.JwtConfig
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.and

class LoginHandler {

    suspend fun handle(call: ApplicationCall) {
        DatabaseConfig.connect()

        val parameters = call.receive<LoginData>()
        val email = parameters.email
        val password = parameters.password

        val user = transaction {
            User.slice(
                User.id,
                User.name,
                User.email,
                User.phone_number,
                User.birth_date).select {
                (User.email eq email) and (User.password eq password)
            }.map {
                UserInfoResponse(
                    it[User.id],
                    it[User.name],
                    it[User.email],
                    it[User.phone_number],
                    it[User.birth_date]
                )
            }.singleOrNull()
        }

        if (user != null) {
            val token = JwtConfig.generateToken(user)
            call.response.cookies.append(
                Cookie(
                    name = "auth_token",
                    value = token,
                    httpOnly = false,
                    secure = false,
                    path = "/"
                )
            )
            call.respond(HttpStatusCode.OK, LoginResponse("Login efetuado com sucesso"))
        } else {
            call.respond(HttpStatusCode.Unauthorized, LoginResponse("Email ou senha incorretos"))
        }
    }
}