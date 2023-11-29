package com.donovanSergeAimenHatim.uniroute.ecrans.historique

import com.donovanSergeAimenHatim.uniroute.sourceDeDonnées.SourceKelconke
import com.donovanSergeAimenHatim.uniroute.trajet.TrajetDataManager
import com.donovanSergeAimenHatim.uniroute.utilisateur.UtilisateurDataManager

class PrésentateurHistorique (val vue : HistoriqueFragment){
    private lateinit var trajetDataManager : TrajetDataManager

    /* // Données statique
    val trajets = ModèleHistorique ("Détails du trajets","Montréal -> Toronto",
        "Calgary -> Vancouver",
        "Mississauga -> Hamilton",
        "Edmonton -> Winnipeg");
     */

    suspend fun ChargerHistorique(userID : Int): ModèleHistorique? {
        val sourceKelconque = SourceKelconke()
        trajetDataManager = TrajetDataManager(sourceKelconque)
        val historique : ModèleHistorique?
            val trajet = trajetDataManager.getTrajetById(userID)
            historique = trajet?.let {
                ModèleHistorique( "Historique des trajets",  trajet.villeDepart,
                 trajet.date, trajet.villeDestination)
            }
        return historique

    }
}