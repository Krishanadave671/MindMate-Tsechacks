package com.example.tsechacksapp.fragment

import android.content.Intent
import android.icu.lang.UCharacter
import android.media.Image
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.tsechacksapp.Adapter.FamilydataAdapter
import com.example.tsechacksapp.Adapter.dashboardadapter
import com.example.tsechacksapp.CollapsingTollbar_family
import com.example.tsechacksapp.CollapsingtoolbarActivity
import com.example.tsechacksapp.MainActivity
import com.example.tsechacksapp.R
import com.example.tsechacksapp.models.FamilyData
import com.example.tsechacksapp.models.postdata
import org.json.JSONException

class HomeFragment : Fragment() {
    private lateinit var list : ArrayList<FamilyData>
    private lateinit var dashboardadapter: dashboardadapter
    private lateinit var postlist : ArrayList<postdata>
    private lateinit var familydataAdapter : FamilydataAdapter
    private lateinit var viewPagerImgSlider: ViewPager2
    private lateinit var sliderHandle: Handler
    private lateinit var sliderRun :Runnable
    private lateinit var postrecyclerView: RecyclerView
    private lateinit var profileimg : ImageView
    private lateinit var linearLayoutManager: LinearLayoutManager
    //for jk
    private lateinit var ques: TextView
    private lateinit var ans: TextView
    private var requestQueue: RequestQueue? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view =  inflater.inflate(R.layout.fragment_home, container, false)
        viewPagerImgSlider = view.findViewById(R.id.viewPagerImgSlider)
        profileimg = view.findViewById(R.id.rolephoto)
        postrecyclerView = view.findViewById(R.id.postrecyclerview)

        //for jk
        ques=view.findViewById(R.id.jk_que)
        ans=view.findViewById(R.id.jk_ans)
        requestQueue=Volley.newRequestQueue(requireContext())
        apiCaller()

        loadrecyclerfamilyphoto()
        loadpostsrecyclerview()
        return view
    }

    private fun loadpostsrecyclerview() {
        postlist = ArrayList()
        postlist.add(postdata(R.drawable.friends,"Donald trump","Friend","I love playing golf i love vjdsvjsfdjsfj disd if ",R.drawable.fireboypng))
        postlist.add(postdata(R.drawable.friends,"johns adam","Family"," ",R.drawable.yashdalvi ))
        postlist.add(postdata(R.drawable.friends,"Elon musk","Friend"," I am a codecell core member and Air 1 in codechef",R.drawable.foresttrekingimage ))
        postlist.add(postdata(R.drawable.friends,"chris Gayle","son"," I am a codecell core member and Air 1 in codechef",R.drawable.birthdayparty))
        postlist.add(postdata(R.drawable.friends,"vladymyr putin ","brother"," I am a codecell core member and Air 1 in codechef",R.drawable.friends ))
        postlist.add(postdata(R.drawable.friends,"Zelensky","baby"," I am a codecell core member and Air 1 in codechef",R.drawable.yashdalvi ))
        dashboardadapter = dashboardadapter(postlist,requireContext())
        postrecyclerView.adapter = dashboardadapter
        linearLayoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
        postrecyclerView.layoutManager = linearLayoutManager
    }

    private fun loadrecyclerfamilyphoto() {
        list = ArrayList()
        list.add(FamilyData("Abhinay patil",R.drawable.person1image,1))
        list.add(FamilyData("adam johnes",R.drawable.person2image,1))
        list.add(FamilyData("shane warne",R.drawable.person3image,1))
        list.add(FamilyData("josh buttler",R.drawable.person4image,1))
        list.add(FamilyData("GT thampi",R.drawable.person5image,1))
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

            startActivity(Intent(requireContext(),CollapsingtoolbarActivity::class.java))
            } })




    }

    private fun apiCaller(){
        val url = "https://v2.jokeapi.dev/joke/pun"
        val request = JsonObjectRequest(Request.Method.GET, url, null, Response.Listener {
                response ->try {
                    val setup= response.getString("setup")
                val delivery = response.getString("delivery")
                ques.append("$setup")
                ans.append("$delivery")

        } catch (e: JSONException) {
            e.printStackTrace()
        }
        }, Response.ErrorListener { error -> error.printStackTrace() })
        requestQueue?.add(request)
    }



}