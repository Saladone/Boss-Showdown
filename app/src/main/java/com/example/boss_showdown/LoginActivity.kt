package com.example.boss_showdown

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Toast
import com.example.boss_showdown.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityLoginBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()

        val actionbar= supportActionBar
        actionbar!!.title= "Zona Login"
        actionbar.setDisplayHomeAsUpEnabled(true)

        binding.buttonAccedi.setOnClickListener{
            if(editTextLoginEmail.text.toString().isBlank()){
                editTextLoginEmail.error="Per favore inserire un indirizzo email"
                editTextLoginEmail.requestFocus()
            }

            else if(!Patterns.EMAIL_ADDRESS.matcher(editTextLoginEmail.text.toString()).matches()){
                editTextLoginEmail.error="Per favore inserire un indirizzo email valido"
                editTextLoginEmail.requestFocus()
            }

            else if(editTextLoginPassword.text.toString().isBlank()){
                editTextLoginPassword.error="Per favore inserire una password"
                editTextLoginPassword.requestFocus()
            }
            else {
                auth.signInWithEmailAndPassword(editTextLoginEmail.text.toString(), editTextLoginPassword.text.toString())
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success")
                            val user = auth.currentUser
                            val uid = user?.uid
                            database= FirebaseDatabase.getInstance("https://boss-showdown-default-rtdb.europe-west1.firebasedatabase.app").getReference("Utenti")
                            database.child(uid.toString()).get().addOnSuccessListener {

                                if(it.exists()){

                                    val username: String = it.child("username").value.toString()
                                    val punti: Int = it.child("punti").value.toString().toInt()
                                    Toast.makeText(this,"Login effettuato",Toast.LENGTH_SHORT).show()

                                    val intent = Intent(this@LoginActivity, MenuActivity::class.java)
                                    intent.putExtra("Username", username)
                                    intent.putExtra("Punti", punti)
                                    intent.putExtra("id", uid.toString())
                                    startActivity(intent)
                                    auth.signOut()
                                    finish()
                                }
                                else{
                                    Toast.makeText(this,"Utente non registrato",Toast.LENGTH_SHORT).show()
                                }

                            }

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.exception)
                            Toast.makeText(baseContext, "Autenticazione fallita.",
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


    fun gotoRegistra(view: View){

        val intent = Intent(this@LoginActivity,RegisterActivity::class.java)
        startActivity(intent)
    }

}
