package com.example.utils

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.example.UserInfoResponse
import java.util.*

object JwtConfig {
    private const val secret = "woke-challenge-woke-challenge"
    private const val issuer = "WokeTech"
    private const val validityInMs = 36_000_00 * 8

    private val algorithm = Algorithm.HMAC512(secret)

    fun generateToken(userInfo: UserInfoResponse): String {
        return JWT.create()
            .withIssuer(issuer)
            .withClaim("email", userInfo.email)
            .withClaim("userId", userInfo.id)
            .withClaim("name", userInfo.name)
            .withClaim("birthDate", userInfo.birthDate)
            .withClaim("phoneNumber", userInfo.phoneNumber)


            .withExpiresAt(Date(System.currentTimeMillis() + validityInMs))
            .sign(algorithm)
    }
}