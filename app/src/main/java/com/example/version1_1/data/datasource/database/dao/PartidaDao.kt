package com.example.version1_1.data.datasource.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.version1_1.data.datasource.database.entities.PartidaEntity

@Dao
interface PartidaDao {

    @Query ("SELECT * FROM partidaentity")
    suspend fun getPartidas(): List<PartidaEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPartida(vararg partida: PartidaEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPartidas(partidas: List<PartidaEntity>)

    @Query ("DELETE FROM partidaentity WHERE id = :id")
    suspend fun deletePartida(id: Int)
}