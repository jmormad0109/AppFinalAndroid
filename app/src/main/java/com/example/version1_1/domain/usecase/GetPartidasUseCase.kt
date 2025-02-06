package com.example.version1_1.domain.usecase

import com.example.version1_1.data.datasource.database.entities.PartidaEntity
import com.example.version1_1.data.repository.PartidaRepository
import com.example.version1_1.domain.mapper.toPartidaEntity
import com.example.version1_1.domain.models.Partida
import com.example.version1_1.domain.models.Repository
import javax.inject.Inject

class GetPartidasUseCase @Inject constructor(
    private val partidaRepositoryDao: PartidaRepository
) {

    suspend operator fun invoke(): List<Partida>?{
        Repository.partidas = partidaRepositoryDao.getPartidasEntity()

        if (Repository.partidas.isEmpty()){
            Repository.partidas = partidaRepositoryDao.getPartidas()
            val dataPartidaEntity: List<PartidaEntity> = Repository.partidas.map { it.toPartidaEntity() }
            partidaRepositoryDao.insertPartidas(dataPartidaEntity)
        }
        return Repository.partidas
    }

}