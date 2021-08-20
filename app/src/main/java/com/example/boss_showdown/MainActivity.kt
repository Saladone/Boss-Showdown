package com.example.boss_showdown

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun gotoLogin(view: View){

        val intent = Intent(this@MainActivity,LoginActivity::class.java)
        startActivity(intent)

    }

    fun gotoInfo(view: View){

        val intent = Intent(this@MainActivity,InfoActivity::class.java)
        startActivity(intent)

    }

    fun gotoGame(view: View){

        val intent = Intent(this@MainActivity,GameActivity::class.java)
        intent.putExtra("Livello_ID", 20)
        intent.putExtra("Tipo_ID", 0)
    }


}