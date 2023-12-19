package com.donovanSergeAimenHatim.uniroute.ecrans.historique

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.donovanSergeAimenHatim.uniroute.R
import kotlinx.coroutines.launch

class DétailsHistoriqueFragment : Fragment(){
    var historique: ModèleDétailsHistorique? = null
    var idTrajet: String? = null
    lateinit var présentateur_détails_historique : PrésentateurDétailsHistorique
    private lateinit var loadingLogo: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        idTrajet = arguments?.getString("idTrajet")

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        présentateur_détails_historique = PrésentateurDétailsHistorique(this)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details_historique, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var titre_vue : TextView = view.findViewById(R.id.titreDetailsTrajet)
        var itinéraireTrajet : TextView = view.findViewById(R.id.textViewTrajet)
        var trajet : TextView = view.findViewById(R.id.itineraireTrajet)
        var lePrix : TextView = view.findViewById(R.id.textViewPrix)
        var prix : TextView = view.findViewById(R.id.prixTrajet)
        var laDurée : TextView = view.findViewById(R.id.textViewDuree)
        var durée : TextView = view.findViewById(R.id.dureeTrajet)
        var laDistance : TextView = view.findViewById(R.id.textViewDistance)
        var distance : TextView = view.findViewById(R.id.distanceTrajet)
        var leVehicule : TextView = view.findViewById(R.id.textViewVehicule)
        var véhicule : TextView = view.findViewById(R.id.vehiculeType)
        var laDate : TextView = view.findViewById(R.id.textViewDate)
        var date : TextView = view.findViewById(R.id.dateDemande)
        loadingLogo = view.findViewById<LinearLayout>(R.id.loadingPanel_detailsHistorique)



        lifecycleScope.launch {
            historique = idTrajet?.let { présentateur_détails_historique.ChargerDetailsHistorique(it.toInt()) }
            if (historique != null) {
                titre_vue.text = historique!!.titre
                itinéraireTrajet.text = "Trajet : "
                trajet.text = "${historique!!.villeDepart} -> ${historique!!.villeDestination}" + "\n"
                lePrix.text = "Prix : "
                prix.text = historique!!.prixTrajet + " $"+ "\n"
                laDurée.text = "Durée du trajet : "
                durée.text = historique!!.dureeTrajet + "\n"
                laDistance.text = "Distance du trajet : "
                distance.text =  historique!!.distanceTrajet + " Km." + "\n"
                leVehicule.text = "Model du vehicule : "
                véhicule.text = historique!!.modelVehicule + "\n"
                laDate.text = "Date du trajet : "
                date.text = historique!!.date
                loadingLogo.visibility = View.GONE
            } else {
                // Gérer le cas où historique est null, par exemple afficher un message d'erreur
                Toast.makeText(context, "Erreur : les détails du trajet ne peuvent pas être chargés.", Toast.LENGTH_SHORT).show()
            }
        }
    }


}