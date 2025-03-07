package com.example.version1_1

import android.app.Application
import com.example.version1_1.data.service.RetrofitClient

class MyApp: Application() {
    override fun onCreate() {
        super.onCreate()
        RetrofitClient.init(this)
    }
}