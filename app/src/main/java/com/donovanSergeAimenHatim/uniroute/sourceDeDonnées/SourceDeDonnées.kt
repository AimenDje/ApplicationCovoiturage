package com.donovanSergeAimenHatim.uniroute.sourceDeDonnées

import java.sql.ResultSet

interface SourceDeDonnées {
    suspend fun <T> obtenirDonnées(
        nomTable: String,
        colonne: String,
        condition: String,
        transform: (String) -> T
    ): T?
}

