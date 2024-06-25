package com.example.plugins

import io.ktor.server.application.*
import io.ktor.server.routing.*
import com.example.handlers.*
import io.ktor.server.auth.*

fun Application.configureRouting() {
    val loginHandler = LoginHandler()
    val userInfoHandler = UserInfoHandler()

    routing {
        post("/login") {
            loginHandler.handle(call)
        }

        authenticate("auth-jwt") {
            get("/userinfo") {
                userInfoHandler.handle(call)
            }
        }
    }
}
