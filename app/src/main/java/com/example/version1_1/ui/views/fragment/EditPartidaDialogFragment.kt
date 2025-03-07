package com.example.version1_1.ui.dialog

import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.version1_1.data.models.Partida
import com.example.version1_1.databinding.FragmentEditPartidaBinding

class EditPartidaDialogFragment(
    private val partida: Partida,
    private val onPartidaUpdated: (Partida) -> Unit
) : DialogFragment() {

    private var _binding: FragmentEditPartidaBinding? = null
    private val binding get() = _binding!!

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(requireContext())
        _binding = FragmentEditPartidaBinding.inflate(layoutInflater)
        dialog.setContentView(binding.root)

        // Cargar los datos actuales
        binding.editNombre.setText(partida.nombre)
        binding.editResultado.setText(partida.resultado)
        binding.editEstadistica.setText(partida.estadistica)
        binding.editFecha.setText(partida.fecha)

        binding.btnActualizar.setOnClickListener {
            val nombre = binding.editNombre.text.toString().trim()
            val resultado = binding.editResultado.text.toString().trim().uppercase()
            val estadistica = binding.editEstadistica.text.toString().trim()
            val fecha = binding.editFecha.text.toString().trim()

            if (nombre.isEmpty() || resultado.isEmpty() || estadistica.isEmpty() || fecha.isEmpty()) {
                Toast.makeText(requireContext(), "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val partidaEditada = Partida(nombre, resultado, estadistica, fecha)
            onPartidaUpdated(partidaEditada)
            dismiss()
        }

        binding.btnCancelar.setOnClickListener {
            dismiss()
        }

        return dialog
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
