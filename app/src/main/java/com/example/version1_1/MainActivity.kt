package com.example.version1_1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import android.app.AlertDialog
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

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

        addButton.setOnClickListener {

            val dialogView = layoutInflater.inflate(R.layout.dialog_item, null)
            val editText = dialogView.findViewById<EditText>(R.id.editTextNewItem)
            val saveButton = dialogView.findViewById<Button>(R.id.saveButton)
            val cancelButton = dialogView.findViewById<Button>(R.id.cancelButton)


            val dialog = AlertDialog.Builder(this)
                .setView(dialogView)
                .create()


            saveButton.setOnClickListener {
                val newItem = editText.text.toString()
                if (newItem.isNotEmpty()) {
                    elementList.add(newItem)
                    adapter.notifyItemInserted(elementList.size - 1)
                    recyclerView.scrollToPosition(elementList.size - 1)
                    dialog.dismiss()
                } else {
                    Toast.makeText(this, "El elemento no puede estar vacío", Toast.LENGTH_SHORT).show()
                }
            }

            cancelButton.setOnClickListener {
                dialog.dismiss()
            }

            dialog.show()
        }


    }
}
