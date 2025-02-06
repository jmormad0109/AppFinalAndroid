package com.example.version1_1.framework

import android.content.Context
import androidx.room.Room
import com.example.version1_1.data.datasource.database.DatabasePartidas
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val NAME_DATABASE = "partidas"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, DatabasePartidas::class.java, NAME_DATABASE).build()

    @Singleton
    @Provides
    fun provideDao(database: DatabasePartidas) =
        database.partidaDao()
}