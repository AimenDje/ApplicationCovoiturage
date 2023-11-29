package com.donovanSergeAimenHatim.uniroute.ecrans.historique

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.donovanSergeAimenHatim.uniroute.R

class DétailsHistoriqueFragment : Fragment(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

        var historique: ModèleHistorique
        var présentateur_détails_historique = PrésentateurDétailsHistorique(this)

        /*var id_utilisateur = 3 // ID utilisateur
        GlobalScope.launch(Dispatchers.Main) {
            historique = présentateur_détails_historique.
            if (historique != null) {


            }
        }*/



    }
}