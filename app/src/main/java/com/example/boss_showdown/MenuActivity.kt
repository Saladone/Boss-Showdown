package com.example.boss_showdown

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MenuActivity : AppCompatActivity() {

    var livello: Int=1
    var username:String=""
    var totpoints:Int=0
    var attmod: Int=0
    var healthmod:Int=0
    var defmod:Int=0

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

    fun gotoGameDos(view: View){
        val intent = Intent(this@MenuActivity,GameActivity::class.java)
        intent.putExtra("Livello_ID", 1)
        intent.putExtra("Tipo_ID", 1)
        startActivity(intent)
    }

}