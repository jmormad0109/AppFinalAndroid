package com.example.version1_1.data.service


import com.example.version1_1.data.datasource.PartidasData
import com.example.version1_1.data.models.Partida

class PartidaService{

    private val data = PartidasData()

    suspend fun  getPartidas(): List<Partida> {
        return data.getPartidas()
    }

    suspend fun insertPartidas(partida: Partida){
        data.insertPartida(partida)
    }

    suspend fun editPartida(partida: Partida, nuevaPartida: Partida){
        data.editPartida(partida, nuevaPartida)
    }

    suspend fun deletePartida(idPartida: Int){
        data.deletePartida(idPartida)
    }

}