package com.example.tsechacksapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tsechacksapp.Adapter.PhotoDetail_Adapter
import com.example.tsechacksapp.models.photoData

class CollapsingtoolbarActivity : AppCompatActivity() {

    private lateinit var PhotoDetail_Adapter: PhotoDetail_Adapter
    private lateinit var photolist : ArrayList<photoData>
    private lateinit var image_rc: RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collapsingtoolbar)

        image_rc = findViewById(R.id.image_rc)
        loadrecyclermemberdetails()

    }

    private fun loadrecyclermemberdetails() {

        photolist=ArrayList()
        photolist.add(photoData(R.drawable.yashdalvi))
        photolist.add(photoData(R.drawable.yashdalvi))
        photolist.add(photoData(R.drawable.yashdalvi))
        photolist.add(photoData(R.drawable.yashdalvi))
        PhotoDetail_Adapter = PhotoDetail_Adapter(photolist,this)
        image_rc.adapter = PhotoDetail_Adapter
        linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        image_rc.layoutManager = linearLayoutManager

    }
}