package com.example.boss_showdown

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter(private val userList : ArrayList<UtentexClassifica>) : RecyclerView.Adapter<Adapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.user_item,
            parent,false)
        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentitem = userList[position]

        holder.username.text = currentitem.username
        holder.punti.text = currentitem.punti.toString()

    }

    override fun getItemCount(): Int {

        return userList.size
    }


    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val username : TextView = itemView.findViewById(R.id.tvusername)
        val punti : TextView = itemView.findViewById(R.id.tvpunti)

    }

}