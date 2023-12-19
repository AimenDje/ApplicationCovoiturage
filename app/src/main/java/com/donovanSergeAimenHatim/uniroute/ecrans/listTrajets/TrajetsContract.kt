package com.donovanSergeAimenHatim.uniroute.ecrans.listTrajets

import com.donovanSergeAimenHatim.uniroute.model.ModelUniRoute

interface TrajetsContract {
    interface View {
        fun afficherTrajets(trajets: List<ModelUniRoute.Trajets>)
        fun afficherTrajetSelectionne(trajet: ModelUniRoute.Trajets)
        fun afficherErreur(message: String)
        fun afficherUtilisateur(utilisateur: ModelUniRoute.Utilisateur)
        fun aucunTrajetDisponible()
    }

    interface Presenter {
        fun chargerTrajets(condition: String)
        fun ajouterTrajet(trajet: ModelUniRoute.Trajets)
        fun onTrajetSelectionne(trajet: ModelUniRoute.Trajets)
    }
}
