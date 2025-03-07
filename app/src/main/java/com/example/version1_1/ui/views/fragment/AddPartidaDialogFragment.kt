package com.example.version1_1.ui.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.version1_1.data.models.Partida
import com.example.version1_1.databinding.FragmentAddPartidaBinding

class AddPartidaDialogFragment(private val onPartidaAdded: (Partida) -> Unit) : DialogFragment() {

    private var _binding: FragmentAddPartidaBinding? = null
    private val binding get() = _binding!!

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(requireContext())
        _binding = FragmentAddPartidaBinding.inflate(layoutInflater)
        dialog.setContentView(binding.root)

        binding.btnGuardar.setOnClickListener {
            val nombre = binding.editNombre.text.toString().trim()
            val resultado = binding.editResultado.text.toString().trim().uppercase()
            val estadistica = binding.editEstadistica.text.toString().trim()
            val fecha = binding.editFecha.text.toString().trim()

            if (nombre.isEmpty() || resultado.isEmpty() || estadistica.isEmpty() || fecha.isEmpty()) {
                Toast.makeText(requireContext(), "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val nuevaPartida = Partida(nombre, resultado, estadistica, fecha)
            onPartidaAdded(nuevaPartida)
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
