package com.donovanSergeAimenHatim.uniroute.ecrans.historique

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.donovanSergeAimenHatim.uniroute.R
import com.donovanSergeAimenHatim.uniroute.ecrans.listTrajets.Trajets
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DétailsHistoriqueFragment : Fragment(){

    var idTrajet: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        idTrajet = arguments?.getString("idTrajet")

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details_historique, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var titre_vue : TextView = view.findViewById(R.id.titreDetailsTrajet)
        var trajet : TextView = view.findViewById(R.id.itineraireTrajet)
        var prix : TextView = view.findViewById(R.id.prixTrajet)
        var durée : TextView = view.findViewById(R.id.dureeTrajet)
        var distance : TextView = view.findViewById(R.id.distanceTrajet)
        var véhicule : TextView = view.findViewById(R.id.vehiculeType)
        var heure : TextView = view.findViewById(R.id.heureDemande)
        var date : TextView = view.findViewById(R.id.dateDemande)
        var conducteur : TextView = view.findViewById(R.id.conducteur)

        var historique: ModèleDétailsHistorique?
        var présentateur_détails_historique = PrésentateurDétailsHistorique(this)
      //  historique = idTrajet?.let { présentateur_détails_historique.ChargerDetailsHistorique(it.toInt()) }
        historique = présentateur_détails_historique.ChargerDetailsHistorique(24)
        if (historique != null) {
            titre_vue.text = historique.titre
            trajet.text = "${historique.villeDepart} -> ${historique.villeDestination}"
            prix.text = historique.prixTrajet
            durée.text = historique.dureeTrajet
            distance.text = historique.distanceTrajet
            véhicule.text = historique.modelVehicule
            date.text = historique.date
        }else{
            Toast.makeText(context, "HISTORIQUE NULL", Toast.LENGTH_SHORT).show()
        }
    }


}