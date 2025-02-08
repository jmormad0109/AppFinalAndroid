package com.example.version1_1.ui.modelview

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.version1_1.data.repository.PartidaRepository
import com.example.version1_1.domain.models.Partida
import com.example.version1_1.domain.usecase.DeletePartidaUseCase
import com.example.version1_1.domain.usecase.EditPartidaUseCase
import com.example.version1_1.domain.usecase.GetPartidasUseCase
import com.example.version1_1.domain.usecase.InsertPartidaUseCase
import kotlinx.coroutines.launch

class PartidasViewModel(): ViewModel() {

    val partidaLiveData = MutableLiveData<List<Partida>>()
    private val progresBar = MutableLiveData<Boolean>()
    private val repositorio: PartidaRepository = PartidaRepository()
    private val getPartidasUseCase: GetPartidasUseCase = GetPartidasUseCase(repositorio)
    private val insetPartidasUseCase: InsertPartidaUseCase = InsertPartidaUseCase(repositorio)
    private val editPartidaUseCase: EditPartidaUseCase = EditPartidaUseCase(repositorio)
    private val deletePartidaUseCase: DeletePartidaUseCase = DeletePartidaUseCase(repositorio)


    fun getPartidas() {
        viewModelScope.launch {
            progresBar.value = true

            var data = getPartidasUseCase()

            if (data != null){
                partidaLiveData.value = data
                progresBar.value = false
            }
        }
    }

    fun insertPartida(partida: Partida) {
        viewModelScope.launch {
            insetPartidasUseCase(partida)

            val actualizarLista = getPartidasUseCase()
            partidaLiveData.postValue(actualizarLista)
        }
    }

    fun editPartida(partida: Partida, nuevaPartida: Partida){
        viewModelScope.launch {
            editPartidaUseCase(partida, nuevaPartida)
            val actualizarLista = getPartidasUseCase()
            partidaLiveData.postValue(actualizarLista)
        }
    }

    fun deletePartida(pos: Int){
        viewModelScope.launch {
            deletePartidaUseCase(pos)
            val actualizarLista = getPartidasUseCase()
            partidaLiveData.postValue(actualizarLista)
        }
    }
}