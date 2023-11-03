package com.donovanSergeAimenHatim.uniroute

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.donovanSergeAimenHatim.uniroute.ecrans.historique.HistoriqueFragment

class MessagerieActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_messagerie)

        // Charger le fragment historique une fois que l'on clique sur le bouton historique
        val boutonHistoriqueView = findViewById<Button>(R.id.btnHistorique)
        boutonHistoriqueView.setOnClickListener {
            val fragment = HistoriqueFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit()
        }
    }
}