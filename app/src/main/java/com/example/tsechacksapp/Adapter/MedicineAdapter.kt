package com.example.tsechacksapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.tsechacksapp.R
import com.example.tsechacksapp.data.MedicineDetail
import com.example.tsechacksapp.data.PatientDetail

class MedicineAdapter(private val datas: ArrayList<MedicineDetail>)
    :RecyclerView.Adapter<MedicineAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val medName = itemView.findViewById<TextView>(R.id.medicine_name)
        val medQty = itemView.findViewById<TextView>(R.id.medicine_quantity)
        val medTime = itemView.findViewById<TextView>(R.id.medicine_time)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =LayoutInflater.from(parent.context).inflate(R.layout.medicine_info_item,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = datas[position]
        var string = ""
        if(data.morning == true) string+= "morning "
        if(data.evening == true) string+= "| evening "
        if(data.night == true) string+= "| night"
        holder.medName.text = data.name
        holder.medQty.text = data.quantity
        holder.medTime.text = string

    }

    override fun getItemCount(): Int {
        return datas.size
    }
}