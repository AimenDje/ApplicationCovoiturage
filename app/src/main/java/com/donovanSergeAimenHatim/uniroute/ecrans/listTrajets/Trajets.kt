package com.donovanSergeAimenHatim.uniroute.ecrans.listTrajets

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



}
