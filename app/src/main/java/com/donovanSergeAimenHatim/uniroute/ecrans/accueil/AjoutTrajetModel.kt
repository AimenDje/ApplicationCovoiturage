package com.donovanSergeAimenHatim.uniroute.ecrans.accueil

import com.donovanSergeAimenHatim.uniroute.sourceDeDonnées.SourceKelconke

class AjoutTrajetModel(private val source: SourceKelconke) : AjoutTrajetContract.Model {
    override suspend fun ajouterTrajet(donneesTrajet: Map<String, String>): Boolean {
        return source.ajouterDonnee("trajets", donneesTrajet)
    }
}
