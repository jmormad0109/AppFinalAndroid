package com.example.version1_1.domain.mapper

import com.example.version1_1.data.datasource.database.entities.PartidaEntity
import com.example.version1_1.domain.models.Partida

fun PartidaEntity.toPartida(): Partida {
    return Partida(id = this.id, resultado = this.resultado, estadistica = this.estadistica, fecha = this.fecha)
}

fun Partida.toPartidaEntity(): PartidaEntity{
    return PartidaEntity(id = id, resultado = resultado, estadistica = estadistica, fecha = fecha)
}

fun Pair<String, Partida>.toPartida(): Partida {
    return Partida(id = second.id, resultado = second.resultado, estadistica = second.estadistica, fecha = second.fecha)
}