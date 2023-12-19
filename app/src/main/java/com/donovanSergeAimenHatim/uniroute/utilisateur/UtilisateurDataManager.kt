package com.donovanSergeAimenHatim.uniroute.utilisateur

import android.util.Log
import com.donovanSergeAimenHatim.uniroute.sourceDeDonnées.SourceKelconke
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class UtilisateurDataManager(private val source: SourceKelconke) {

    suspend fun getUtilisateur(nomTable: String, colonne: String = "*", condition: String = ""): List<Utilisateur> {
        val utilisateurJson = source.obtenirDonnées(nomTable, colonne, condition, ::transformJsonToUtilisateurs)
        return utilisateurJson ?: emptyList()
    }

    suspend fun getUtilisateurById(id: Int): Utilisateur? {
        val condition = "id=$id"
        val utilisateurs = getUtilisateur("utilisateur", "*", condition)
        return utilisateurs.firstOrNull()
    }
    suspend fun modifierUtilisateurByID(utilisateurID: String, nouvellesDonnees:Map<String, Any>):Boolean{
        val table = "utilisateur"
        return source.modifierDonnee(table, nouvellesDonnees, utilisateurID)
    }


    fun transformJsonToUtilisateurs(jsonString: String): List<Utilisateur> {
        Log.d("listUtilisateur", "JSON String: $jsonString")
        val gson = Gson()
        val type = object : TypeToken<List<String>>() {}.type
        val utilisateursString: List<String> = gson.fromJson(jsonString, type)

        return utilisateursString.map { utilisateurString ->
            val parts = utilisateurString.split("|")
            Utilisateur(
                id = parts[0].toInt(),
                nom = parts[1],
                prenom = parts[2],
                email = parts[3],
                telephone = parts[4],
                adresse = parts[5],
                voiture = parts[6],
                note = parts[7].toDouble(),
                langue = parts[8],
                photo = parts[9]
            )
        }
    }
}