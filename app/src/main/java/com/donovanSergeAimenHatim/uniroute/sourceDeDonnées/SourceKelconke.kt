package com.donovanSergeAimenHatim.uniroute.sourceDeDonnées

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.donovanSergeAimenHatim.uniroute.ecrans.profil.ModèleProfile
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.request
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.readText
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.http.Parameters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

class SourceKelconke() : SourceDeDonnées {
    companion object{
        val profils = mutableListOf(

        ModèleProfile("arnold", "Gauthier", "Arnold", "argauthier@gmail.com ", "(514) 541-1452", 250 , 4.5 ,listOf("ang", "esp"), "Audi RS6", "adresse"),
        ModèleProfile("serge", "Milongo", "Serge", "sauthier@gmail.com ", "(514) 587-1457", 80 , 3.5,listOf("fran", "ang"), "Audi R8", "adresse2"),
        ModèleProfile("aimen", "Djemaoune", "Aimen", "djeaimen@gmail.com", "(514) 123-4567", 120, 4.6, listOf("fran", "esp"), "BMW X5", "adresse3"),
        ModèleProfile("donovan", "Beulze", "Donovan", "DonBeulze@gmail.com", "(514) 789-0123", 180, 4.0, listOf("ang", "fran"), "Mercedes-Benz C-Class", "adresse4"),
        ModèleProfile("hatim", "Hatimi", "hatim", "thatimie@gmail.com", "(514) 987-6543", 300, 4.5, listOf("ang", "fran", "esp"), "Tesla Model S", "adresse5")
    )
    }

private val client = HttpClient(CIO)

    //Simulation de données pour les profils
    override fun obtenirProfils(): MutableList<ModèleProfile>{

        return profils
    }

    override fun modifierProfil(
        utilisateurÀModifier: String,
        nom: String,
        prénom: String,
        email: String,
        voiture: String,
        adresse: String
    ) {
        for(utilisateur in profils){
            if(utilisateur.nom == utilisateurÀModifier){
                val utilisateurModifié = utilisateur.copy (photo = utilisateur.photo, nom =nom, prénom = prénom, email =  email,
                    typeVoiture = voiture, adresse = adresse
                )
                val index = profils.indexOf(utilisateur)
                if (index != -1) {
                    profils[index] = utilisateurModifié

                }


            }


        }

    }



override suspend fun <T> obtenirDonnées(
        nomTable: String,
        colonne: String,
        condition: String,
        transform: (String) -> T
    ): T? {
        return withContext(Dispatchers.IO) {
            try {
                val url = "https://donovanbeulze.com/unirouteAPI/" // Remplacez avec l'URL de votre API
                val response: HttpResponse = client.request(url) {
                    method = HttpMethod.Get
                    parameter("table", nomTable)
                    parameter("colonne", colonne)
                    parameter("critere", condition)
                }

                if (response.status == HttpStatusCode.OK) {
                    val jsonString = response.readText()
                    transform(jsonString)
                } else {
                    null
                }
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }

    override suspend fun ajouterDonnee(
        nomTable: String,
        donnees: Map<String, Any>
    ): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val url = "https://donovanbeulze.com/unirouteAPI/ajouter.php"
                val formData = Parameters.build {
                    append("table", nomTable)
                    donnees.forEach { (key, value) ->
                        append(key, value.toString())
                    }
                }
                val response: HttpResponse = client.post(url) {
                    body = formData
                }

                response.status == HttpStatusCode.OK
            } catch (e: Exception) {
                e.printStackTrace()
                false
            }
        }
    }
}


