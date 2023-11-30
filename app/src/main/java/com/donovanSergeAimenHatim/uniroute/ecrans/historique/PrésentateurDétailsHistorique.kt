package com.donovanSergeAimenHatim.uniroute.ecrans.historique

import com.donovanSergeAimenHatim.uniroute.ecrans.listTrajets.TrajetDataManager
import com.donovanSergeAimenHatim.uniroute.sourceDeDonnées.SourceKelconke

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
    suspend fun ChargerDetailsHistorique(userID : Int): ModèleDétailsHistorique? {
        val sourceKelconque = SourceKelconke()
        trajetDataManager = TrajetDataManager(sourceKelconque)
        val detailsHistorique : ModèleDétailsHistorique?
        val trajet = trajetDataManager.getTrajetById(userID)
        detailsHistorique = trajet?.let {
            ModèleDétailsHistorique( "Détails du trajets", trajet.villeDepart,
                trajet.date, trajet.villeDestination,
                trajet.nbPassager, trajet.priseCharge,
                trajet.prixTrajet, trajet.dureeTrajet, trajet.distanceTrajet, trajet.modelVehicule)
        }
        return detailsHistorique

    }


}