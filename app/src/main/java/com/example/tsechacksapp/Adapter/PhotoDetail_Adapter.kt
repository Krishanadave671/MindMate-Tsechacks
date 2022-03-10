package com.example.tsechacksapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.tsechacksapp.R
import com.example.tsechacksapp.models.photoData
import com.example.tsechacksapp.models.postdata

class PhotoDetail_Adapter(private val list : ArrayList<photoData>, private val context: Context) : RecyclerView.Adapter<PhotoDetail_Adapter.Viewholder>() {
    inner class Viewholder(itemview : View) : RecyclerView.ViewHolder(itemview){
        val detailPage_image : ImageView = itemview.findViewById(R.id.detailPage_image)}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoDetail_Adapter.Viewholder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.image_item, parent, false)
        return Viewholder(view)
    }
    override fun onBindViewHolder(holder: PhotoDetail_Adapter.Viewholder, position: Int) {
        val itemmodel = list.get(position)
        holder.detailPage_image.setImageResource(itemmodel.detailPage_image)}

    override fun getItemCount(): Int {
        return list.size
    }
}



