package com.example.boss_showdown

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class LeaderboardActivity : AppCompatActivity() {

    private lateinit var dbref : DatabaseReference
    private lateinit var userRecyclerview : RecyclerView
    private lateinit var userArrayList : ArrayList<UtentexClassifica>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leaderboard)

        val actionbar= supportActionBar
        actionbar!!.title= "Classifica Giocatori"
        actionbar.setDisplayHomeAsUpEnabled(true)

        userRecyclerview = findViewById(R.id.userList)
        userRecyclerview.layoutManager = LinearLayoutManager(this)
        userRecyclerview.setHasFixedSize(true)

        userArrayList = arrayListOf<UtentexClassifica>()
        getUserData()

    }

    private fun getUserData() {

        dbref = FirebaseDatabase.getInstance("https://boss-showdown-default-rtdb.europe-west1.firebasedatabase.app").getReference("Utenti")

        dbref.addValueEventListener(object : ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()){

                    for (userSnapshot in snapshot.children){

                        val username: String = userSnapshot.child("username").value.toString()
                        val punti: Int = userSnapshot.child("punti").value.toString().toInt()
                        val user = UtentexClassifica(username,0,punti)
                        userArrayList.add(user!!)

                    }
                    userArrayList.sortByDescending {
                        it.punti
                    }
                    var pos: Int=0
                    userArrayList.forEach {
                        it.posizione=pos
                        pos=pos+1
                    }
                    userRecyclerview.adapter = Adapter(userArrayList)


                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })

    }
}