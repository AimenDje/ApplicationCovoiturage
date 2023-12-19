package com.donovanSergeAimenHatim.uniroute.ecrans.listTrajets

import com.donovanSergeAimenHatim.uniroute.utilisateur.Utilisateur

interface TrajetsContract {
    interface View {
        fun afficherTrajets(trajets: List<Trajets>)
        fun afficherTrajetSelectionne(trajet: Trajets)
        fun afficherErreur(message: String)
        fun afficherUtilisateur(utilisateur: Utilisateur)
        fun aucunTrajetDisponible()
    }

    interface Presenter {
        fun chargerTrajets(condition: String)
        fun ajouterTrajet(trajet: Trajets)
        fun onTrajetSelectionne(trajet: Trajets)
    }
}
