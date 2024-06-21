package com.example.plugins

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import com.example.handlers.*

fun Application.configureRouting() {
    val loginHandler = LoginHandler()
    val userInfoHandler = UserInfoHandler()

    routing {
        post("/login") {
            loginHandler.handle(call)
        }

        get("/userinfo") {
            call.respondText("ok")
            //userInfoHandler.handle(call)
        }
    }
}
