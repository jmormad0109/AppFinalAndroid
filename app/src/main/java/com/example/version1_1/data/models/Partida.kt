package com.example.version1_1.data.models

import com.google.gson.annotations.SerializedName

data class Partida(
    @SerializedName("nombrePartida") val nombre: String,
    @SerializedName("resultado") val resultado: String,
    @SerializedName("estadistica") val estadistica: String,
    @SerializedName("fecha") val fecha: String
)
