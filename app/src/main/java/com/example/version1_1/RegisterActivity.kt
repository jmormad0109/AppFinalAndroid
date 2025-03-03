package com.example.version1_1

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.version1_1.data.models.User
import com.example.version1_1.databinding.ActivityRegisterBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.auth.auth
import retrofit2.Retrofit

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginButton.setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding.registerButton.setOnClickListener{
            val dni = binding.dniEditText.text.toString().trim()
            val name = binding.nameEditText.text.toString().trim()
            val email = binding.emailEditText.text.toString().trim()
            val password = binding.passwordEditText.text.toString().trim()
            val repeatPassword = binding.passwordRepeatEditText.text.toString().trim()

            if (dni.isEmpty() || name.isEmpty() || email.isEmpty() || password.isEmpty() || repeatPassword.isEmpty()){
                Toast.makeText(this, "Rellene todos los campos", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if (password != repeatPassword){
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            val user = User(dni, name, email, password)
            RetrofitClient
        }


    }

}