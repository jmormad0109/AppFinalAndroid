package com.example.version1_1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.version1_1.R.*
import com.example.version1_1.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var auth: FirebaseAuth
    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var drawer: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        //Navigation Drawer

        val toolbar: Toolbar = findViewById(R.id.toolbar_main)
        setSupportActionBar(toolbar)

        drawer = findViewById(id.drawer_layout)
        val navigationView: NavigationView = findViewById(id.nav_view)

        toggle = ActionBarDrawerToggle(this, drawer, toolbar, string.navigation_drawer_open, string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        navigationView.setNavigationItemSelectedListener(this)


        sharedPreferences = getSharedPreferences("login-info", MODE_PRIVATE)
        auth = FirebaseAuth.getInstance()

        val elementList = mutableListOf<String>()
        for (i in 1..15) {
            elementList.add("$i")
        }

        val recyclerView: RecyclerView = findViewById(id.recyclerView)
        val adapter = Adapter(elementList)
        recyclerView.adapter = adapter

        activityMainBinding.logOutButton.setOnClickListener{
            logOut(this)
        }
        val addButton: Button = findViewById(id.addButton)



        addButton.setOnClickListener {

            val dialogView = layoutInflater.inflate(layout.dialog_item, null)
            val editText = dialogView.findViewById<EditText>(id.editTextNewItem)
            val saveButton = dialogView.findViewById<Button>(id.saveButton)
            val cancelButton = dialogView.findViewById<Button>(id.cancelButton)


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

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId){
            R.id.nav_casa -> Toast.makeText(this, "Item Casa", Toast.LENGTH_LONG).show()
            R.id.nav_item_2 -> Toast.makeText(this, "Item 2", Toast.LENGTH_LONG).show()
            R.id.nav_item_3 -> Toast.makeText(this, "Item 3", Toast.LENGTH_LONG).show()
            R.id.nav_item_4 -> Toast.makeText(this, "Item 4", Toast.LENGTH_LONG).show()
        }
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        toggle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        toggle.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)

    }

    private fun logOut(context: Context){
        FirebaseAuth.getInstance().signOut()

        val intent = Intent(context, LoginActivity::class.java)
        startActivity(intent)
    }
}
