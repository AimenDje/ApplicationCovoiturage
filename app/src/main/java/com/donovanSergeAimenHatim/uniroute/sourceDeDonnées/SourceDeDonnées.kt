package com.donovanSergeAimenHatim.uniroute.sourceDeDonnées

import java.sql.ResultSet

interface SourceDeDonnées {
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

