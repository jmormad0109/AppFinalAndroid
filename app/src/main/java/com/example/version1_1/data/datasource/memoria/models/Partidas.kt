package com.example.version1_1.data.datasource.memoria.models

import com.example.version1_1.domain.models.Partida

object Partidas {
    var partidas : List<Pair<String, Partida>> = listOf(
        "Jugador 1" to Partida(1, "Ganada", "84% de acierto", "2024-10-21"),
        "Jugador 2" to Partida(2, "Perdida", "63% de acierto", "2024-08-14"),
        "Jugador 1" to Partida(3, "Perdida", "57% de acierto", "2025-01-09"),
        "Jugador 3" to Partida(4, "Empate", "68% de acierto", "2024-11-27")
    )
}