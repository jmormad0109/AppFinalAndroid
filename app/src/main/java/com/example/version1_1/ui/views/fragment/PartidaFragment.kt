package com.example.version1_1.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.version1_1.data.models.Partida
import com.example.version1_1.databinding.FragmentPartidaBinding
import com.example.version1_1.ui.adapter.PartidaAdapter
import com.example.version1_1.ui.dialog.AddPartidaDialogFragment
import com.example.version1_1.ui.dialog.EditPartidaDialogFragment
import com.example.version1_1.ui.viewmodel.PartidaViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class PartidaFragment : Fragment() {

    private var _binding: FragmentPartidaBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PartidaViewModel by viewModels()
    private lateinit var adapter: PartidaAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPartidaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupObservers()

        binding.addButton.setOnClickListener {
            val dialog = AddPartidaDialogFragment { partida ->
                viewModel.agregarPartida(partida)
            }
            dialog.show(childFragmentManager, "AddPartidaDialog")
        }


        viewModel.cargarPartidas()
    }

    private fun setupRecyclerView() {
        adapter = PartidaAdapter(
            onEditClick = { partida -> editarPartida(partida) },
            onDeleteClick = { partida -> eliminarPartida(partida) }
        )

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@PartidaFragment.adapter
        }
    }

    private fun setupObservers() {
        lifecycleScope.launch {
            viewModel.partidas.collectLatest { partidas ->
                adapter.submitList(partidas)
            }
        }
    }

    private fun agregarNuevaPartida() {
        val nuevaPartida = Partida(
            nombre = "Nueva Partida",
            resultado = "GANADO",
            estadistica = "Puntaje: 100",
            fecha = "2025-03-07"
        )

        viewModel.agregarPartida(nuevaPartida)
        Toast.makeText(requireContext(), "Partida agregada", Toast.LENGTH_SHORT).show()
    }

    private fun editarPartida(partida: Partida) {
        val dialog = EditPartidaDialogFragment(partida) { partidaEditada ->
            viewModel.actualizarPartida(partida.nombre, partidaEditada)
        }
        dialog.show(childFragmentManager, "EditPartidaDialog")
    }


    private fun eliminarPartida(partida: Partida) {
        viewModel.eliminarPartida(partida.nombre)
        Toast.makeText(requireContext(), "Partida eliminada", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
