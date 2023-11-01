package com.donovanSergeAimenHatim.uniroute

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.donovanSergeAimenHatim.uniroute.ecrans.accueil.AccueilFragment
import com.donovanSergeAimenHatim.uniroute.ecrans.messagerie.MessagerieFragment
import com.donovanSergeAimenHatim.uniroute.ecrans.profile.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottonnav)
        bottomNavigationView.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.coVoiturage -> {
                    val fragment = AccueilFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .commit()
                    true
                }
                R.id.messagerie -> {
                    val fragment = MessagerieFragment()
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

        // Charger le premier fragment par défaut
        if (savedInstanceState == null) {
            val fragment = AccueilFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit()
        }
    }
}
