package com.donovanSergeAimenHatim.uniroute.ecrans.accueil

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class AjoutTrajetPresenter(private val view: AjoutTrajetContract.View,
                           private val model: AjoutTrajetContract.Model) : AjoutTrajetContract.Presenter {

    override fun ajouterTrajet(scope: CoroutineScope, donneesTrajet: Map<String, String>) {
        scope.launch {
            val succes = model.ajouterTrajet(donneesTrajet)
            if (succes) {
                view.afficherSuccesAjout()
            } else {
                view.afficherErreurAjout("Erreur lors de l'ajout du trajet.")
            }
        }
    }
}
