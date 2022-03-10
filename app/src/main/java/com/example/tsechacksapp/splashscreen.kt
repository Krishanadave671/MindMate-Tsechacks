package com.example.tsechacksapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.example.tsechacksapp.fragment.HomeFragment
import com.example.tsechacksapp.login.LoginActivity

class splashscreen : AppCompatActivity() {
    var lottieAnimationView1: LottieAnimationView? = null
    var lottieAnimationView2: LottieAnimationView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)

        //bg = findViewById(R.id.imageView5);
        lottieAnimationView1 = findViewById(R.id.lottieAnimationView1)
        lottieAnimationView2 = findViewById(R.id.lottieAnimationView2)
        Handler().postDelayed({
            val i = Intent(this@splashscreen, LoginActivity::class.java)
            startActivity(i)
            finish()
        }, 1500)
    }
}