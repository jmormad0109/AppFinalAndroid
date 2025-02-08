package com.example.version1_1

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.version1_1.databinding.ActivityLoginBinding
import com.example.version1_1.ui.views.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException

class LoginActivity : AppCompatActivity() {

    private lateinit var activityLoginBinding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityLoginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(activityLoginBinding.root)


        this.auth = FirebaseAuth.getInstance()
        sharedPreferences = getSharedPreferences("login-info", MODE_PRIVATE)
        loadEmail()
        start()
        confirmarUsuario()

    }

    private fun confirmarUsuario(){
        val currentUser = auth.currentUser
        if(currentUser != null && currentUser.isEmailVerified){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun start(){
        activityLoginBinding.registerButton.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        activityLoginBinding.loginButton.setOnClickListener{
            val email = activityLoginBinding.emailEditText.text.toString().trim()

            if (activityLoginBinding.loginButton.text == "Recuperar contraseña"){
                if (email.isNotEmpty()){
                    recoverPassword(email){ result, message ->
                        Toast.makeText(this@LoginActivity, message, Toast.LENGTH_LONG).show()
                        if (result){
                            resetToLoginState()
                        }
                    }
                } else {
                    Toast.makeText(this, "Introduzca un correo electrónico, por favor", Toast.LENGTH_LONG).show()
                }
            } else {
                val pass = activityLoginBinding.passwordEditText.text.toString()
                if (email.isNotEmpty() && pass.isNotEmpty())
                    startLogin(email, pass) { result, message ->
                        Toast.makeText(this@LoginActivity, message, Toast.LENGTH_LONG).show()
                        if (result){
                            intent = Intent(this@LoginActivity, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    }
                else
                    Toast.makeText(this, "Rellene todos los campos para continuar", Toast.LENGTH_LONG).show()
            }
        }

        activityLoginBinding.resetPasswordText.setOnClickListener{
            toggleToRecoveryState()

        }
    }

    private fun startLogin(user: String, pass: String, onResult: (Boolean, String) -> Unit) {
        auth.signInWithEmailAndPassword(user, pass)
            .addOnCompleteListener { taskSingIn ->
                var message = ""

                if (taskSingIn.isSuccessful) {
                    val possibleUser = auth.currentUser
                    if (possibleUser?.isEmailVerified == true) {
                        saveEmail(user)
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        auth.signOut()
                        onResult(false, "Verifica tu correo antes de loguearte")
                    }
                } else {
                    try {
                        throw taskSingIn.exception ?: Exception("Error inesperado")
                    } catch (e: FirebaseAuthInvalidUserException) {
                        message = "El usuario tiene porblemas para borrarse"
                    } catch (e: FirebaseAuthInvalidCredentialsException) {
                        message = if (e.message?.contains("There is no user record corresponding to this identifier") == true){
                            "El usuario no existe"
                        } else "Constraseña incorrecta o usuario no registrado"
                    } catch (e: Exception){
                        message = e.message.toString()
                    }
                    onResult(false, message)
                }
            }
    }

    private fun recoverPassword(email: String, onResult: (Boolean, String) -> Unit){
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { taskResetEmail ->
                if (taskResetEmail.isSuccessful){
                    onResult(true, "Tienes un nuevo email con la nueva contraseña")
                }
                else{
                    var message = ""
                    try {
                        throw taskResetEmail.exception ?: Exception("Error en le reseteo")
                    }catch (e: FirebaseAuthInvalidCredentialsException){
                        message = "Email incorrecto"
                    }catch (e: Exception){
                        message = e.message.toString()
                    }
                    onResult(false, message)
                }
            }
    }


    private fun saveEmail(email: String) {
        val editor = sharedPreferences.edit()
        editor.putString("saved_email", email)
        editor.apply()
    }

    private fun loadEmail() {
        val savedEmail = sharedPreferences.getString("saved_email", "")
        if (savedEmail.isNullOrEmpty()) {
            activityLoginBinding.emailEditText.setText(savedEmail)
        }
    }

    private fun toggleToRecoveryState() {
        activityLoginBinding.passwordEditText.visibility = android.view.View.INVISIBLE
        activityLoginBinding.loginButton.text = "Recuperar contraseña"
        activityLoginBinding.resetPasswordText.text = "Volver al login"
        activityLoginBinding.resetPasswordText.setOnClickListener {
            resetToLoginState()
        }
    }

    private fun resetToLoginState() {
        activityLoginBinding.passwordEditText.visibility = android.view.View.VISIBLE
        activityLoginBinding.loginButton.text = "Login"
        activityLoginBinding.resetPasswordText.text = "Recuperar contraseña"
        activityLoginBinding.resetPasswordText.setOnClickListener {
            toggleToRecoveryState()
        }
    }
}