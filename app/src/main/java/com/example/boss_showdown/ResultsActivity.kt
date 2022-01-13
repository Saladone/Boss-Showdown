package com.example.boss_showdown

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.example.boss_showdown.databinding.ActivityResultsBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ResultsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultsBinding
    private lateinit var database: DatabaseReference
    private var punti=0
    private var turno=1
    private var livello=1
    private var uid=""
    private var totale=0
    private var username=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionbar= supportActionBar
        actionbar!!.title= "Riepilogo partita"
        actionbar.setDisplayHomeAsUpEnabled(false)

        punti=intent.getIntExtra("Punti",0)
        turno=intent.getIntExtra("Turni",1)
        livello=intent.getIntExtra("Livello",1)
        uid=intent.getStringExtra("id").toString()
        totale=intent.getIntExtra("Totale",0)
        username=intent.getStringExtra("Username").toString()

        val turniepunti: TextView = findViewById(R.id.textViewTurnPoint)
        val totpoints: TextView = findViewById(R.id.textViewTotale)
        val esperienza: TextView = findViewById(R.id.textViewProxLev)
        var nuovolivello: Int
        var nextlevel: Int

        turniepunti.setText("Hai combattuto per $turno turni totalizzando $punti punti.")
        totpoints.setText("Il numero totale di punti dopo questa partita e' $totale")
        nuovolivello=LevelUp(totale)
        nextlevel=toNextLev(totale)
        if(nuovolivello>livello && nextlevel==0){
            esperienza.setText("Hai raggiunto il livello massimo! Sei all'apice della potenza!")
        }
        if(nuovolivello>livello && nextlevel!=0){
            esperienza.setText("Hai raggiunto il livello $nuovolivello!\nTi mancano $nextlevel punti esperienza per il prossimo livello.")
        }
        if(nuovolivello<=livello && nextlevel==0){
            esperienza.setText("Sei gia' al livello massimo, eppure continui a combattere. Congratulazioni!")
        }
        if(nuovolivello<=livello && nextlevel!=0){
            esperienza.setText("Ti mancano $nextlevel punti esperienza per il prossimo livello.")
        }

        binding.buttonSaveReturn.setOnClickListener{
            database= FirebaseDatabase.getInstance("https://boss-showdown-default-rtdb.europe-west1.firebasedatabase.app").getReference("Utenti")
            val utente= mapOf("punti" to totale.toString())
            database.child(uid).updateChildren(utente).addOnSuccessListener {
                Toast.makeText(this,"Salvataggio effettuato",Toast.LENGTH_SHORT).show()
                val intent = Intent(this@ResultsActivity, MenuActivity::class.java)
                intent.putExtra("Username", username)
                intent.putExtra("Punti", totale)
                intent.putExtra("id", uid)
                startActivity(intent)
                finish()
            }.addOnFailureListener{
                Toast.makeText(this,"Salvataggio non riuscito", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@ResultsActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

    }

    private fun LevelUp(points: Int): Int {

        var level=1
        var nextLevel=1500
        while(points>=nextLevel && level<50){
            level=level+1
            nextLevel=nextLevel+1500*level
        }
        return level
    }

    private fun toNextLev(points: Int): Int {

        var level=1
        var nextLevel=1500
        while(points>=nextLevel && level<50){
            level=level+1
            nextLevel=nextLevel+1500*level
        }
        if(level==50){
            nextLevel=0
            return nextLevel
        }
        else {
            return nextLevel - points
        }
    }
}