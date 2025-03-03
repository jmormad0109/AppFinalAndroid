package com.example.version1_1.data.service

import com.example.version1_1.data.models.AuthResponse
import retrofit2.Call
import com.example.version1_1.data.models.User
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("register")
    fun register(@Body user: User): Call<Void>

    @POST("auth")
    fun login(@Body loginRequest: LoginRequest): Call<AuthResponse>
}

data class LoginRequest(
    val dni: String,
    val password: String
)