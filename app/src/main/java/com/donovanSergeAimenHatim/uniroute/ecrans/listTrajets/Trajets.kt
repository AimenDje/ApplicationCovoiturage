package com.donovanSergeAimenHatim.uniroute.ecrans.listTrajets

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Trajets(val id: Int,
              val utilisateurID: Int,
              val villeDepart: String,
              val date: String,
              val villeDestination: String,
              val nbPassager: Int,
              val priseCharge: String,
              val prixTrajet : String,
              val dureeTrajet : String,
              val distanceTrajet : String,
              val modelVehicule : String) {

    fun getTrajetDescription(): String {
        return "$villeDepart -> $villeDestination"
    }

    fun logTrajetInfo() {
        Log.d("InfoTrajet", "Trajet Info - ID: $id, UtilisateurID: $utilisateurID, VilleDepart: $villeDepart, Date: $date, VilleDestination: $villeDestination, NbPassager: $nbPassager, PriseCharge: $priseCharge, PrixTrajet: $prixTrajet, DureeTrajet: $dureeTrajet, DistanceTrajet: $distanceTrajet, ModelVehicule: $modelVehicule")
    }



}
