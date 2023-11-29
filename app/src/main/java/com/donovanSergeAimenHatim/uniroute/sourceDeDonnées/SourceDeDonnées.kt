package com.donovanSergeAimenHatim.uniroute.sourceDeDonnées

import com.donovanSergeAimenHatim.uniroute.ecrans.profil.ModèleProfile
import java.sql.ResultSet

interface SourceDeDonnées {
    fun obtenirProfils (): MutableList<ModèleProfile>?
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


}

