package com.example.boss_showdown

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment

class FragmentView() : Fragment(R.layout.fragmentview) {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragmentview, container, false)
        val img: ImageView = view.findViewById(R.id.imageScreenshot)

        val args = this.arguments

        val pos = args?.get("data")

        if(pos==0){
            img.setImageResource(R.drawable.potentialbackground)
        }
        if(pos==1){
            img.setImageResource(R.drawable.bg001)
        }
        if(pos==2){
            img.setImageResource(R.drawable.bg002)
        }
        if(pos==3){
            img.setImageResource(R.drawable.bg003)
        }
        if(pos==4){
            img.setImageResource(R.drawable.bg004)
        }
        if(pos==5){
            img.setImageResource(R.drawable.bg005)
        }
        if(pos==6){
            img.setImageResource(R.drawable.bg006)
        }
        if(pos==7){
            img.setImageResource(R.drawable.bg007)
        }
        if(pos==8){
            img.setImageResource(R.drawable.bg008)
        }
        if(pos==9){
            img.setImageResource(R.drawable.bg009)
        }
        if(pos==10){
            img.setImageResource(R.drawable.bg010)
        }

        return view
    }

}