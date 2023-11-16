package com.donovanSergeAimenHatim.uniroute.péférences

class PrésentateurPréférences(private val view:PreferenceFragment) {
    private val model = ModèlePréférences()
    fun mettreAJourPréférence(
        nouveauNom:String, nouveauPrénom:String, email:String,
        typedeVoiture:String, adresse : String, affichageEnKm:Boolean,
        themeSombre:Boolean
    ){
        model.nouveauNom = nouveauNom
        model.nouveauPrenom = nouveauPrénom
        model.nouvelEmail = email
        model.adresse = adresse
        model.typeDeVoiture = typedeVoiture
        model.affichageEnKm = affichageEnKm
        model.themeClair = themeSombre

        sauvegarderPréférences()


    }

    private fun sauvegarderPréférences(){
        // Logique pour sauvegarder les préférences (dans une base de données ou dans une sources de données)
    }
}