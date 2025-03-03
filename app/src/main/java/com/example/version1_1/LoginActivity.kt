package com.example.version1_1

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.version1_1.data.models.AuthResponse
import com.example.version1_1.data.service.AuthService
import com.example.version1_1.data.service.LoginRequest
import com.example.version1_1.data.service.RetrofitClient
import com.example.version1_1.databinding.ActivityLoginBinding
import com.example.version1_1.ui.views.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("login-info", MODE_PRIVATE)

        binding.registerButton.setOnClickListener{
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        binding.loginButton.setOnClickListener{
            val dni = binding.dniEditText.text.toString().trim()
            val password = binding.passwordEditText.text.toString()

            if (dni.isEmpty() || password.isEmpty()){
                Toast.makeText(this, "Rellena todos los campos", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            val loginRequest = LoginRequest(dni, password)
            RetrofitClient.authService.login(loginRequest).enqueue(object: Callback<AuthResponse>{
                override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                    if (response.isSuccessful){
                        val token = response.body()?.token
                        token?.let {
                            saveToken(it)
                            Toast.makeText(this@LoginActivity, "Inicio de sesión correcto", Toast.LENGTH_LONG).show()
                            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                            finish()
                        }
                    }else{
                        Toast.makeText(this@LoginActivity, "DNI o contraseña incorrectos", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                    Toast.makeText(this@LoginActivity, "Error: ${t.message}", Toast.LENGTH_LONG).show()
                }
            })

        }
    }
    private fun saveToken(token: String){
        val editor = sharedPreferences.edit()
        editor.putString("jwt_token", token)
        editor.apply()
    }
}