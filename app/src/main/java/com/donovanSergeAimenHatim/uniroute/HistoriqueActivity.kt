package com.donovanSergeAimenHatim.uniroute

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.donovanSergeAimenHatim.uniroute.ecrans.historique.MontrealTorontoFragment

class HistoriqueActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_historique)

        // Charger le fragment fragment Montréal Toronto une fois que l'on clique sur le bouton flèche ver le bas pour plus de détails
        val boutonHistoriqueView = findViewById<Button>(R.id.trajet1)
        boutonHistoriqueView.setOnClickListener {
            val fragment = MontrealTorontoFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit()
        }
    }
}