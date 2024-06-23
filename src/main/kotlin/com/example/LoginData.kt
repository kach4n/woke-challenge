package com.example

import kotlinx.serialization.Serializable

@Serializable
data class LoginData(val email: String, val password: String)

@Serializable
data class LoginResponse(val message: String)

@Serializable
data class UserResponse(val name: String, val phoneNumber: String, val birthDate: String)