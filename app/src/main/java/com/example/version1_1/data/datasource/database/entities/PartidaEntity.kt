package com.example.version1_1.data.datasource.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PartidaEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int=0,
    @ColumnInfo(name = "resultado") val resultado: String,
    @ColumnInfo(name = "estadistica") val estadistica: String,
    @ColumnInfo(name = "fecha") val fecha: String
)


