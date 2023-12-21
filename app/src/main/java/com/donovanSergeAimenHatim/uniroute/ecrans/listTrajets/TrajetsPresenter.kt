package com.donovanSergeAimenHatim.uniroute.ecrans.listTrajets

import com.donovanSergeAimenHatim.uniroute.model.ModelUniRoute
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
                if(trajets.isEmpty()){
                    view.aucunTrajetDisponible()
                }
                view.afficherTrajets(trajets)
            } catch (e: Exception) {
                view.afficherErreur(e.message ?: "Erreur inconnue")
            }
        }
    }

    override fun ajouterTrajet(trajet: ModelUniRoute.Trajets) {
        trajet.logTrajetInfo()
        GlobalScope.launch(Dispatchers.Main) {
            try {
                if(dataManager.ajouterTrajet(trajet)){
                    //view.afficherErreur("Ajouter avec sucess")
                }else{
                    view.afficherErreur("Ajouter sans sucess")
                }
            } catch (e: Exception) {
                view.afficherErreur(e.message ?: "Erreur inconnue")
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

    override fun onTrajetSelectionne(trajet: ModelUniRoute.Trajets) {
        view.afficherTrajetSelectionne(trajet)
    }

    fun reserverTrajet(trajetId: Int, utilisateurId: Int) {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                if (dataManager.reserverTrajet(trajetId, utilisateurId)) {
                   //view.afficherErreur("Réservation réussie")
                } else {
                    view.afficherErreur("La réservation a échoué")
                }
            } catch (e: Exception) {
                view.afficherErreur(e.message ?: "Erreur inconnue")
            }
        }
    }

    fun annulerTrajet(trajetId: Int, utilisateurId: Int){
        GlobalScope.launch(Dispatchers.Main) {
            try {
                if (dataManager.annulerTrajet(trajetId, utilisateurId)) {
                    //view.afficherErreur("Annulation réussie")

                } else {
                    view.afficherErreur("L'annulation a échoué")
                }
            } catch (e: Exception) {
                view.afficherErreur(e.message ?: "Erreur inconnue")
            }
        }
    }


}
