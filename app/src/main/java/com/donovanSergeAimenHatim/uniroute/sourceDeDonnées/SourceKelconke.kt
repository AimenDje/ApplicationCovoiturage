package com.donovanSergeAimenHatim.uniroute.sourceDeDonnées

import android.content.Context
import android.util.Log
import android.widget.Toast
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.parameter
import io.ktor.client.request.request
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.readText
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
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

    private val client = HttpClient(CIO)
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
                    transform(jsonString) // Transforme le JSON en objet Kotlin
                } else {
                    null // Gestion d'erreur
                }
            } catch (e: Exception) {
                e.printStackTrace()
                null // Gestion d'erreur
            }
        }
    }
}


