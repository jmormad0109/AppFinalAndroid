package com.example.version1_1

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.version1_1.databinding.ActivityRegisterBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.auth.auth

class RegisterActivity : AppCompatActivity() {

    private lateinit var activityRegisterBinding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityRegisterBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(activityRegisterBinding.root)

        this.auth = FirebaseAuth.getInstance()
        sharedPreferences = getSharedPreferences("register-info", MODE_PRIVATE)

        this.auth = Firebase.auth

        activityRegisterBinding.loginButton.setOnClickListener{
            startActivityLogin()
        }

        start()
    }

    private fun start(){



        activityRegisterBinding.registerButton.setOnClickListener {
            val email = activityRegisterBinding.emailEditText.text.toString().trim()
            val password = activityRegisterBinding.passwordEditText.text.toString()
            val repeatPassword = activityRegisterBinding.passwordRepeatEditText.text.toString()

            if (password != repeatPassword || email.isEmpty() || password.isEmpty() || repeatPassword.isEmpty()){
                Toast.makeText(this, "La contraseña no coincide o falta algún campo", Toast.LENGTH_LONG).show()
            } else {
                registerUser(email, password) { result, message ->
                    Toast.makeText(this@RegisterActivity, message, Toast.LENGTH_LONG).show()
                    if (result)
                        startActivityLogin()
                }
            }
        }
    }

    private fun registerUser(email: String, password: String, onResult: (Boolean, String) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){
                taskAssin->
                if (taskAssin.isSuccessful){
                    val user = auth.currentUser
                    user?.sendEmailVerification()
                        ?.addOnCompleteListener{
                            taskVerification ->
                        var message = ""
                            message = if (taskVerification.isSuccessful)
                                "Usuario registrado. Verifique el correo del usuario"
                            else
                                "Usuario registrado. ${taskVerification.exception?.message}"
                            auth.signOut()
                            onResult(true, message)
                        }
                        ?.addOnFailureListener{
                            exception->
                            onResult(false, "No se pudo enviar el correo de verificación: ${exception.message}")
                        }

                } else {
                    try{
                        throw taskAssin.exception ?:Exception ("Error desconocido")
                    } catch (e: FirebaseAuthUserCollisionException){
                        onResult (false, "Ese usuario ya existe")
                    }catch (e: FirebaseAuthWeakPasswordException){
                        onResult (false, "La contraseña es débil: ${e.reason}")
                    }
                    catch (e: FirebaseAuthInvalidCredentialsException){
                        onResult (false, "El email proporcionado, no es válido")
                    }
                    catch (e: Exception){
                        onResult (false, e.message.toString())
                    }
                }
            }
    }

    private fun startActivityLogin(){
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}