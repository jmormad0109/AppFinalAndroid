package com.example.version1_1.data.repository

import com.example.version1_1.data.datasource.database.dao.PartidaDao
import com.example.version1_1.data.datasource.database.entities.PartidaEntity
import com.example.version1_1.data.datasource.memoria.service.PartidaService
import com.example.version1_1.domain.mapper.toPartida
import com.example.version1_1.domain.mapper.toPartidaEntity
import com.example.version1_1.domain.models.Partida
import com.example.version1_1.domain.models.Repository
import com.example.version1_1.domain.repository.PartidaRepositoryInterface
import javax.inject.Inject

class PartidaRepository @Inject constructor(
    private val service: PartidaService,
    private val partidaDao: PartidaDao
) : PartidaRepositoryInterface {
    override fun getPartidas(): List<Partida> {
        val data = service.getPartidas()

        Repository.partidas = data.map { it.toPartida() }
        return Repository.partidas
    }

    override suspend fun getPartidasEntity(): List<Partida> {
        val listEntity: List<PartidaEntity> = partidaDao.getPartidas()
        Repository.partidas = listEntity.map { it.toPartida() }
        return Repository.partidas
    }

    override suspend fun insertPartidas(listPartidasEntity: List<PartidaEntity>) {
        partidaDao.insertPartidas(listPartidasEntity)
    }

    override suspend fun insertPartida(partidaEntity: PartidaEntity): Partida {
        partidaDao.insertPartida(partidaEntity)
        return partidaEntity.toPartida()
    }

    override suspend fun deletePartida(partidaEntity: PartidaEntity) {
        partidaDao.deletePartida(partidaEntity.id)
    }


}