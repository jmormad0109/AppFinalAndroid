package com.example.version1_1.data.service

import com.example.version1_1.data.models.Partida
import retrofit2.Response
import retrofit2.http.*



    interface PartidaApi {

        @GET("partida")
        suspend fun getPartidas(): Response<List<Partida>>

        @GET("partida/{nombrePartida}")
        suspend fun getPartidaPorNombre(@Path("nombrePartida") nombre: String): Response<Partida>

        @POST("partida")
        suspend fun addPartida(@Body partida: Partida): Response<Partida>

        @PATCH("partida/{nombrePartida}")
        suspend fun updatePartida(
            @Path("nombrePartida") nombre: String,
            @Body partida: Partida
        ): Response<Partida>

        @DELETE("partida/{nombrePartida}")
        suspend fun deletePartida(@Path("nombrePartida") nombre: String): Response<Unit>
    }
