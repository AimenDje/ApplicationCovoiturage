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

        var présentateur_détails_histaurique = PrésentateurDétailsHistorique(this)

        // Obtenir les données du trajet depuis les arguments du fragment
        titre_vue.text = présentateur_détails_histaurique.trajet1.titre
        trajet.text = présentateur_détails_histaurique.trajet1.itineraireTrajet
        prix.text = présentateur_détails_histaurique.trajet1.prixTrajet
        durée.text = présentateur_détails_histaurique.trajet1.dureeTrajet
        distance.text = présentateur_détails_histaurique.trajet1.distanceTrajet
        véhicule.text = présentateur_détails_histaurique.trajet1.vehiculeType
        heure.text = présentateur_détails_histaurique.trajet1.heureDemande
        date.text = présentateur_détails_histaurique.trajet1.dateDemande
        conducteur.text = présentateur_détails_histaurique.trajet1.conducteur


        titre_vue.text = présentateur_détails_histaurique.trajet2.titre
        trajet.text = présentateur_détails_histaurique.trajet2.itineraireTrajet
        prix.text = présentateur_détails_histaurique.trajet2.prixTrajet
        durée.text = présentateur_détails_histaurique.trajet2.dureeTrajet
        distance.text = présentateur_détails_histaurique.trajet2.distanceTrajet
        véhicule.text = présentateur_détails_histaurique.trajet2.vehiculeType
        heure.text = présentateur_détails_histaurique.trajet2.heureDemande
        date.text = présentateur_détails_histaurique.trajet2.dateDemande
        conducteur.text = présentateur_détails_histaurique.trajet2.conducteur


        titre_vue.text = présentateur_détails_histaurique.trajet3.titre
        trajet.text = présentateur_détails_histaurique.trajet3.itineraireTrajet
        prix.text = présentateur_détails_histaurique.trajet3.prixTrajet
        durée.text = présentateur_détails_histaurique.trajet3.dureeTrajet
        distance.text = présentateur_détails_histaurique.trajet3.distanceTrajet
        véhicule.text = présentateur_détails_histaurique.trajet3.vehiculeType
        heure.text = présentateur_détails_histaurique.trajet3.heureDemande
        date.text = présentateur_détails_histaurique.trajet3.dateDemande
        conducteur.text = présentateur_détails_histaurique.trajet3.conducteur


        titre_vue.text = présentateur_détails_histaurique.trajet4.titre
        trajet.text = présentateur_détails_histaurique.trajet4.itineraireTrajet
        prix.text = présentateur_détails_histaurique.trajet4.prixTrajet
        durée.text = présentateur_détails_histaurique.trajet4.dureeTrajet
        distance.text = présentateur_détails_histaurique.trajet4.distanceTrajet
        véhicule.text = présentateur_détails_histaurique.trajet4.vehiculeType
        heure.text = présentateur_détails_histaurique.trajet4.heureDemande
        date.text = présentateur_détails_histaurique.trajet4.dateDemande
        conducteur.text = présentateur_détails_histaurique.trajet4.conducteur

    }
}