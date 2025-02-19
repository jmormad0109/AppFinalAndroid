package com.example.version1_1.ui.views.fragment

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.example.version1_1.R
import com.example.version1_1.databinding.FragmentSettingsBinding
import com.google.android.material.navigation.NavigationView
import java.io.File


class SettingsFragment: Fragment(R.layout.fragment_settings) {

    private lateinit var binding: FragmentSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)

        cargarImagenPorDefecto()

        binding.btnCamara.setOnClickListener{hacerFoto()}
        binding.btnGaleria.setOnClickListener{seleccionarFoto()}
        return binding.root
    }


    private fun hacerFoto(){
        val intent =  Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        hacerFotoCamara.launch(intent)
    }

    private fun seleccionarFoto(){
        seleccionarFotoGaleria.launch("image/*")
    }

    private val hacerFotoCamara =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result ->
                if (result.resultCode == Activity.RESULT_OK){
                    val bitmap = result.data?.extras?.get("data") as Bitmap
                    actualizarImagen(bitmap)
                }
        }

    private val seleccionarFotoGaleria =
        registerForActivityResult(ActivityResultContracts.GetContent()) {
            uri: Uri? ->
            uri?.let { actualizarImagen(it) }
        }


    private fun actualizarImagen(uri: Uri){
        val navigationView = requireActivity().findViewById<NavigationView>(R.id.navigation_view)
        val header = navigationView.getHeaderView(0)
        val img = header.findViewById<ImageView>(R.id.img_Nav_Header)
        img.setImageURI(uri)
    }

    private fun actualizarImagen(bitmap: Bitmap){
        val navigationView = requireActivity().findViewById<NavigationView>(R.id.navigation_view)
        val header = navigationView.getHeaderView(0)
        val img = header.findViewById<ImageView>(R.id.img_Nav_Header)
        img.setImageBitmap(bitmap)
    }

    private fun cargarImagenPorDefecto(){
        val navigationView = requireActivity().findViewById<NavigationView>(R.id.navigation_view)
        val header = navigationView.getHeaderView(0)
        val img = header.findViewById<ImageView>(R.id.img_Nav_Header)

        img.setImageResource(R.drawable.luffy)
    }
/*
* private fun hacerFoto(){
        val intent =  Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        hacerFotoCamara.launch(intent)
    }
    private val hacerFotoCamara =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                result ->
            if (result.resultCode == Activity.RESULT_OK){
                val bitmap = result.data?.extras?.get("data") as Bitmap
                actualizarImagen(bitmap)
            }
        }

    private fun actualizarImagen(bitmap: Bitmap){
        val navigationView = requireActivity().findViewById<NavigationView>(R.id.navigation_view)
        val header = navigationView.getHeaderView(0)
        val img = header.findViewById<ImageView>(R.id.img_Nav_Header)
        img.setImageBitmap(bitmap)
    }*/
}