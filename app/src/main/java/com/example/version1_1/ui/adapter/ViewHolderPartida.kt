package com.example.version1_1.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.version1_1.databinding.CardItemBinding
import com.example.version1_1.domain.models.Partida

class ViewHolderPartida(view: View) : RecyclerView.ViewHolder(view) {
    private lateinit var binding: CardItemBinding


    init{
        binding = CardItemBinding.bind(view)
    }

}