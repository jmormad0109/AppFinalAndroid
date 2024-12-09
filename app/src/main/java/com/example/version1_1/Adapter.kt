package com.example.version1_1

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
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

        holder.deleteButton.setOnClickListener {
            val currentPosition = holder.bindingAdapterPosition
            if (currentPosition != RecyclerView.NO_POSITION) {
                elementList.removeAt(currentPosition)
                notifyItemRemoved(currentPosition)
                notifyItemRangeChanged(currentPosition, elementList.size)
            }
        }

        holder.editButton.setOnClickListener {
            val currentPosition = holder.bindingAdapterPosition
            if (currentPosition != RecyclerView.NO_POSITION) {
                val context = holder.itemView.context


                val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_item, null)
                val editText = dialogView.findViewById<EditText>(R.id.editTextNewItem)
                val saveButton = dialogView.findViewById<Button>(R.id.saveButton)
                val cancelButton = dialogView.findViewById<Button>(R.id.cancelButton)


                editText.setText(elementList[currentPosition])


                val dialog = AlertDialog.Builder(context)
                    .setView(dialogView)
                    .create()


                saveButton.setOnClickListener {
                    val updatedText = editText.text.toString()
                    if (updatedText.isNotEmpty()) {
                        elementList[currentPosition] = updatedText
                        notifyItemChanged(currentPosition)
                        dialog.dismiss()
                    } else {
                        Toast.makeText(context, "El elemento no puede estar vacío", Toast.LENGTH_SHORT).show()
                    }
                }

                cancelButton.setOnClickListener {
                    dialog.dismiss()
                }

                dialog.show()
            }
        }

    }
    override fun getItemCount(): Int = elementList.size
    }



