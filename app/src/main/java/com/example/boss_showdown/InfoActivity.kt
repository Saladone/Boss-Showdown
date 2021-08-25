package com.example.boss_showdown

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.boss_showdown.databinding.ActivityInfoBinding
import kotlinx.android.synthetic.main.fragmenttext.*

class InfoActivity : AppCompatActivity() {

    var pos=0
    var stato=0

    lateinit var binding: ActivityInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragmentText()

        val actionbar= supportActionBar
        actionbar!!.title= "Info sul gioco"
        actionbar.setDisplayHomeAsUpEnabled(true)
}

override fun onSupportNavigateUp(): Boolean {
   onBackPressed()
   return true
}

fun posUp(view: View){

   if(pos<10){
       pos = pos+1
   }
    if(stato==0){
       replaceFragmentText()
    }
    else{
        replaceFragmentView()
    }

}

fun posDown(view: View){

   if(pos>0) {
       pos = pos-1
   }
    if(stato==0){
        replaceFragmentText()
    }
    else{
        replaceFragmentView()
    }
}

fun screenClick(view: View){

   stato=1
   replaceFragmentView()

}

fun textClick(view: View){

   stato=0
   replaceFragmentText()

}


    private fun replaceFragmentText(){

        val bundle = Bundle()
        bundle.putInt("data",pos)
        val fragment= FragmentText()
        fragment.arguments=bundle
        val fragmentManager= supportFragmentManager
        val fragmentTransaction= fragmentManager.beginTransaction()

        fragmentTransaction.replace(R.id.fragmentContainer,fragment)
        fragmentTransaction.commit()

}
    private fun replaceFragmentView(){

        val bundle = Bundle()
        bundle.putInt("data",pos)
        val fragment= FragmentView()
        fragment.arguments=bundle
        val fragmentManager= supportFragmentManager
        val fragmentTransaction= fragmentManager.beginTransaction()

        fragmentTransaction.replace(R.id.fragmentContainer,fragment)
        fragmentTransaction.commit()

    }

}