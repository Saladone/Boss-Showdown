package com.example.boss_showdown

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

    /*    val actionbar= supportActionBar
        actionbar!!.title= "Menu' giocatore"
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setHomeButtonEnabled()*/
    }

   /* override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }*/

    fun gotoScoreboard(view: View) {


    }

}