package com.donovanSergeAimenHatim.uniroute.model

import android.util.Log
import com.donovanSergeAimenHatim.uniroute.sourceDeDonnées.SourceKelconke

data class ModelUniRoute(
    var detailsHistorique: DetailsHistorique?,
    var historique: Historique?,
    var trajets: Trajets?,
    var profil: Profil?,
    var preferences: Preferences?,
    var utilisateur: Utilisateur?
) {
    data class DetailsHistorique(
        var titre: String,
        var villeDepart: String,
        var date: String,
        var villeDestination: String,
        var nbPassager: Int,
        var priseCharge: String,
        var prixTrajet: String,
        var dureeTrajet: String,
        var distanceTrajet: String,
        var modelVehicule: String
    )

    data class Historique(
        var titre: String,
        var villeDepart: String,
        var date: String,
        var villeDestination: String
    )

    data class Trajets(
        var id: Int,
        var utilisateurID: Int,
        var villeDepart: String,
        var date: String,
        var villeDestination: String,
        var nbPassager: Int,
        var priseCharge: String,
        var prixTrajet: String,
        var dureeTrajet: String,
        var distanceTrajet: String,
        var modelVehicule: String,
        var utilisateursReserves: String
    ) {

        fun getTrajetDescription(): String {
            return "$villeDepart -> $villeDestination"
        }

        fun logTrajetInfo() {
            Log.d("InfoTrajet", "Trajet Info - ID: $id, UtilisateurID: $utilisateurID, VilleDepart: $villeDepart, Date: $date, VilleDestination: $villeDestination, NbPassager: $nbPassager, PriseCharge: $priseCharge, PrixTrajet: $prixTrajet, DureeTrajet: $dureeTrajet, DistanceTrajet: $distanceTrajet, ModelVehicule: $modelVehicule")
        }



    }

    data class Profil(
        var photo: String,
        var nom: String,
        var prénom: String,
        var email: String,
        var téléphone: String,
        var nombre_covoiturage: Int,
        var notes: Double,
        var languesParlées: List<String>,
        var typeVoiture: String,
        var adresse: String
    ) {
        private val _source = SourceKelconke()


        fun obtenirProfils(): MutableList<Profil>? {

            return _source.obtenirProfils()
        }

    }

        data class Preferences(
        var utilisateurÀModifier: String,
        var nouveauNom: String,
        var nouveauPrenom: String,
        var nouvelEmail: String,
        var typeDeVoiture: String,
        var adresse: String,
        var affichageEnKm: Boolean,
        var themeClair: Boolean
    ){
        private var _source = SourceKelconke()
        fun modifierUnProfil( utilisateurÀModifier:String, nom:String, prénom:String, email:String,
                              voiture:String, adresse:String){
            _source.modifierProfil(utilisateurÀModifier, nom, prénom, email, voiture, adresse)

        }
    }

    data class Utilisateur(
        var id: Int,
        var nom: String,
        var prenom: String,
        var email: String,
        var telephone: String,
        var adresse: String,
        var voiture: String,
        var note: Double,
        var langue: String,
        var photo: String
    )

}
