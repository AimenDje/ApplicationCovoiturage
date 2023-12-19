package com.donovanSergeAimenHatim.uniroute.ecrans.historique

import android.util.Log
import com.donovanSergeAimenHatim.uniroute.ecrans.listTrajets.TrajetDataManager
import com.donovanSergeAimenHatim.uniroute.ecrans.listTrajets.Trajets
import com.donovanSergeAimenHatim.uniroute.sourceDeDonnées.SourceKelconke
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PrésentateurDétailsHistorique (val vue : DétailsHistoriqueFragment){

    /* // Données statique
        var trajet4 = ModèleDétailsHistorique("Détails du trajet",
        "Ville de départ : Edmonton",
        "Date de demande                       Vendredi 3 novembre 2023",
        "Ville de destination : Winnipeg",
        3,
        "",
        "Prix du trajet : 156 \$",
        "Durée : 10 h 46 min.",
        "Distance : 1305 km.",
        "Type de véhicule                                                          Rolls-Royce Phantom");
    */

    private lateinit var trajetDataManager : TrajetDataManager
    suspend fun ChargerDetailsHistorique(trajetId : Int): ModèleDétailsHistorique? {
            val sourceKelconque = SourceKelconke()
            trajetDataManager = TrajetDataManager(sourceKelconque)
            val trajeth = withContext(Dispatchers.IO) {
                trajetDataManager.getTrajetById(trajetId)
            }
        return if (trajeth != null) {
            Log.d("historiqueTrajet", "NON NULL ${trajeth.villeDepart}")
            ModèleDétailsHistorique(
                "Détails du trajets", trajeth.villeDepart, trajeth.date, trajeth.villeDestination,
                trajeth.nbPassager, trajeth.priseCharge, trajeth.prixTrajet, trajeth.dureeTrajet,
                trajeth.distanceTrajet, trajeth.modelVehicule
            )
        } else {
            Log.d("getTrajetById", "NULL")
            null
        }
    }


}