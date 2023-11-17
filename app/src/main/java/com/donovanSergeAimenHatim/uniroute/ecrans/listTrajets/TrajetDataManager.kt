package com.donovanSergeAimenHatim.uniroute.ecrans.listTrajets

import android.util.Log
import android.widget.Toast
import com.donovanSergeAimenHatim.uniroute.sourceDeDonnées.SourceKelconke
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
    class TrajetDataManager(private val source: SourceKelconke) {

        suspend fun getTrajets(nomTable: String, colonne: String = "*", condition: String = ""): List<Trajets> {
            val trajetsJson = source.obtenirDonnées(nomTable, colonne, condition, ::transformJsonToTrajets)
            return trajetsJson ?: emptyList()
        }

        fun transformJsonToTrajets(jsonString: String): List<Trajets> {
            Log.d("listTrajets", "JSON String: $jsonString")
            val gson = Gson()
            val type = object : TypeToken<List<String>>() {}.type
            val trajetsStrings: List<String> = gson.fromJson(jsonString, type)
            return trajetsStrings.map { trajetString ->
                val parts = trajetString.split("|")
                Trajets(
                    id = parts[0].toInt(),
                    utilisateurId = parts[1].toInt(),
                    villeDepart = parts[2],
                    date = parts[3],
                    villeDestination = parts[4],
                    nbPassager = parts[5].toInt(),
                    priseCharge = parts[6]
                )
            }
        }
    }