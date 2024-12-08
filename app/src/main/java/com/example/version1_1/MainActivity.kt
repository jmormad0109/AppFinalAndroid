package com.example.version1_1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import android.app.AlertDialog
import android.widget.Button
import android.widget.EditText
import com.google.android.material.floatingactionbutton.FloatingActionButton

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

        val addButton: Button = findViewById(R.id.addButton)

        // Evento para añadir un elemento
        addButton.setOnClickListener {
            val editText = EditText(this)
            editText.hint = "Escribe un nuevo elemento"

            AlertDialog.Builder(this)
                .setTitle("Agregar nuevo elemento")
                .setView(editText)
                .setPositiveButton("Agregar") { _, _ ->
                    val newItem = editText.text.toString()
                    if (newItem.isNotEmpty()) {
                        elementList.add(newItem) // Añadir al final de la lista
                        adapter.notifyItemInserted(elementList.size - 1) // Notificar al adaptador

                        // Desplazar el scroll al último elemento
                        recyclerView.scrollToPosition(elementList.size - 1)
                    }
                }
                .setNegativeButton("Cancelar", null)
                .show()
        }
    }
}
