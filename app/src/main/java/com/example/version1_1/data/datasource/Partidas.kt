package com.example.version1_1.data.datasource

import com.example.version1_1.data.models.Partida

class PartidasData {

    private val partidasList = mutableListOf<Partida>(
        Partida(1, "Ganada", "74%", "12/06/2024"),
        Partida(2, "Perdida", "54%", "17/06/2024"),
        Partida(3, "Perdida", "43%", "02/07/2024"),
        Partida(4, "Ganada", "69%", "23/07/2024"),
        Partida(5, "Ganada", "80%", "08/08/2024"),
        Partida(6, "Empatada", "57%", "13/01/2025"),
        Partida(7, "Ganada", "78%", "16/01/2025"),
        Partida(8, "Perdida", "38%", "01/09/2024"),
        Partida(9, "Empatada", "60%", "06/09/2024"),
        Partida(10, "Perdida", "58%", "03/10/2024"),
    )



    fun getPartidas(): List<Partida>{
        return partidasList
    }

    fun insertPartida(partida: Partida){
        partidasList.add(partida)
    }

    fun editPartida(partida: Partida, nuevaPartida: Partida){
        val indice = partidasList.indexOfFirst { partida.id == nuevaPartida.id }

        if (indice != -1){
            partidasList[indice] = nuevaPartida
        }
    }
    fun deletePartida(idPartida: Int){
        partidasList.removeAt(idPartida)
    }
}