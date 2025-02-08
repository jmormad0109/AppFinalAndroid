package com.example.version1_1.ui.views.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.version1_1.R
import com.example.version1_1.databinding.FragmentAddPartidaBinding
import com.example.version1_1.domain.models.Partida

class AddPartidaDialogFragment: DialogFragment() {

    private lateinit var binding: FragmentAddPartidaBinding
    var addPartida: ((Partida) -> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_partida, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddPartidaBinding.bind(view)
        crearListener()
    }

    private fun crearListener() {
        binding.btnGuardar.setOnClickListener{
            val nuevaPartida = recoverDataLayout()
            if(nuevaPartida.isValid()){
                addPartida?.invoke(nuevaPartida)
                dismiss()
            } else {
                Toast.makeText(activity, "Rellene todos los campos", Toast.LENGTH_LONG).show()
            }
        }

        binding.btnCancelar.setOnClickListener{
            dismiss()
        }
    }

    private fun recoverDataLayout(): Partida {
        var numId: Int = 1
        numId = numId++
        return Partida(
            id = numId,
            resultado = binding.editResultado.text.toString(),
            estadistica = binding.editEstadistica.text.toString(),
            fecha = binding.editFecha.text.toString()
        )
    }

    private fun Partida.isValid(): Boolean {
        return resultado.isNotEmpty() && estadistica.isNotEmpty() && fecha.isNotEmpty()
    }

}