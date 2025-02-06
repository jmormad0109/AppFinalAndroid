package com.example.version1_1.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.version1_1.R
import com.example.version1_1.domain.models.Partida
import com.example.version1_1.domain.models.PartidasData

class AdapterPartida : RecyclerView.Adapter<ViewHolderPartida>() {

    var partidaRepository: List<Partida> = PartidasData.partidas

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderPartida {
        val layoutInflater = LayoutInflater.from(parent.context)
        val layoutCardItem = R.layout.card_item

        return ViewHolderPartida(
            layoutInflater.inflate(layoutCardItem, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return partidaRepository.size
    }

    override fun onBindViewHolder(holder: ViewHolderPartida, position: Int) {

    }
}