package com.donovanSergeAimenHatim.uniroute.ecrans.accueil

import kotlinx.coroutines.CoroutineScope

interface AjoutTrajetContract {
    interface View {
        fun afficherSuccesAjout()
        fun afficherErreurAjout(message: String)

    }

    interface Presenter {
        fun ajouterTrajet(scope: CoroutineScope, donneesTrajet: Map<String, String>)

    }

    interface Model {
        suspend fun ajouterTrajet(donneesTrajet: Map<String, String>): Boolean

    }
}
