package com.example.version1_1.data.repository

import com.example.version1_1.data.service.PartidaService
import com.example.version1_1.domain.models.Partida
import com.example.version1_1.domain.repository.PartidaRepositoryInterface

class PartidaRepository (
    private val service: PartidaService = PartidaService()
) : PartidaRepositoryInterface {
    override suspend fun getAll(): List<Partida> {
        val partidas = service.getPartidas()
        return partidas.map { partida -> Partida(
            partida.id,
            partida.resultado,
            partida.estadistica,
            partida.fecha
        )
        }
    }

    override suspend fun insert(partida: Partida) {

        val newPartida = com.example.version1_1.data.models.Partida(
            partida.id,
            partida.resultado,
            partida.estadistica,
            partida.fecha
        )

        service.insertPartidas(newPartida)
    }

    override suspend fun editPartida(partida: Partida, nuevaPartida: Partida) {
        val partida = com.example.version1_1.data.models.Partida(
            partida.id,
            partida.resultado,
            partida.estadistica,
            partida.fecha
        )

        val nuevaPartida = com.example.version1_1.data.models.Partida(
            nuevaPartida.id,
            nuevaPartida.resultado,
            nuevaPartida.estadistica,
            nuevaPartida.fecha

        )

        service.editPartida(partida, nuevaPartida)
    }

    override suspend fun delete(id: Int): Boolean {
        service.deletePartida(id)
        return true
    }


}