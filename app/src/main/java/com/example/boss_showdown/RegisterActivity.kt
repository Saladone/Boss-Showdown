package com.example.boss_showdown

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val actionbar= supportActionBar
        actionbar!!.title= "Zona Registrazione"
        actionbar.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun RegistraERitorna(view: View){



        val intent = Intent(this@RegisterActivity,LoginActivity::class.java)
        startActivity(intent)
    }

}