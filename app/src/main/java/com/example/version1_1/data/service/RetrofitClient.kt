package com.example.version1_1.data.service

import android.content.Context
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "http://10.0.2.2:8081/"
    private lateinit var appContext: Context

    // Inicialización del contexto para obtener el token
    fun init(context: Context) {
        appContext = context.applicationContext
    }

    // Obtener el token guardado en SharedPreferences
    fun getToken(): String? {
        val sharedPreferences = appContext.getSharedPreferences("login-info", Context.MODE_PRIVATE)
        return sharedPreferences.getString("jwt_token", null)
    }

    // Interceptor para agregar el token a cada petición
    private val authInterceptor = Interceptor { chain ->
        val request = chain.request().newBuilder()
        getToken()?.let { token ->
            request.addHeader("Authorization", "Bearer $token")
        }
        chain.proceed(request.build())
    }

    // Configuración del cliente HTTP con logging y autenticación
    private val client = OkHttpClient.Builder()
        .addInterceptor(authInterceptor) // Agrega el token en cada petición
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    // Crear Retrofit
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    // Servicios de Retrofit
    val authService: AuthService by lazy {
        retrofit.create(AuthService::class.java)
    }

    val partidaService: PartidaApi by lazy {
        retrofit.create(PartidaApi::class.java)
    }
}
