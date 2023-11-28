package com.donovanSergeAimenHatim.uniroute.péférences

import com.donovanSergeAimenHatim.uniroute.ecrans.profil.ModèleProfile
import com.donovanSergeAimenHatim.uniroute.sourceDeDonnées.SourceKelconke

data class ModèlePréférences( var utilisateurÀModifier:String = "",
                              var nouveauNom: String = "",
                              var nouveauPrenom: String = "",
                              var nouvelEmail: String = "",
                              var typeDeVoiture: String = "",
                              var adresse : String = "",
                              var affichageEnKm: Boolean = true,
                              var themeClair: Boolean = false) {

    private var _source = SourceKelconke()

    fun modifierUnProfil( utilisateurÀModifier:String, nom:String, prénom:String, email:String,
                          voiture:String, adresse:String){
        _source.modifierProfil(utilisateurÀModifier, nom, prénom, email, voiture, adresse)

    }
    fun modifierTheme (themeClair: Boolean){

    }

    fun modifierAffichageDistance (themeClair: Boolean){

    }
}