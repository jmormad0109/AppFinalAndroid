package com.example.version1_1.domain.usecase

import com.example.version1_1.data.repository.PartidaRepository
import com.example.version1_1.domain.models.Partida

class InsertPartidaUseCase(private val repository: PartidaRepository) {
    suspend operator fun invoke(partida: Partida){
        repository.insert(partida)
    }
}