package com.example.boss_showdown

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView

class GameActivity : AppCompatActivity() {

    //Stato=0: Normale
    //stato=1: Guardia bassa
    //stato=2: Guardia alta

    private var mp: MediaPlayer? = null
    private var currentSong = mutableListOf(R.raw.bossbattle,R.raw.rayquaza,R.raw.bossbattle2,R.raw.dialga,R.raw.palkia,R.raw.finale,R.raw.legend,R.raw.yveltal,R.raw.finale2,R.raw.legend2)

    private var tipo=0
    private var uid: String=""
    private var livello=1
    private var ps=150
    private var psmax=150
    private var punti=0
    private var totalpoints:Long=0
    private var turno=1
    private var attacco=100
    private var difesa= 70
    private var attaccoB=80
    private var difesaB = 50
    private var stato=0
    private var mul=1
    private var username:String="Wojack Mascherato"




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val actionbar= supportActionBar
        actionbar!!.title= "Partita in corso"
        actionbar.setHomeButtonEnabled(false)

        tipo = intent.getIntExtra("Tipo_ID", 0)
        livello = intent.getIntExtra("Livello_ID", 1)
        username = intent.getStringExtra("Username").toString()
        totalpoints = intent.getIntExtra("Punti",0).toLong()
        if(tipo==1){
            uid=intent.getStringExtra("id").toString()
        }
        SetGame(livello)

        val rand:Int=(0 until currentSong.size).random()
        mp = MediaPlayer.create(this,currentSong[rand])
        Log.d("GameActivity", "ID: ${mp!!.audioSessionId}")
        mp?.start()
        mp?.isLooping=true
        Log.d("GameActivity", "Durata: ${mp!!.duration/1000} secondi")

    }

    override fun onStop() {
        super.onStop()
        if(mp!=null) mp?.pause()
        Log.d("GameActivity", "Pausa a: ${mp!!.currentPosition/1000} secondi")

    }

    override fun onResume() {
        super.onResume()
        mp?.start()
        mp?.isLooping=true
        Log.d("GameActivity", "Durata: ${mp!!.duration/1000} secondi")
    }

    override fun onDestroy() {
        super.onDestroy()
        if(mp!=null){
            mp?.stop()
            mp?.reset()
            mp?.release()
            mp=null
        }
    }

    private fun SetGame(livello: Int){

        val testo: TextView = findViewById(R.id.textViewCronistoria)
        val turn: TextView = findViewById(R.id.textViewTurno)
        val point: TextView = findViewById(R.id.textViewPunti)
        val salute: TextView = findViewById(R.id.textViewPS)
        if(livello<50) {
            attacco = attacco + ((attacco / 100) * (livello - 1) * 5)
            difesa = difesa + ((difesa / 100) * (livello - 1) * 5)
            psmax = psmax + ((psmax / 100) * (livello - 1) * 5)
        }
        else{
            attacco = attacco + ((attacco / 100) * livello * 5)
            difesa = difesa + ((difesa / 100) * livello * 5)
            psmax = psmax + ((psmax / 100) * livello * 5)
        }

        ps=psmax

        turn.setText("Turno: $turno")
        point.setText("Punti: $punti")
        testo.setText("-Che cosa fara' "+username+"?")
        salute.setText("PS: $ps / $psmax")


    }

    private fun TurnoBoss(){

        val testo: TextView = findViewById(R.id.textViewCronistoria)
        val turn: TextView = findViewById(R.id.textViewTurno)
        val point: TextView = findViewById(R.id.textViewPunti)
        val salute: TextView = findViewById(R.id.textViewPS)
        var danno: Int =attaccoB-difesa
        var roll: Int
        var Stringa: String= (testo.text).toString()

        if(turno%10==0){

            danno= attaccoB-50
            roll=DamageRoll(danno)
            roll= (roll*1.5).toInt()
            if(stato==1){
                roll= (roll*1.25).toInt()
                ps=ps-roll
                if(ps<0){
                    ps=0
                }
            }
            if(stato==2){
                roll= (roll*0.50).toInt()
                ps=ps-roll
                if(ps<0){
                    ps=0
                }
                else{
                    punti=punti+roll
                    point.setText("Punti: $punti")
                }
            }
            if(stato==0){
                ps=ps-roll
                if(ps<0){
                    ps=0
                }
            }

            salute.setText("PS: $ps / $psmax")
            testo.text=Stringa+"\n"+"-Il colpo devastante del Boss Gigachad infligge a "+username+" la bellezza di $roll PS di danno!"

            attaccoB= (attaccoB*1.20).toInt()
            attacco=(attacco*1.20).toInt()
            Stringa=(testo.text).toString()
            testo.text=Stringa+"\n"+"-Il Boss Gigachad trabocca di potere e la sua capcita' offensiva aumenta sensibilmente!"
            turno= turno+1

            turn.setText("Turno: $turno")

        }
        else{

            roll=DamageRoll(danno)

            if(stato==1){
                roll= (roll*1.25).toInt()
                ps=ps-roll
                if(ps<0){
                    ps=0
                }
            }
            if(stato==2){
                roll= (roll*0.50).toInt()
                ps=ps-roll
                if(ps<0){
                    ps=0
                }
                else{
                    punti=punti+roll
                    point.setText("Punti: $punti")
                }
            }
            if(stato==0){
                ps=ps-roll
                if(ps<0){
                    ps=0
                }
            }

            salute.setText("PS: $ps / $psmax")
            Stringa=(testo.text).toString()
            testo.text=Stringa+"\n"+"-Il Boss Gigachad contrattacca infliggendo a "+username+" $roll PS di danno!"

            if((turno+1)%10==0){
                Stringa=(testo.text).toString()
                testo.text=Stringa+"\n"+"-Il Boss Gigachad sta accumulando potere. Il prossimo attacco sara' micidiale!"
            }

            turno= turno+1

            turn.setText("Turno: $turno")
        }

        stato=0
        if(ps==0){
            Endgame()
        }

    }

    fun doAttacco(view: View){

        val testo: TextView = findViewById(R.id.textViewCronistoria)
        val point: TextView = findViewById(R.id.textViewPunti)
        val danno: Int =(attacco-difesaB)*mul
        val roll: Int = DamageRoll(danno)

        punti= punti+roll
        point.setText("Punti: $punti")
        testo.setText("-"+username+" attacca infliggendo $roll PS di danno al Boss Gigachad!")


        TurnoBoss()
    }

    fun doAttaccoP(view: View){

        stato=1

        val testo: TextView = findViewById(R.id.textViewCronistoria)
        val point: TextView = findViewById(R.id.textViewPunti)
        val danno: Int =((attacco-difesaB)*1.5*mul).toInt()
        val roll: Int = DamageRoll(danno)

        punti= punti+roll
        point.setText("Punti: $punti")
        testo.setText("-"+username+" sferra un attacco a piena potenza infliggendo $roll PS di danno al Boss Gigachad restando tuttavia scoperto per il contrattacco nemico!")

        TurnoBoss()
    }

    fun doGuardia(view: View){

        val testo: TextView = findViewById(R.id.textViewCronistoria)
        stato=2
        testo.setText("-"+username+" assume una posizione di guardia per ridurre i danni del prossimo attacco!")
        TurnoBoss()
    }

    fun doCura(view: View){

        val testo: TextView = findViewById(R.id.textViewCronistoria)
        val salute: TextView = findViewById(R.id.textViewPS)
        val scarto=psmax-ps
        val prima=ps
        val dopo:Int
        if(scarto==0){
            testo.setText("-In preda alla confusione "+username+" prova a curarsi pur essendo in piena salute...")
        }
        else {
            ps = ps + psmax / 2
            if (ps > psmax) {
                ps = psmax
            }
            dopo=ps-prima
            salute.setText("PS: $ps / $psmax")
            testo.setText("-"+username+" ha recuperto $dopo PS!")
        }
        TurnoBoss()
    }

    private fun DamageRoll(danno: Int): Int {

        val roll : Int
        val minimo = (danno*0.85).toInt()
        roll = (minimo..danno).random()


        return roll
    }

    private fun Endgame(){

        if(tipo==0){
            val intent = Intent(this@GameActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        else {
            val intent = Intent(this@GameActivity, ResultsActivity::class.java)
            totalpoints=totalpoints+punti
            if(totalpoints>2000000000){
                totalpoints=2000000000
            }
            intent.putExtra("Punti", punti)
            intent.putExtra("Turni", turno)
            intent.putExtra("id", uid)
            intent.putExtra("Livello", livello)
            intent.putExtra("Totale", totalpoints.toInt())
            intent.putExtra("Username", username)
            startActivity(intent)
            finish()
        }
    }
}