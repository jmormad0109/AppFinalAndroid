package com.example.version1_1.data.datasource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.version1_1.data.datasource.database.dao.PartidaDao
import com.example.version1_1.data.datasource.database.entities.PartidaEntity

@Database(entities = [PartidaEntity::class], version = 1)


abstract class DatabasePartidas : RoomDatabase(){
    abstract fun partidaDao(): PartidaDao
}