package com.example.tsechacksapp.fragment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.tsechacksapp.Adapter.FamilydataAdapter
import com.example.tsechacksapp.R
import com.example.tsechacksapp.models.FamilyData

class HomeFragment : Fragment() {
    private lateinit var list : ArrayList<FamilyData>
    private lateinit var familydataAdapter : FamilydataAdapter
    private lateinit var viewPagerImgSlider: ViewPager2
    private lateinit var sliderHandle: Handler
    private lateinit var sliderRun :Runnable
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view =  inflater.inflate(R.layout.fragment_home, container, false)
        viewPagerImgSlider = view.findViewById(R.id.viewPagerImgSlider)

        loadrecyclerfamilyphoto()
        return view
    }

    private fun loadrecyclerfamilyphoto() {
        list = ArrayList()
        list.add(FamilyData("Abhinay patil",R.drawable.yashdalvi,1))
        list.add(FamilyData("Abhinay patil",R.drawable.yashdalvi,1))
        list.add(FamilyData("Abhinay patil",R.drawable.yashdalvi,1))
        list.add(FamilyData("Abhinay patil",R.drawable.yashdalvi,1))
        list.add(FamilyData("Abhinay patil",R.drawable.yashdalvi,1))
        familydataAdapter = FamilydataAdapter(viewPagerImgSlider,list, requireContext())
        viewPagerImgSlider.adapter = familydataAdapter
        viewPagerImgSlider.clipToPadding = false
        viewPagerImgSlider.clipChildren = false
        viewPagerImgSlider.offscreenPageLimit = 1
        viewPagerImgSlider.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val comPosPageTarn = CompositePageTransformer()
        comPosPageTarn.addTransformer(MarginPageTransformer(40))
        comPosPageTarn.addTransformer { page, position ->
            val r: Float = 1 - Math.abs(position)
            page.scaleY = 0.85f + r * 0.15f
        }
        viewPagerImgSlider.setPageTransformer(comPosPageTarn)
        sliderHandle = Handler()
        sliderRun = Runnable {
            viewPagerImgSlider.currentItem = viewPagerImgSlider.currentItem + 1
        }

        viewPagerImgSlider.registerOnPageChangeCallback(
            object :ViewPager2.OnPageChangeCallback(){

                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    sliderHandle.removeCallbacks(sliderRun)
                    sliderHandle.postDelayed(sliderRun,2000)
                }
            })

        familydataAdapter.setOnItemClickListener(object : FamilydataAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
               Toast.makeText(activity,"hi",Toast.LENGTH_SHORT).show()
            }
        })



    }
}