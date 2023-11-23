package com.donovanSergeAimenHatim.uniroute.ecrans.profil

import com.donovanSergeAimenHatim.uniroute.sourceDeDonnées.SourceDeDonnées
import com.donovanSergeAimenHatim.uniroute.sourceDeDonnées.SourceKelconke

data class ModèleProfile(
    var photo:String = "",
    var nom:String = "",
    var prénom: String = "",
    var email : String = "",
    var téléphone:String = "",
    var nombre_covoiturage : Int = 0, var notes: Double = 0.0,
    val languesParlées: List<String> = listOf("français", "anglais", "espagnol"),
    var typeVoiture : String = "",
    var adresse: String  = "",
 ) {
    private val _source = SourceKelconke()


    fun obtenirProfils (): MutableList<ModèleProfile>? {

        return _source.obtenirProfils()
    }

}

