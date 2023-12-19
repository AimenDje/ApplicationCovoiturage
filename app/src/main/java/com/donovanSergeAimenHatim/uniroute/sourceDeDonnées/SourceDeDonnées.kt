package com.donovanSergeAimenHatim.uniroute.sourceDeDonnées

import com.donovanSergeAimenHatim.uniroute.model.ModelUniRoute

interface SourceDeDonnées {
    fun obtenirProfils (): MutableList<ModelUniRoute.Profil>?
    fun modifierProfil(utilisateurÀModifier:String, nom:String, prenom:String, email:String, voiture:String, adresse:String){}
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

    suspend fun modifierDonnee(
        nomTable: String,
        donnees: Map<String, Any>,
        id: String
    ): Boolean
}

