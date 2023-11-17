package com.donovanSergeAimenHatim.uniroute.sourceDeDonnées

import android.content.Context
import android.util.Log
import android.widget.Toast
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

    private val client = HttpClient(CIO)
    override suspend fun <T> obtenirDonnées(
        nomTable: String,
        colonne: String,
        condition: String,
        transform: (String) -> T
    ): T? {
        return withContext(Dispatchers.IO) {
            try {
                val url = "https://donovanbeulze.com/unirouteAPI/"
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


