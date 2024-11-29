package com.example.version1_1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val elementList = mutableListOf<String>()
        for (i in 1..15) {
            elementList.add("$i")
        }

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val adapter = Adapter(elementList)
        recyclerView.adapter = adapter
    }
}
