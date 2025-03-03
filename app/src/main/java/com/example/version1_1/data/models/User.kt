package com.example.version1_1.data.models

data class User(
    val dni: String,
    val name: String,
    val email: String,
    val password: String,
    val token: String? = null
)

data class AuthResponse(
    val token: String
)