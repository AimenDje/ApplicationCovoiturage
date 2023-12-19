package com.donovanSergeAimenHatim.uniroute.ecrans.historique

import android.util.Log
import com.donovanSergeAimenHatim.uniroute.ecrans.listTrajets.TrajetDataManager
import com.donovanSergeAimenHatim.uniroute.model.ModelUniRoute
import com.donovanSergeAimenHatim.uniroute.utilisateur.UtilisateurDataManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PrésentateurHistorique (val view : HistoriqueInterface.View, private val dataManager: TrajetDataManager,private val utilisateurDataManager: UtilisateurDataManager): HistoriqueInterface.Presenter {
    override fun chargerTrajets(condition: String) {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val trajets = withContext(Dispatchers.IO) {
                    dataManager.getTrajets("trajets","*",condition)
                }
                view.afficherHistorique(trajets)
            } catch (e: Exception) {
                view.afficherErreur(e.message ?: "Erreur inconnue")
                e.message?.let { Log.d("ErreurTrajets", it) }
            }
        }
    }

    suspend fun chargerUtilisateur(id: Int): ModelUniRoute.Utilisateur? {
        return try {
            withContext(Dispatchers.IO) {
                utilisateurDataManager.getUtilisateurById(id)
            }
        } catch (e: Exception) {
            null
        }
    }


}