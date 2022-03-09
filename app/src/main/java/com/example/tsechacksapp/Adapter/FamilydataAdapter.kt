package com.example.tsechacksapp.Adapter

import android.content.ClipData
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.tsechacksapp.R
import com.example.tsechacksapp.models.FamilyData

class FamilydataAdapter(val viewPager : ViewPager2, private val list : ArrayList<FamilyData>, private val context: Context): RecyclerView.Adapter<FamilydataAdapter.ViewHolder> (){

    private lateinit var mListener :onItemClickListener
    interface onItemClickListener {
        fun onItemClick(position: Int)

    }
    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }

    inner class ViewHolder(ItemView: View, listener: onItemClickListener) : RecyclerView.ViewHolder(ItemView) {
        val Familyphoto: ImageView = itemView.findViewById(R.id.imageView_familyphoto)
        val familynametext: TextView = itemView.findViewById(R.id.textView_familyname)

        init {
            itemView.setOnClickListener{
                listener.onItemClick(adapterPosition)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.familyviewpagecustom, parent, false)
        view.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        return ViewHolder(view,mListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ItemsViewModel = list.get(position)
        holder.familynametext.setText(ItemsViewModel.title)
        holder.Familyphoto.setImageResource(ItemsViewModel.image)
        if (position == list.size - 2) {
            viewPager.post(run)
        }
    }

    private val run = Runnable {
        list.addAll(list)
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return list.size
    }
}