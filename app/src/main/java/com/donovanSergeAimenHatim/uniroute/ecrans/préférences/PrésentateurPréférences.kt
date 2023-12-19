package com.donovanSergeAimenHatim.uniroute.ecrans.préférences

import androidx.appcompat.app.AppCompatDelegate
import com.donovanSergeAimenHatim.uniroute.ecrans.profil.ModèleProfile
import com.donovanSergeAimenHatim.uniroute.sourceDeDonnées.SourceKelconke
import com.donovanSergeAimenHatim.uniroute.utilisateur.UtilisateurDataManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PrésentateurPréférences(private val vue: PreferenceFragment) {
    private val model = ModèlePréférences()
    private lateinit var userDataManager: UtilisateurDataManager
    fun mettreAJourPréférence(utilisateurÀModifier:String,
                                      nouveauNom:String, nouveauPrénom:String, nouvelEmail:String,
                                      nouveauTypedeVoiture:String, nouvelleAdresse : String
    ){

        model.modifierUnProfil(utilisateurÀModifier, nouveauNom, nouveauPrénom,
            nouvelEmail, nouveauTypedeVoiture, nouvelleAdresse)

    }

     suspend fun modifierUtilisateurApi(UtilisateurID: String, nouvellesDonnnes: Map<String, Any>):Boolean{
         val sourceKelconke = SourceKelconke()
         userDataManager = UtilisateurDataManager(sourceKelconke)
         return userDataManager.modifierUtilisateurByID(UtilisateurID, nouvellesDonnnes)
    }
    fun modifierProfilUtilisateur(UtilisateurID: String, nouvellesDonnnes: Map<String, Any>) {

        GlobalScope.launch(Dispatchers.Main) {
            try {
                // Appel de la fonction suspendue pour modifier le profil
                val modification = modifierUtilisateurApi(UtilisateurID, nouvellesDonnnes)
                vue.modifierUnUtilisateur(modification)

                // Mise à jour de la vue avec les données de profil
            } catch (e: Exception) {
                // Affichage d'une erreur sur la vue en cas d'exception
                vue.afficherErreur(e)
            }
        }
    }



    fun modifierTheme (themeClair: Boolean) {
        if(themeClair == false) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
        else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }




    // Fonction suspendue pour charger un profil depuis une API
    suspend fun chargerProfileDepuisAPI(userID: Int): ModèleProfile? {
        // Création d'une instance de SourceKelconke pour gérer les données
        val sourceKelconke = SourceKelconke()
        // Initialisation de userDataManager avec sourceKelconke
        userDataManager = UtilisateurDataManager(sourceKelconke)

        // Récupération des données utilisateur par son ID
        val utilisateur = userDataManager.getUtilisateurById(userID)

        // Construction de l'objet ModèleProfile à partir des données utilisateur
        var profil = utilisateur?.let {
            ModèleProfile ("arnold", utilisateur.nom, utilisateur.prenom, utilisateur.email,
                it.telephone, 12, utilisateur.note.toDouble(), listOf("ang", "esp"),
                utilisateur.voiture, utilisateur.adresse)
        }

        // Retourne l'objet profil (peut être null)
        return profil
    }

    // Fonction pour charger le profil utilisateur, exécutée dans une coroutine
    fun chargerProfilUtilisateur(idUtilisateur: Int) {
        // Lancement d'une coroutine dans le scope global
        GlobalScope.launch(Dispatchers.Main) {
            try {
                // Appel de la fonction suspendue pour obtenir le profil
                val profil = chargerProfileDepuisAPI(idUtilisateur)
                vue.afficherInformations(profil)

                // Mise à jour de la vue avec les données de profil
            } catch (e: Exception) {
                // Affichage d'une erreur sur la vue en cas d'exception
                vue.afficherErreur(e)
            }
        }
    }




        }