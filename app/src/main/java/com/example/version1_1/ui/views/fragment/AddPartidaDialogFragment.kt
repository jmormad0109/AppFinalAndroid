package com.example.version1_1.ui.views.fragment

import android.Manifest
import android.content.ContentValues
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.example.version1_1.databinding.FragmentAddPartidaBinding
import com.example.version1_1.domain.models.Partida
import java.text.SimpleDateFormat
import java.util.*

class AddPartidaDialogFragment : DialogFragment() {

    private lateinit var binding: FragmentAddPartidaBinding

    var addPartida: ((Partida) -> Unit)? = null

    private var photoUri: Uri? = null


    private lateinit var cameraPermissionLauncher: ActivityResultLauncher<String>
    private lateinit var takePhotoLauncher: ActivityResultLauncher<Uri>
    private lateinit var galleryLauncher: ActivityResultLauncher<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        cameraPermissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
                if (isGranted) {
                    launchCamera()
                } else {
                    Toast.makeText(requireContext(), "Permiso de cámara denegado", Toast.LENGTH_SHORT).show()
                }
            }

        takePhotoLauncher =
            registerForActivityResult(ActivityResultContracts.TakePicture()) { success ->
                if (success) {
                    binding.imgViewAddDialog.setImageURI(photoUri)
                } else {
                    Toast.makeText(requireContext(), "Error al capturar la foto", Toast.LENGTH_SHORT).show()
                }
            }

        galleryLauncher =
            registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
                uri?.let {
                    photoUri = it
                    binding.imgViewAddDialog.setImageURI(it)
                }
            }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentAddPartidaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        crearListener()
    }

    private fun crearListener() {
        binding.btnGuardar.setOnClickListener {
            val nuevaPartida = recoverDataLayout()
            if (nuevaPartida.isValid()) {
                // Se crea la partida incluyendo el URI de la foto (convertido a String)
                val partidaConFoto = nuevaPartida.copy(
                    fotoUri = photoUri?.toString()
                )
                addPartida?.invoke(partidaConFoto)
                dismiss()
            } else {
                Toast.makeText(activity, "Rellene todos los campos", Toast.LENGTH_LONG).show()
            }
        }

        binding.btnCancelar.setOnClickListener { dismiss() }

        binding.btnCamara.setOnClickListener {
            if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                launchCamera()
            } else {
                cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
            }
        }

        binding.btnGaleria.setOnClickListener {
            launchGallery()
        }
    }

    private fun launchCamera() {
        val filename = "IMG_" + SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date()) + ".jpg"
        val contentValues = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, filename)
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
        }
        photoUri = requireActivity().contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
        photoUri?.let { takePhotoLauncher.launch(it) }
    }

    private fun launchGallery() {
        galleryLauncher.launch("image/*")
    }

    private fun recoverDataLayout(): Partida {
        val numId = 1
        return Partida(
            id = numId,
            resultado = binding.editResultado.text.toString(),
            estadistica = binding.editEstadistica.text.toString(),
            fecha = binding.editFecha.text.toString(),
            fotoUri = null
        )
    }

    private fun Partida.isValid(): Boolean {
        return resultado.isNotEmpty() && estadistica.isNotEmpty() && fecha.isNotEmpty()
    }
}

