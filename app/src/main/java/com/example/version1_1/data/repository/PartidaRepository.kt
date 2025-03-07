package com.example.version1_1.data.repository

import com.example.version1_1.data.models.Partida
import com.example.version1_1.data.service.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class PartidaRepository {

    private val api = RetrofitClient.partidaService

    suspend fun getPartidas(): List<Partida>? {
        return withContext(Dispatchers.IO) {
            val response = api.getPartidas()
            if (response.isSuccessful) response.body() else null
        }
    }

    suspend fun addPartida(partida: Partida): Partida? {
        return withContext(Dispatchers.IO) {
            val response = api.addPartida(partida)
            if (response.isSuccessful) response.body() else null
        }
    }

    suspend fun updatePartida(nombrePartida: String, partida: Partida): Partida? {
        val partidaCorregida = partida.copy(resultado = partida.resultado.uppercase()) // 🔥 Aquí convertimos a mayúsculas
        return withContext(Dispatchers.IO) {
            val response = api.updatePartida(nombrePartida, partidaCorregida)
            if (response.isSuccessful) response.body() else null
        }
    }


    suspend fun deletePartida(nombrePartida: String): Boolean {
        return withContext(Dispatchers.IO) {
            val response = api.deletePartida(nombrePartida)
            response.isSuccessful
        }
    }
}
