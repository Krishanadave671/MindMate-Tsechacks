package com.example.tsechacksapp.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.tsechacksapp.R
import com.example.tsechacksapp.game.GameMainActivity
import pl.bclogic.pulsator4droid.library.PulsatorLayout


class ProfileFragment : Fragment() {
    private lateinit var ImageViewgame : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)


        val pulsator = view.findViewById(R.id.pulsator) as PulsatorLayout

        ImageViewgame = view.findViewById(R.id.pulsatingImage)

        ImageViewgame.setOnClickListener{
            startActivity(Intent(context,GameMainActivity::class.java))
        }

        pulsator.start()

        pulsator.count = 50
        pulsator.duration = 100



        return view
    }
}