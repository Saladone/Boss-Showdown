package com.example.boss_showdown

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class MenuActivity : AppCompatActivity() {

    var uid: String=""
    var livello: Int=1
    var username:String=""
    var totpoints:Int=0
    var mod=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val actionbar= supportActionBar
        actionbar!!.title= "Menu' giocatore"
        actionbar.setHomeButtonEnabled(false)

        val giocatore: TextView = findViewById(R.id.textViewMenuUsername)
        username = intent.getStringExtra("Username").toString()
        totpoints = intent.getIntExtra("Punti", 0)
        uid=intent.getStringExtra("id").toString()
        giocatore.setText(username)
        LevelUp(totpoints)

    }

    private fun LevelUp(points: Int){

        val lvl: TextView = findViewById(R.id.textViewLevel)
        val punti: TextView = findViewById(R.id.textViewTotPoints)
        val attplus: TextView = findViewById(R.id.textViewAttMod)
        val defplus: TextView = findViewById(R.id.textViewDefMod)
        val psplus: TextView = findViewById(R.id.textViewPsMod)
        var nextLevel=1500  //max: 1837500
        while(points>=nextLevel && livello<50){
            livello=livello+1
            mod=mod+5
            nextLevel=nextLevel+1500*livello
        }
        if(livello==50){
            mod=mod+5
        }
        lvl.setText("Livello: $livello")
        attplus.setText("Mod. Attacco: +$mod%")
        defplus.setText("Mod. Difesa: +$mod%")
        psplus.setText("Mod. PS: +$mod%")
        if(livello<50){
            punti.setText("Totale Punti: $totpoints / $nextLevel")
        }
        else{
            punti.setText("Totale Punti: $totpoints / -")
        }
    }


    fun gotoLeaderboard(view: View) {

        val intent = Intent(this@MenuActivity,LeaderboardActivity::class.java)
        startActivity(intent)

    }

    fun gotoInfo(view: View){

        val intent = Intent(this@MenuActivity,InfoActivity::class.java)
        startActivity(intent)

    }

    fun gotoGameDos(view: View){
        val intent = Intent(this@MenuActivity,GameActivity::class.java)
        intent.putExtra("Username", username)
        intent.putExtra("Livello_ID", livello)
        intent.putExtra("Tipo_ID", 1)
        intent.putExtra("Punti", totpoints)
        intent.putExtra("id", uid)
        startActivity(intent)
        finish()
    }

}