package com.example.version1_1.domain.usecase

import com.example.version1_1.data.repository.PartidaRepository

class DeletePartidaUseCase(private val repository: PartidaRepository) {
    suspend operator fun invoke(id: Int){
        repository.delete(id)
    }
}