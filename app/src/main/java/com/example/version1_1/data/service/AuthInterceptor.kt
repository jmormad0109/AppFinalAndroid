package com.example.version1_1.data.service

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        RetrofitClient.getToken()?.let { token ->
            request.addHeader("Authorization", "Bearer $token")
        }

        return chain.proceed(request.build())
    }

}