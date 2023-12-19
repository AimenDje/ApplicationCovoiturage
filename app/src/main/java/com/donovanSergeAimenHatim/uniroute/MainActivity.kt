package com.donovanSergeAimenHatim.uniroute

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AnimationUtils
import androidx.fragment.app.FragmentContainerView
import com.donovanSergeAimenHatim.uniroute.ecrans.accueil.AccueilFragment
import com.donovanSergeAimenHatim.uniroute.ecrans.listTrajets.TrajetReserverFragment
import com.donovanSergeAimenHatim.uniroute.ecrans.logo.LogoFragment
import com.donovanSergeAimenHatim.uniroute.ecrans.profil.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Afficher le LogoFragment dans le fragmentContainerViewSplashScreen
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerViewSplashScreen, LogoFragment())
                .commit()
        }
        val fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        val fadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out)
        var fragmentContainerSplashScreen = findViewById<FragmentContainerView>(R.id.fragmentContainerViewSplashScreen)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottonnav)
        Handler(Looper.getMainLooper()).postDelayed({
            fragmentContainerSplashScreen.startAnimation(fadeOut)
            fragmentContainerSplashScreen.visibility = View.GONE
            bottomNavigationView.startAnimation(fadeIn)
            bottomNavigationView.visibility = View.VISIBLE
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, AccueilFragment())
                .commit()
        }, 3000)
        bottomNavigationView.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.coVoiturage -> {
                    val fragment = AccueilFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .commit()
                    true
                }
                R.id.reservation -> {
                    val fragment = TrajetReserverFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .commit()
                    true
                }
                R.id.profile -> {
                    val fragment = ProfileFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .commit()
                    true
                }
                else -> false
            }
        }
        if (savedInstanceState == null) {
            val fragment = AccueilFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit()
        }
    }
}
