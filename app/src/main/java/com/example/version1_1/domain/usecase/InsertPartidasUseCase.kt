package com.example.version1_1.domain.usecase

import com.example.version1_1.data.datasource.database.dao.PartidaDao
import javax.inject.Inject

class InsertPartidasUseCase @Inject constructor(
    private val partidaRepositoryDao: PartidaDao
){


}