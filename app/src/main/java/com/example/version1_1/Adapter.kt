package com.example.version1_1

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter(private var elementList: MutableList<String>) :
    RecyclerView.Adapter<Adapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val element: TextView = view.findViewById(R.id.elementTextView)
        val deleteButton: ImageButton = view.findViewById(R.id.deleteButton)
        val editButton: ImageButton = view.findViewById(R.id.editButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.element.text = "Elemento ${elementList[position]}"

        // Botón de eliminar
        holder.deleteButton.setOnClickListener {
            val currentPosition = holder.bindingAdapterPosition
            if (currentPosition != RecyclerView.NO_POSITION) {
                elementList.removeAt(currentPosition)
                notifyItemRemoved(currentPosition)
                notifyItemRangeChanged(currentPosition, elementList.size)
            }
        }

        // Botón de editar
        holder.editButton.setOnClickListener {
            val currentPosition = holder.bindingAdapterPosition
            if (currentPosition != RecyclerView.NO_POSITION) {
                val context = holder.itemView.context

                // Crear un cuadro de diálogo para editar
                val editText = EditText(context)
                editText.setText(elementList[currentPosition])
                AlertDialog.Builder(context)
                    .setTitle("Editar elemento")
                    .setView(editText)
                    .setPositiveButton("Guardar") { _, _ ->
                        val newText = editText.text.toString()
                        if (newText.isNotEmpty()) {
                            elementList[currentPosition] = newText
                            notifyItemChanged(currentPosition) // Notificar cambios al adaptador
                        }
                    }
                    .setNegativeButton("Cancelar", null)
                    .show()
            }
        }
    }

    override fun getItemCount(): Int = elementList.size
}
