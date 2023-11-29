package com.donovanSergeAimenHatim.uniroute.trajet

import android.icu.util.Calendar
import android.util.Log

import androidx.compose.ui.text.intl.Locale
import com.donovanSergeAimenHatim.uniroute.sourceDeDonnées.SourceKelconke
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


import android.widget.DatePicker
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*



class TrajetDataManager(private val source: SourceKelconke) {

    suspend fun getTrajet(nomTable: String, colonne: String = "*", condition: String = ""): List<Trajet>{
        val trajetJson = source.obtenirDonnées(nomTable, colonne, condition, ::transformJsonToTrajets)
        return trajetJson ?: emptyList()
    }

    suspend fun getTrajetById(id : Int): Trajet?{
        val condition = "id=$id"
        val trajets = getTrajet("trajet","*",condition)
        return trajets.firstOrNull()
    }

    fun transformJsonToTrajets(jsonString: String): List<Trajet> {
        Log.d("listeTrajet", "JSON String: $jsonString")
        val gson = Gson()
        val type = object : TypeToken<List<String>>() {}.type
        val trajetsString: List<String> = gson.fromJson(jsonString, type)

        return trajetsString.map { trajetsString ->
            val parts = trajetsString.split("|")
            Trajet(
                id = parts[0].toInt(),
                utilisateurID = parts[1].toInt(),
                villeDepart = parts[2],
                date = parts[3],
                villeDestination = parts[4],
                nbPassager = parts[5].toInt(),
                priseCharge = parts[6],
                prixTrajet = parts[7],
                dureeTrajet = parts[8],
                distanceTrajet = parts[9],
                modelVehicule = parts[10]
            )

        }
    }
}