package com.example.version1_1.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.version1_1.data.models.Partida
import com.example.version1_1.data.repository.PartidaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PartidaViewModel(private val repository: PartidaRepository = PartidaRepository()) : ViewModel() {

    private val _partidas = MutableStateFlow<List<Partida>>(emptyList())
    val partidas: StateFlow<List<Partida>> get() = _partidas

    fun cargarPartidas() {
        viewModelScope.launch {
            val partidasList = repository.getPartidas()
            _partidas.value = partidasList ?: emptyList()
        }
    }

    fun agregarPartida(partida: Partida) {
        viewModelScope.launch {
            val nuevaPartida = repository.addPartida(partida)
            if (nuevaPartida != null) {
                cargarPartidas() // Recargar lista tras agregar
            }
        }
    }

    fun actualizarPartida(nombrePartida: String, partidaActualizada: Partida) {
        viewModelScope.launch {
            val partidaEditada = repository.updatePartida(nombrePartida, partidaActualizada)
            if (partidaEditada != null) {
                cargarPartidas() // Recargar lista tras editar
            }
        }
    }

    fun eliminarPartida(nombrePartida: String) {
        viewModelScope.launch {
            val eliminada = repository.deletePartida(nombrePartida)
            if (eliminada) {
                cargarPartidas() // Recargar lista tras eliminar
            }
        }
    }
}
