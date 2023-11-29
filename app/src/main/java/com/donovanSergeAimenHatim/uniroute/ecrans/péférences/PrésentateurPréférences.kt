package com.donovanSergeAimenHatim.uniroute.ecrans.péférences

import androidx.appcompat.app.AppCompatDelegate

class PrésentateurPréférences(private val view: PreferenceFragment) {
    private val model = ModèlePréférences()
     fun mettreAJourPréférence(utilisateurÀModifier:String,
                                      nouveauNom:String, nouveauPrénom:String, nouvelEmail:String,
                                      nouveauTypedeVoiture:String, nouvelleAdresse : String
    ){

        model.modifierUnProfil(utilisateurÀModifier, nouveauNom, nouveauPrénom,
            nouvelEmail, nouveauTypedeVoiture, nouvelleAdresse)




    }

    fun modifierTheme (themeClair: Boolean) {
        if(themeClair == false) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
        else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }
    fun modifierAffichaeDistance (AffichageDistance:Boolean){

    }




}