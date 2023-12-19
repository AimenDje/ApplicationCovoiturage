package com.donovanSergeAimenHatim.uniroute.ecrans.listTrajets
import android.util.Log
import com.donovanSergeAimenHatim.uniroute.sourceDeDonnées.SourceKelconke
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
    class TrajetDataManager(private val source: SourceKelconke) {

        suspend fun getTrajets(nomTable: String, colonne: String = "*", condition: String = ""): List<Trajets> {
            val trajetsJson = source.obtenirDonnées(nomTable, colonne, condition, ::transformJsonToTrajets)
            return trajetsJson ?: emptyList()
        }

        suspend fun getTrajetById(id : Int): Trajets?{
            val condition = "id=$id"
            val trajets = getTrajets("trajets","*",condition)
            return trajets.firstOrNull()
        }
        suspend fun reserverTrajet(idTrajet: Int, idUtilisateur: Int): Boolean {
            val trajet = getTrajetById(idTrajet)
            trajet?.let {
                val utilisateursReserves = it.utilisateursReserves.split(';').filterNot { it.isEmpty() }
                if (utilisateursReserves.size < it.nbPassager) {
                    val updatedUtilisateursReserves = (utilisateursReserves + idUtilisateur.toString()).joinToString(";")
                    val donneesMisesAJour = mapOf(
                        "id" to it.id.toString(),
                        "utilisateurID" to it.utilisateurID,
                        "villeDepart" to it.villeDepart,
                        "date" to it.date,
                        "villeDestination" to it.villeDestination,
                        "nbPassager" to it.nbPassager,
                        "priseCharge" to it.priseCharge,
                        "prixTrajet" to it.prixTrajet,
                        "dureeTrajet" to it.dureeTrajet,
                        "distanceTrajet" to it.distanceTrajet,
                        "modelVehicule" to it.modelVehicule,
                        "utilisateursReserves" to updatedUtilisateursReserves
                    )
                    // Log pour afficher les données
                    Log.d("reserverTrajet", "Données à envoyer: $donneesMisesAJour")
                    return source.modifierDonnee("trajets", donneesMisesAJour, it.id.toString())
                }
            }
            return false
        }
        suspend fun annulerTrajet(idTrajet: Int, idUtilisateur: Int):Boolean{
            val trajet = getTrajetById(idTrajet)
            trajet?.let {
                val utilisateursReserves = it.utilisateursReserves.split(';').filterNot { it.isEmpty() }
                if (idUtilisateur.toString() in utilisateursReserves) {
                    val updatedUtilisateursReserves = utilisateursReserves.filter { it != idUtilisateur.toString() }.joinToString(";")
                    val donneesMisesAJour = mapOf(
                        "id" to it.id.toString(),
                        "utilisateurID" to it.utilisateurID,
                        "villeDepart" to it.villeDepart,
                        "date" to it.date,
                        "villeDestination" to it.villeDestination,
                        "nbPassager" to it.nbPassager,
                        "priseCharge" to it.priseCharge,
                        "prixTrajet" to it.prixTrajet,
                        "dureeTrajet" to it.dureeTrajet,
                        "distanceTrajet" to it.distanceTrajet,
                        "modelVehicule" to it.modelVehicule,
                        "utilisateursReserves" to updatedUtilisateursReserves
                    )
                    // Log pour afficher les données
                    Log.d("annulerTrajet", "Données à envoyer: $donneesMisesAJour")
                    return source.modifierDonnee("trajets", donneesMisesAJour, it.id.toString())
                }
            }
            return false
        }

        suspend fun ajouterTrajet(trajet: Trajets): Boolean {
            val donnees = mapOf(
                "id" to trajet.id.toString(),
                "utilisateurID" to trajet.utilisateurID,
                "villeDepart" to trajet.villeDepart,
                "date" to trajet.date,
                "villeDestination" to trajet.villeDestination,
                "nbPassager" to trajet.nbPassager,
                "priseCharge" to trajet.priseCharge,
                "prixTrajet" to trajet.prixTrajet,
                "dureeTrajet" to trajet.dureeTrajet,
                "distanceTrajet" to trajet.distanceTrajet,
                "modelVehicule" to trajet.modelVehicule
            )
            // Log pour afficher les données
            Log.d("ajouterTrajet", "Données à envoyer: $donnees")
            return source.ajouterDonnee("trajets", donnees)
        }

        fun transformJsonToTrajets(jsonString: String): List<Trajets> {
            Log.d("listeTrajet", "JSON String: $jsonString")
            val gson = Gson()
            val type = object : TypeToken<List<String>>() {}.type
            val trajetsString: List<String> = gson.fromJson(jsonString, type)

            return trajetsString.map { trajetsString ->
                val parts = trajetsString.split("|")
                Trajets(
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
                    modelVehicule = parts[10],
                    utilisateursReserves = parts[11]
                )

            }
        }
    }