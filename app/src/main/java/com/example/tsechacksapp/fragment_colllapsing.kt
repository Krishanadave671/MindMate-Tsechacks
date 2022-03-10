package com.example.tsechacksapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tsechacksapp.Adapter.PhotoDetail_Adapter
import com.example.tsechacksapp.Adapter.dashboardadapter
import com.example.tsechacksapp.models.photoData
import com.example.tsechacksapp.models.postdata

class CollapsingTollbar_family : Fragment() {
    private lateinit var PhotoDetail_Adapter: PhotoDetail_Adapter
    private lateinit var photolist : ArrayList<photoData>
    private lateinit var image_rc: RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_collapsing_tollbar_family)


        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {

            val view =  inflater.inflate(R.layout.image_item, container, false)

            image_rc = view.findViewById(R.id.image_rc)
            loadrecyclermemberdetails()
            return view
    }

        private fun loadrecyclermemberdetails(){
            photolist=ArrayList()
            photolist.add(photoData(R.drawable.yashdalvi))
            photolist.add(photoData(R.drawable.yashdalvi))
            photolist.add(photoData(R.drawable.yashdalvi))
            photolist.add(photoData(R.drawable.yashdalvi))
            PhotoDetail_Adapter = PhotoDetail_Adapter(photolist,requireContext())
            image_rc.adapter = PhotoDetail_Adapter
            linearLayoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
            image_rc.layoutManager = linearLayoutManager
        }
}