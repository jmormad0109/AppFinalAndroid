package com.example.version1_1.ui.views.fragment

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.version1_1.Adapter
import com.example.version1_1.R

class ListFragment: Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: Adapter
    private lateinit var addButton: Button
    private val elementList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Infla el layout del fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        // Inicializa la lista con elementos
        for (i in 1..15) {
            elementList.add("$i")
        }

        // Configura el RecyclerView
        recyclerView = view.findViewById(R.id.recyclerView)
        adapter = Adapter(elementList)
        recyclerView.adapter = adapter

        // Configura el botón para añadir elementos
        addButton = view.findViewById(R.id.addButton)
        addButton.setOnClickListener {
            showAddItemDialog()
        }

        return view
    }

    private fun showAddItemDialog() {
        // Infla el layout del diálogo
        val dialogView = layoutInflater.inflate(R.layout.dialog_item, null)
        val editText = dialogView.findViewById<EditText>(R.id.editTextNewItem)
        val saveButton = dialogView.findViewById<Button>(R.id.saveButton)
        val cancelButton = dialogView.findViewById<Button>(R.id.cancelButton)

        // Crea el diálogo
        val dialog = AlertDialog.Builder(requireContext())
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
                Toast.makeText(requireContext(), "El elemento no puede estar vacío", Toast.LENGTH_SHORT).show()
            }
        }

        cancelButton.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

}