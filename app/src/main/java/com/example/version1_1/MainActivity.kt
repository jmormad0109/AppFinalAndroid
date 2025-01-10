package com.example.version1_1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.version1_1.databinding.ActivityLoginBinding
import com.example.version1_1.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var auth: FirebaseAuth
    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        sharedPreferences = getSharedPreferences("login-info", MODE_PRIVATE)
        auth = FirebaseAuth.getInstance()

        val elementList = mutableListOf<String>()
        for (i in 1..15) {
            elementList.add("$i")
        }

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val adapter = Adapter(elementList)
        recyclerView.adapter = adapter

        activityMainBinding.logOutButton.setOnClickListener{
            logOut(this)
        }
        val addButton: Button = findViewById(R.id.addButton)



        addButton.setOnClickListener {

            val dialogView = layoutInflater.inflate(R.layout.dialog_item, null)
            val editText = dialogView.findViewById<EditText>(R.id.editTextNewItem)
            val saveButton = dialogView.findViewById<Button>(R.id.saveButton)
            val cancelButton = dialogView.findViewById<Button>(R.id.cancelButton)


            val dialog = AlertDialog.Builder(this)
                .setView(dialogView)
                .create()


            saveButton.setOnClickListener {
                val newItem = editText.text.toString()
                if (newItem.isNotEmpty()) {
                    elementList.add(newItem)
                    adapter.notifyItemInserted(elementList.size - 1)
                    recyclerView.scrollToPosition(elementList.size - 1)
                    dialog.dismiss()
                } else {
                    Toast.makeText(this, "El elemento no puede estar vacío", Toast.LENGTH_SHORT).show()
                }
            }

            cancelButton.setOnClickListener {
                dialog.dismiss()
            }

            dialog.show()
        }




    }
    private fun logOut(context: Context){
        FirebaseAuth.getInstance().signOut()

        val intent = Intent(context, LoginActivity::class.java)
        startActivity(intent)
    }
}
