package com.example.version1_1.domain.repository

import com.example.version1_1.data.datasource.database.entities.PartidaEntity
import com.example.version1_1.domain.models.Partida

interface PartidaRepositoryInterface {

    fun getPartidas() : List<Partida>


    suspend fun getPartidasEntity() : List<Partida>

    suspend fun insertPartida(partidaEntity: PartidaEntity) : Partida

    suspend fun insertPartidas(listPartidasEntity: List<PartidaEntity>)

    suspend fun deletePartida(partidaEntity: PartidaEntity)

}