package com.donovanSergeAimenHatim.uniroute.ecrans.listTrajets

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Trajets(
    val id: Int,
    val utilisateurId: Int,
    val villeDepart: String,
    val villeDestination: String,
    val date: String,
    val nbPassager: Int,
    val priseCharge: String
) {

    fun getTrajetDescription(): String {
        return "$villeDepart -> $villeDestination"
    }



}
