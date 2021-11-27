package com.example.boss_showdown

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class GameActivity : AppCompatActivity() {

    //Stato=0: Normale
    //stato=1: Guardia bassa
    //stato=2: Guardia alta


    private var tipo=0
    private var livello=1
    private var ps=150
    private var psmax=150
    private var punti=0
    private var turno=1
    private var attacco=100
    private var difesa= 70
    private var attaccoB=80
    private var difesaB = 50
    private var stato=0
    private var mul=1




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val actionbar= supportActionBar
        actionbar!!.title= "Partita in corso"
        actionbar.setHomeButtonEnabled(false)

        tipo = intent.getIntExtra("Tipo_ID", 0)
        livello = intent.getIntExtra("Livello_ID", 1)
        SetGame(livello)
     //   val testo: TextView = findViewById(R.id.textViewCronistoria)


    }

    private fun SetGame(livello: Int){

        val testo: TextView = findViewById(R.id.textViewCronistoria)
        val turn: TextView = findViewById(R.id.textViewTurno)
        val point: TextView = findViewById(R.id.textViewPunti)
        val salute: TextView = findViewById(R.id.textViewPS)
        attacco=attacco+((attacco/100)*(livello-1)*10)
        difesa=difesa+((difesa/100)*(livello-1)*10)
        psmax=psmax+((psmax/100)*(livello-1)*10)
        ps=psmax

        turn.setText("Turno: $turno")
        point.setText("Punti: $punti")
        testo.setText("-Che cosa fara' Wojak Mascherato?")
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
            testo.text=Stringa+"\n"+"-Il colpo devastante del Boss Gigachad infligge a Wojak Mascherato la bellezza di $roll PS di danno!"

            attaccoB= (attaccoB*1.20).toInt()
            mul= mul*2
            Stringa=(testo.text).toString()
            testo.text=Stringa+"\n"+"-Il Boss Gigachad trabocca di potere e la sua caacita' offensiva aumenta sensibilmente!"
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
            testo.text=Stringa+"\n"+"-Il Boss Gigachad contrattacca infliggendo a Wojak Mascherato $roll PS di danno!"

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
        testo.setText("-Wojak Mascherato attacca infliggendo $roll PS di danno al Boss Gigachad!")


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
        testo.setText("-Wojak Mascherato sferra un attacco a piena potenza infliggendo $roll PS di danno al Boss Gigachad restando tuttavia scoperto per il contrattacco nemico!")

        TurnoBoss()
    }

    fun doGuardia(view: View){

        val testo: TextView = findViewById(R.id.textViewCronistoria)
        stato=2
        testo.setText("-Wojak Mascherato assume una posizione di guardia per ridurre i danni del prossimo attacco!")
        TurnoBoss()
    }

    fun doCura(view: View){

        val testo: TextView = findViewById(R.id.textViewCronistoria)
        val salute: TextView = findViewById(R.id.textViewPS)
        val scarto=psmax-ps
        val prima=ps
        val dopo:Int
        if(scarto==0){
            testo.setText("-Wojak Mascherato e' talmente stunnato da provare a curarsi pur essendo in piena salute...")
        }
        else {
            ps = ps + psmax / 2
            if (ps > psmax) {
                ps = psmax
            }
            dopo=ps-prima
            salute.setText("PS: $ps / $psmax")
            testo.setText("-Wojak Mascherato ha recuperto $dopo PS!")
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

        val intent = Intent(this@GameActivity,ResultsActivity::class.java)
        intent.putExtra("Punti", punti)
        intent.putExtra("Turno", turno)
        intent.putExtra("Tipo", tipo)
        startActivity(intent)
    }

  /*  private fun Quit(){

        val intent = Intent(this@GameActivity,MainActivity::class.java)
        startActivity(intent)
        finish()
    }*/

}