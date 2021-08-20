package com.example.boss_showdown

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class GameActivity : AppCompatActivity() {

    private var tipo=0
    private var livello=0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val actionbar= supportActionBar
        actionbar!!.title= "Partita in corso"
        actionbar.setHomeButtonEnabled(false)

        tipo = intent.getIntExtra("Tipo_ID", 0)
        livello = intent.getIntExtra("Livello_ID", 1)
        Game(livello,tipo)

    }

    private fun Game(livello: Int, stato: Int){


    }

    private fun TurnoBoss(){

    }

    private fun TuoTurno(){


    }

}