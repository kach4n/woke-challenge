package com.example

import com.example.plugins.*
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.http.HttpHeaders
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*


fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

@Suppress("unused")
fun Application.module() {
    DatabaseConfig.connect()

    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            isLenient = true
        })
    }
    install(CORS){
        anyHost()
        allowHeader(HttpHeaders.ContentType)
        allowMethod(HttpMethod.Options)
        allowHeader(HttpHeaders.Authorization)
        allowMethod(HttpMethod.Post)

        allowCredentials = true
    }
    install(Authentication) {
        jwt("auth-jwt") {
            verifier(JWT
                .require(Algorithm.HMAC512("woke-challenge-woke-challenge"))
                .build())

            validate { credential ->
                val email = credential.payload.getClaim("email").asString()
                if (email != null) {
                    JWTPrincipal(credential.payload)
                } else {
                    null
                }
            }
        }
    }

    configureRouting()
}
