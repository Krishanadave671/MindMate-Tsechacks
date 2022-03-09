package com.example.tsechacksapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.tsechacksapp.R
import com.example.tsechacksapp.data.PatientDetail

class PatientDetailsAdapter(private val datas: ArrayList<PatientDetail>)
    :RecyclerView.Adapter<PatientDetailsAdapter.ViewHolder>() {




    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val params =itemView.findViewById<TextView>(R.id.patient_detail_list)
        val status = itemView.findViewById<ImageView>(R.id.patient_detail_status)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =LayoutInflater.from(parent.context).inflate(R.layout.patient_info_item,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = datas[position]
        holder.params.text = data.desc
        val imageRes = when(data.status){
            "true"-> R.drawable.ic_baseline_done_24
            else -> R.drawable.ic_baseline_cancel_24
        }
        holder.status.setImageResource(imageRes)
    }

    override fun getItemCount(): Int {
        return datas.size
    }
}