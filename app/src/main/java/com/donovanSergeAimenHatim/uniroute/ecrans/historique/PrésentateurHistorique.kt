package com.donovanSergeAimenHatim.uniroute.ecrans.historique

import com.donovanSergeAimenHatim.uniroute.ecrans.listTrajets.TrajetDataManager
import com.donovanSergeAimenHatim.uniroute.ecrans.listTrajets.Trajets
import com.donovanSergeAimenHatim.uniroute.ecrans.listTrajets.TrajetsContract
import com.donovanSergeAimenHatim.uniroute.sourceDeDonnées.SourceKelconke
import com.donovanSergeAimenHatim.uniroute.utilisateur.Utilisateur
import com.donovanSergeAimenHatim.uniroute.utilisateur.UtilisateurDataManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PrésentateurHistorique (val view : HistoriqueInterface.View, private val dataManager: TrajetDataManager,private val utilisateurDataManager: UtilisateurDataManager): HistoriqueInterface.Presenter {
    private lateinit var trajetDataManager : TrajetDataManager

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

    override fun chargerTrajets(condition: String) {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val trajets = withContext(Dispatchers.IO) {
                    dataManager.getTrajets("trajets","*",condition)
                }
                view.afficherHistorique(trajets)
            } catch (e: Exception) {
                view.afficherErreur(e.message ?: "Erreur inconnue")
            }
        }
    }

    suspend fun chargerUtilisateur(id: Int): Utilisateur? {
        return try {
            withContext(Dispatchers.IO) {
                utilisateurDataManager.getUtilisateurById(id)
            }
        } catch (e: Exception) {
            null
        }
    }


}