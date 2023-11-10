package com.donovanSergeAimenHatim.uniroute.ecrans.connexion

interface Model {
    interface View {
        fun afficherChargement()
        fun cacherChargement()
        fun affichageConnexionRéussie(username: String)
        fun affichageConnexionÉchouée(error: String)
    }

    interface Presenter {
        fun seConnecter(username: String, password: String)
    }
}