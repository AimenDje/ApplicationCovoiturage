package com.donovanSergeAimenHatim.uniroute

import android.app.Application

// Déclaration de la classe UniRouteApp qui hérite de Application
class UniRouteApp : Application() {
    // Variable privée pour stocker l'identifiant de l'utilisateur
    private var utilisateurId: Int = 99 // ID par défaut est 99, pour Patrick Lafrance

    // Surcharge de la méthode onCreate, appelée lors de la création de l'application
    override fun onCreate() {
        super.onCreate()
        // Ici, vous pourriez initialiser des ressources globales si nécessaire
    }

    // Méthode publique pour obtenir l'ID de l'utilisateur actuellement stocké
    fun getUtilisateurID(): Int {
        return utilisateurId
    }

    // Méthode publique pour définir un nouvel ID utilisateur
    fun setUtilisateurID(nouvelleID: Int) {
        utilisateurId = nouvelleID
    }
}
