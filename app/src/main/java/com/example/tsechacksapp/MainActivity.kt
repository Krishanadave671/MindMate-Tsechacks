package com.example.tsechacksapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.tsechacksapp.fragment.HomeFragment
import com.example.tsechacksapp.fragment.MedicineFragment
import com.example.tsechacksapp.fragment.ProfileFragment
import com.google.android.material.navigation.NavigationView
import np.com.susanthapa.curved_bottom_navigation.CbnMenuItem

class MainActivity : AppCompatActivity() {

    private lateinit var navHostFragment: NavHostFragment
    private lateinit var bottomNavigation: np.com.susanthapa.curved_bottom_navigation.CurvedBottomNavigationView
    private lateinit var navController: NavController
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var drawerNavigationView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigation = findViewById(R.id.bottom_nav)
        navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
        navController = navHostFragment.navController
        drawerLayout = findViewById(R.id.drawer_layout)
        drawerNavigationView = findViewById(R.id.nav_drawer)

        val menuItems = arrayOf(
            CbnMenuItem(
              R.drawable.ic_medicine,
              R.drawable.avd_medicine
            ),
            CbnMenuItem(
                R.drawable.ic_home,
                R.drawable.avd_home
            ) ,
            CbnMenuItem(
            R.drawable.ic_profile,
            R.drawable.avd_profile
        )
        )

        bottomNavigation.setMenuItems(menuItems,1)
//        bottomNavigation.setupWithNavController(navController)

        bottomNavigation.setOnMenuItemClickListener { cbnMenuItem, i ->
            val destFragment: Fragment = when ( cbnMenuItem.icon){
                R.drawable.ic_medicine -> MedicineFragment()
                R.drawable.ic_profile -> ProfileFragment()
                else -> HomeFragment()
            }
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container_view,destFragment).commit()

        }
    }
}