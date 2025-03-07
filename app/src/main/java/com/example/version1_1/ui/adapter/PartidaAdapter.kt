package com.example.version1_1.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.version1_1.data.models.Partida
import com.example.version1_1.databinding.CardItemBinding

class PartidaAdapter(
    private val onEditClick: (Partida) -> Unit,
    private val onDeleteClick: (Partida) -> Unit
) : ListAdapter<Partida, PartidaAdapter.PartidaViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PartidaViewHolder {
        val binding = CardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PartidaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PartidaViewHolder, position: Int) {
        val partida = getItem(position)
        holder.bind(partida)
    }

    inner class PartidaViewHolder(private val binding: CardItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(partida: Partida) {
            binding.nombreTxt.text = partida.nombre
            binding.resultadoTxt.text = "Resultado: ${partida.resultado}"
            binding.estadisticaTxt.text = "Estadística: ${partida.estadistica}"
            binding.fechaTxt.text = "Fecha: ${partida.fecha}"

            binding.editButton.setOnClickListener { onEditClick(partida) }
            binding.deleteButton.setOnClickListener { onDeleteClick(partida) }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Partida>() {
        override fun areItemsTheSame(oldItem: Partida, newItem: Partida): Boolean {
            return oldItem.nombre == newItem.nombre
        }

        override fun areContentsTheSame(oldItem: Partida, newItem: Partida): Boolean {
            return oldItem == newItem
        }
    }
}
