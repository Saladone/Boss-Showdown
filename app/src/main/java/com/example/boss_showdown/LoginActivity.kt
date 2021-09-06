package com.example.boss_showdown

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val actionbar= supportActionBar
        actionbar!!.title= "Login e Registrazione"
        actionbar.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun Procedi(view: View){


        val intent = Intent(this@LoginActivity,MenuActivity::class.java)
        startActivity(intent)

    }

    fun gotoRegistra(view: View){

        val intent = Intent(this@LoginActivity,RegisterActivity::class.java)
        startActivity(intent)
    }

}
