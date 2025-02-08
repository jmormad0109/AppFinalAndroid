package com.example.version1_1.domain.repository

import com.example.version1_1.domain.models.Partida

interface PartidaRepositoryInterface {
    suspend fun getAll(): List<Partida>
    suspend fun insert(partida: Partida)
    suspend fun editPartida(partida: Partida, nuevaPartida: Partida)
    suspend fun delete(id: Int): Boolean

}