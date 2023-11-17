package com.donovanSergeAimenHatim.uniroute.ecrans.connexion

interface Model {
    interface View {
        fun afficherChargement()
        fun cacherChargement()
        fun afficherConnexionRéussie(username: String)
        fun afficherConnexionÉchouée(error: String)
    }

    interface Presenter {
        fun seConnecter(username: String, password: String)
    }
}