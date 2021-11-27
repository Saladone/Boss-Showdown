package com.example.boss_showdown

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import com.example.boss_showdown.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()

        val actionbar= supportActionBar
        actionbar!!.title= "Zona Registrazione"
        actionbar.setDisplayHomeAsUpEnabled(true)

        binding.buttonLoginRegistrati.setOnClickListener{

            if(editTextUsername.text.toString().isEmpty()){
                editTextUsername.error="Per favore inserire un username"
                editTextUsername.requestFocus()
            }

            else if(editTextEmail.text.toString().isEmpty()){
                editTextEmail.error="Per favore inserire un indirizzo email"
                editTextEmail.requestFocus()
            }

            else if(!Patterns.EMAIL_ADDRESS.matcher(editTextEmail.text.toString()).matches()){
                editTextEmail.error="Per favore inserire un indirizzo email valido"
                editTextEmail.requestFocus()
            }

            else if(editTextPassword.text.toString().isEmpty()){
                editTextPassword.error="Per favore inserire una password"
                editTextPassword.requestFocus()
            }
            else {
                val username = binding.editTextUsername.text.toString()
                val email = binding.editTextEmail.text.toString()
                val password = binding.editTextPassword.text.toString()

                database = FirebaseDatabase.getInstance("https://boss-showdown-default-rtdb.europe-west1.firebasedatabase.app").getReference("Utenti")
                val Utente = Utente(username, email, password, 0)
                database.child(username).setValue(Utente).addOnSuccessListener {

                    textViewMess.setText("Registrazione effettuata con successo!")
                }.addOnFailureListener {

                    textViewMess.setText("Si e' verificato un problema con la registrazione!")
                }
                auth.createUserWithEmailAndPassword(editTextEmail.text.toString(), editTextPassword.text.toString())
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            startActivity(Intent(this@RegisterActivity,LoginActivity::class.java))
                            finish()
                        } else {
                            Toast.makeText(baseContext, "Non sei riuscito a registrarti",
                                Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
    fun RegistraERitorna(view: View){

        if(editTextUsername.text.toString().isEmpty()){
            editTextUsername.error="Per favore inserire un username"
            editTextUsername.requestFocus()
            return
        }

        if(editTextEmail.text.toString().isEmpty()){
            editTextEmail.error="Per favore inserire un indirizzo email"
            editTextEmail.requestFocus()
            return
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(editTextEmail.text.toString()).matches()){
            editTextEmail.error="Per favore inserire un indirizzo email valido"
            editTextEmail.requestFocus()
            return
        }

        if(editTextPassword.text.toString().isEmpty()){
            editTextPassword.error="Per favore inserire una password"
            editTextPassword.requestFocus()
            return
        }

        auth.createUserWithEmailAndPassword(editTextEmail.text.toString(), editTextPassword.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                   startActivity(Intent(this@RegisterActivity,LoginActivity::class.java))
                   finish()
                } else {
                    Toast.makeText(baseContext, "Non sei riuscito a registrarti",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }

}