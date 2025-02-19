package com.example.version1_1.ui.views.fragment

import AdapterPartida
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.version1_1.R
import com.example.version1_1.databinding.FragmentListBinding
import com.example.version1_1.domain.models.Partida
import com.example.version1_1.ui.modelview.PartidasViewModel

class PartidasFragment: Fragment(R.layout.fragment_list) {

    private lateinit var binding: FragmentListBinding
    private lateinit var adapter: AdapterPartida
    private val partidasViewModel: PartidasViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)

        crearRecyclerView()
        observerViewModel()
        crearBotonAdd()
        return binding.root
    }

    private fun crearRecyclerView(){

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter = AdapterPartida(emptyList(), ::editPartida, ::deletePartida)
        binding.recyclerView.adapter = adapter
    }

    private fun crearBotonAdd() {
        binding.addButton.setOnClickListener{
            crearDialogAdd()
        }
    }

    private fun deletePartida(pos: Int){
        val partidaId = adapter.listaPartidas[pos].id
        partidasViewModel.deletePartida(pos)
        Toast.makeText(requireContext(), "Partida eliminada.", Toast.LENGTH_LONG).show()
    }

    private fun editPartida(partida: Partida){
        val dialog = EditPartidaDialogFragment()
        var args = Bundle().apply {
            putInt("id", partida.id)
            putString("resultado", partida.resultado)
            putString("estadistica", partida.estadistica)
            putString("fecha", partida.fecha)
        }

        dialog.arguments = args
        dialog.editPartida = {
            editPartida -> partidasViewModel.editPartida(partida, editPartida)
        }

        dialog.show(parentFragmentManager, "EditPartidaDialogFragment")
    }

    private fun crearDialogAdd(){
        val dialog = AddPartidaDialogFragment()
        dialog.addPartida = {
            partida -> partidasViewModel.insertPartida(partida)
        }

        dialog.show(parentFragmentManager, "AddPartidaDialogFragment")
    }

    private fun observerViewModel(){
        partidasViewModel.partidaLiveData.observe(viewLifecycleOwner) {
            partidas ->
            if (partidas.isNotEmpty()){
                actualizarPartidas(partidas)
            }
        }

        cargarDatos()
    }

    private fun cargarDatos(){
        partidasViewModel.getPartidas()
    }

    private fun actualizarPartidas(partidas: List<Partida>){
        if (adapter.listaPartidas != partidas){
            val tamano = adapter.listaPartidas.size
            adapter.listaPartidas = partidas
            adapter.notifyDataSetChanged()
        }
    }
}