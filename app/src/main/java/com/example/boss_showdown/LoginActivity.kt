package com.example.boss_showdown

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = FirebaseAuth.getInstance()

        val actionbar= supportActionBar
        actionbar!!.title= "Zona Login"
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
