package com.example.version1_1.ui.views.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.version1_1.R
import com.example.version1_1.databinding.FragmentEditPartidaBinding
import com.example.version1_1.domain.models.Partida

class EditPartidaDialogFragment: DialogFragment() {

    private lateinit var binding: FragmentEditPartidaBinding
    var editPartida: ((Partida) -> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_edit_partida, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentEditPartidaBinding.bind(view)
        cargarDatos(arguments)
        crearListener()
    }

    private fun cargarDatos(arguments: Bundle?){
        arguments?.let {
            binding.editResultado.setText(it.getString("resultado"))
            binding.editEstadistica.setText(it.getString("estadistica"))
            binding.editFecha.setText(it.getString("fecha"))
        }
    }

    private fun crearListener() {
        binding.btnActualizar.setOnClickListener{
            val editarPartida = recoverDataLayout()
            if (editarPartida.isValid()){
                editPartida?.invoke(editarPartida)
                dismiss()
            }else{
                Toast.makeText(activity, "Debe rellenar todos los campos", Toast.LENGTH_LONG).show()
            }
        }

        binding.btnCancelar.setOnClickListener{
            dismiss()
        }
    }

    private fun recoverDataLayout(): Partida {
        return Partida(
            id = requireArguments().getInt("id"),
            resultado = binding.editResultado.text.toString(),
            estadistica = binding.editEstadistica.text.toString(),
            fecha = binding.editFecha.text.toString()
        )
    }

    private fun Partida.isValid(): Boolean {
        return resultado.isNotEmpty() && estadistica.isNotEmpty() && fecha.isNotEmpty()
    }

}