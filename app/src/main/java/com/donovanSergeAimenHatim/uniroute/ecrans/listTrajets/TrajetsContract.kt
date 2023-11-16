package com.donovanSergeAimenHatim.uniroute.ecrans.listTrajets

import com.donovanSergeAimenHatim.uniroute.utilisateur.Utilisateur

interface TrajetsContract {
    interface View {
        fun afficherTrajets(trajets: List<Trajets>)
        fun afficherTrajetSelectionne(trajet: Trajets)
        fun afficherErreur(message: String)
        fun afficherUtilisateur(utilisateur: Utilisateur)
    }

    interface Presenter {
        fun chargerTrajets(condition: String)
        fun onTrajetSelectionne(trajet: Trajets)
    }
}
