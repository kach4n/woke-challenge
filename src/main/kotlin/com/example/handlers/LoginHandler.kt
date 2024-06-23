package com.example.handlers

import com.example.DatabaseConfig
import com.example.LoginData
import com.example.LoginResponse
import com.example.User
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import io.ktor.http.*
import com.example.utils.JwtConfig
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.selectAll

class LoginHandler {

    suspend fun handle(call: ApplicationCall) {
        DatabaseConfig.connect()

        val parameters = call.receive<LoginData>()
        println("Received login data: $parameters")
        val email = parameters.email
        val password = parameters.password

        val userExists = transaction {
            User.select {
                (User.email eq email) and (User.password eq password)
            }.count() > 0
        }

        if (userExists) {
            val token = JwtConfig.generateToken(email)
            call.response.cookies.append(
                Cookie(
                    name = "auth_token",
                    value = token,
                    httpOnly = true,
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