package com.example.version1_1.data.datasource.memoria.service

import com.example.version1_1.domain.models.Partida

interface PartidaServiceRepository {
    fun getPartidas() : List<Pair<String, Partida>>
    //suspend fun insertBreedEntitytoDatabase(partida : Partida)
}