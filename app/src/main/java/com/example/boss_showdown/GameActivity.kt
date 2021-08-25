package com.example.boss_showdown

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class GameActivity : AppCompatActivity() {

    private var tipo=0
    private var livello=0
    private var ps=0
    private var punti=0
    private var turno=1
    private var attacco=50
    private var difesa= 30
    private var attaccoB=70
    private var difesaB = 20
    private var stato=0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val actionbar= supportActionBar
        actionbar!!.title= "Partita in corso"
        actionbar.setHomeButtonEnabled(false)

        tipo = intent.getIntExtra("Tipo_ID", 0)
        livello = intent.getIntExtra("Livello_ID", 1)
        Game(livello,tipo)
        val testo: TextView = findViewById(R.id.textViewCronistoria)
        testo.setText("Livello: " + livello.toString() + "\nTipo: " + tipo.toString())

    }

    private fun Game(livello: Int, tipo: Int){


    }

    private fun TurnoBoss(){

    }

    private fun TuoTurno(){


    }

}