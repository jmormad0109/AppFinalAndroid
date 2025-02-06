package com.example.version1_1.domain.usecase

import com.example.version1_1.data.datasource.database.dao.PartidaDao
import com.example.version1_1.data.repository.PartidaRepository
import javax.inject.Inject

class DeletePartidaUseCase @Inject constructor(
    private val partidaRepositoryDao: PartidaDao
){

    private var id: Int=0

    fun setId(id: Int){
        this.id = id
    }

    suspend operator fun invoke(){
        partidaRepositoryDao.deletePartida(id)
    }

}