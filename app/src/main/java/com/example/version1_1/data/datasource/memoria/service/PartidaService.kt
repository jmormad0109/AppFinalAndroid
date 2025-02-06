package com.example.version1_1.data.datasource.memoria.service

import androidx.room.Insert
import com.example.version1_1.data.datasource.memoria.models.Partidas
import com.example.version1_1.domain.models.Partida
import kotlinx.coroutines.delay
import javax.inject.Inject

class PartidaService @Inject constructor(): PartidaServiceRepository{
    override fun getPartidas(): List<Pair<String, Partida>> {
        return Partidas.partidas
    }

}