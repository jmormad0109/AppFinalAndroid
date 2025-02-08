package com.example.version1_1.domain.usecase

import com.example.version1_1.domain.models.Partida
import com.example.version1_1.domain.repository.PartidaRepositoryInterface

class GetPartidasUseCase(private val respository: PartidaRepositoryInterface) {
    suspend operator fun invoke(): List<Partida>{
        return respository.getAll()
    }
}