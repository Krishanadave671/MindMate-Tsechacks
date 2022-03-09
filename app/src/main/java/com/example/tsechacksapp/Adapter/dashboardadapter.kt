package com.example.tsechacksapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.example.tsechacksapp.R
import com.example.tsechacksapp.models.FamilyData
import com.example.tsechacksapp.models.postdata

class dashboardadapter(private val list : ArrayList<postdata>, private val context: Context) : RecyclerView.Adapter<dashboardadapter.Viewholder>(){

    inner class Viewholder(itemview : View ) : RecyclerView.ViewHolder(itemview){
        val rolephoto : ImageView = itemview.findViewById(R.id.rolephoto)
        val roletitle : TextView = itemview.findViewById(R.id.roletext)
        val description : TextView = itemview.findViewById(R.id.description)
        val familyname :  TextView = itemview.findViewById(R.id.familyname)
        val postphoto : ImageView = itemview.findViewById(R.id.postimage)



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.dashboardrecyclerview, parent, false)
        return Viewholder(view)

    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        val itemmodel = list.get(position)
        holder.familyname.setText(itemmodel.familyname)
        holder.description.setText(itemmodel.description)
        holder.postphoto.setImageResource(itemmodel.postimage)
        holder.rolephoto.setImageResource(itemmodel.rolephoto)
        holder.roletitle.setText(itemmodel.role)

    }

    override fun getItemCount(): Int {
        return list.size

    }
}