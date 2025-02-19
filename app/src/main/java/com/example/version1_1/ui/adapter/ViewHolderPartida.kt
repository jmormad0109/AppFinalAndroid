package com.example.version1_1.ui.adapter

import android.net.Uri
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.version1_1.R
import com.example.version1_1.databinding.CardItemBinding
import com.example.version1_1.domain.models.Partida

class ViewHolderPartida(view: View) : RecyclerView.ViewHolder(view) {
    private val binding: CardItemBinding = CardItemBinding.bind(view)


    fun rendereize(partida: Partida) {
        binding.resultadoTxt.text = partida.resultado
        binding.estadisticaTxt.text = partida.estadistica
        binding.fechaTxt.text = partida.fecha

        if (!partida.fotoUri.isNullOrEmpty()) {
            Glide.with(itemView.context)
                .load(partida.fotoUri)
                .centerCrop()
                .into(binding.imagenCard)
        } else {
            Glide.with(itemView.context)
                .load(R.drawable.mirage)
                .centerCrop()
                .into(binding.imagenCard)
        }
    }

}