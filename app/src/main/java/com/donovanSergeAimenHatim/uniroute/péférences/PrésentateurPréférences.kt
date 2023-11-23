package com.donovanSergeAimenHatim.uniroute.péférences

import com.donovanSergeAimenHatim.uniroute.ecrans.profil.ModèleProfile

class PrésentateurPréférences(private val view:PreferenceFragment) {
    private val model = ModèlePréférences()
    fun mettreAJourPréférence( utilisateurÀModifier:String,
        nouveauNom:String, nouveauPrénom:String, nouvelEmail:String,
        nouveauTypedeVoiture:String, nouvelleAdresse : String
    ){

        model.modifierUnProfil(utilisateurÀModifier, nouveauNom, nouveauPrénom,
            nouvelEmail, nouveauTypedeVoiture, nouvelleAdresse)

    }


}