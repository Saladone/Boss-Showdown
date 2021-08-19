package com.example.boss_showdown

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class GameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val actionbar= supportActionBar
        actionbar!!.title= "Partita in corso"
        actionbar.setHomeButtonEnabled(false)
    }


}