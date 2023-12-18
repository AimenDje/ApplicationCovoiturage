package com.donovanSergeAimenHatim.uniroute.ecrans.profil

import com.donovanSergeAimenHatim.uniroute.sourceDeDonnées.SourceKelconke
import com.donovanSergeAimenHatim.uniroute.utilisateur.UtilisateurDataManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class PrésentateurProfil (val vue : ProfileFragment) {

    private val modèle = ModèleProfile()

    fun obrenirUnProfilUtilisateur(unNom :String) : ModèleProfile?{
        var utilisateurRecherché :ModèleProfile? =null
        var listProfil = modèle.obtenirProfils()
        if (listProfil != null) {
            for (utilisateur in listProfil){
                if (utilisateur.nom == unNom){
                    utilisateurRecherché = utilisateur

                }
            }
        }
        return utilisateurRecherché
    }



        // Déclaration de UtilisateurDataManager mais non initialisé
        private lateinit var userDataManager: UtilisateurDataManager

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
                    vue.mettreAJourProfil(profil)

                    // Mise à jour de la vue avec les données de profil
                } catch (e: Exception) {
                    // Affichage d'une erreur sur la vue en cas d'exception
                    vue.afficherErreur(e)
                }
            }
        }

    }


