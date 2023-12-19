package com.donovanSergeAimenHatim.uniroute.ecrans.historique

import com.donovanSergeAimenHatim.uniroute.model.ModelUniRoute

interface HistoriqueInterface {
    interface View {
        fun afficherHistorique(historique: List<ModelUniRoute.Trajets>)
        fun afficherErreur(message: String)
    }
    interface Presenter {
        fun chargerTrajets(condition: String)

    }


}