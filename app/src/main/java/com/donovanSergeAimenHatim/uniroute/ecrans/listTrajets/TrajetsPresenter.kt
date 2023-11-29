package com.donovanSergeAimenHatim.uniroute.ecrans.listTrajets

import android.util.Log
import com.donovanSergeAimenHatim.uniroute.utilisateur.Utilisateur
import com.donovanSergeAimenHatim.uniroute.utilisateur.UtilisateurDataManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TrajetsPresenter(val view: TrajetsContract.View, private val dataManager: TrajetDataManager,private val utilisateurDataManager: UtilisateurDataManager) : TrajetsContract.Presenter {
    override fun chargerTrajets(condition: String) {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val trajets = withContext(Dispatchers.IO) {
                    dataManager.getTrajets("trajets","*",condition)
                }
                view.afficherTrajets(trajets)
            } catch (e: Exception) {
                view.afficherErreur(e.message ?: "Erreur inconnue")
            }
        }
    }

    override fun ajouterTrajet(trajet: Trajets) {
        trajet.logTrajetInfo()
        GlobalScope.launch(Dispatchers.Main) {
            try {
                if(dataManager.ajouterTrajet(trajet)){
                    view.afficherErreur("Ajouter avec sucess")
                }else{
                    view.afficherErreur("Ajouter sans sucess")
                }
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

    override fun onTrajetSelectionne(trajet: Trajets) {
        view.afficherTrajetSelectionne(trajet)
    }


}
