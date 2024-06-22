package com.example.handlers

import com.example.DatabaseConfig
import com.example.LoginData
import com.example.LoginResponse
import com.example.User
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import io.ktor.http.*
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

        return transaction {
                User.select {
                    (User.email eq email) and (User.password eq password)
                }.count() > 0
            }

    }
}