package com.example.tsechacksapp.doctor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.tsechacksapp.R
import com.example.tsechacksapp.fragment.HomeFragment
import com.example.tsechacksapp.fragment.MedicineFragment
import com.example.tsechacksapp.fragment.ProfileFragment
import np.com.susanthapa.curved_bottom_navigation.CbnMenuItem

class MainMainDoctorActivity : AppCompatActivity() {

    private lateinit var bottomNavigation: np.com.susanthapa.curved_bottom_navigation.CurvedBottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_main_doctor)

        bottomNavigation = findViewById(R.id.bottom_nav_doctor)


        val menuItems = arrayOf(
            CbnMenuItem(
                R.drawable.ic_medicine,
                R.drawable.avd_medicine
            ),
            CbnMenuItem(
                R.drawable.ic_home,
                R.drawable.avd_home
            )
        )


        bottomNavigation.setMenuItems(menuItems,1)
        bottomNavigation.setOnMenuItemClickListener { cbnMenuItem, i ->
            val destFragment: Fragment = when ( cbnMenuItem.icon){
                R.drawable.ic_medicine -> MedicineUploadFragment()
                else -> PhotoUploadFragment()
            }
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container_view_doctor,destFragment).commit()

        }

    }
}