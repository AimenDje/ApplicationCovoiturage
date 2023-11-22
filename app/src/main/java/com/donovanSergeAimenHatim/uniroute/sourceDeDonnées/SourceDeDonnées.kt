package com.donovanSergeAimenHatim.uniroute.sourceDeDonnées

import com.donovanSergeAimenHatim.uniroute.ecrans.profil.ModèleProfile
import java.sql.ResultSet

interface SourceDeDonnées {
    fun obtenirProfils (): List<ModèleProfile>?
    suspend fun <T> obtenirDonnées(
        nomTable: String,
        colonne: String,
        condition: String,
        transform: (String) -> T
    ): T?

    suspend fun ajouterDonnee(
        nomTable: String,
        donnees: Map<String, Any>
    ): Boolean
}

