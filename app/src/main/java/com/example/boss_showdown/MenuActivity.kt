package com.example.boss_showdown

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val actionbar= supportActionBar
        actionbar!!.title= "Menu' giocatore"

    }


    fun gotoScoreboard(view: View) {


    }

    fun gotoInfo(view: View){

        val intent = Intent(this@MenuActivity,InfoActivity::class.java)
        startActivity(intent)

    }

}