package com.donovanSergeAimenHatim.uniroute.ecrans.historique

import com.donovanSergeAimenHatim.uniroute.ecrans.listTrajets.Trajets

interface HistoriqueInterface {
    interface View {
        fun afficherHistorique(trajets: List<Trajets>)
        fun afficherErreur(message: String)
    }
    interface Presenter {
        fun chargerTrajets(condition: String)

    }


}