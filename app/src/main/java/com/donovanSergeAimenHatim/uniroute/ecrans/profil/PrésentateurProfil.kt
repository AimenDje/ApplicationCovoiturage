package com.donovanSergeAimenHatim.uniroute.ecrans.profil

import android.text.TextUtils.replace
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

import com.donovanSergeAimenHatim.uniroute.R
import com.donovanSergeAimenHatim.uniroute.ecrans.connexion.Model
import com.donovanSergeAimenHatim.uniroute.ecrans.historique.HistoriqueFragment
import com.donovanSergeAimenHatim.uniroute.ecrans.messagerie.MessagerieFragment
import com.donovanSergeAimenHatim.uniroute.péférences.PreferenceFragment
import com.donovanSergeAimenHatim.uniroute.sourceDeDonnées.SourceKelconke
import com.donovanSergeAimenHatim.uniroute.utilisateur.UtilisateurDataManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PrésentateurProfil (val vue : ProfileFragment) {

    private lateinit var userDataManager: UtilisateurDataManager
    val profil = ModèleProfile ("arnold", "Gauthier", "Anrold", "argauthier@gmail.com ",
        "(514) 541-1452", 250 , 4.5 ,listOf("ang", "esp"),
        "Audi RS67", "adresse")


    suspend fun chargerProfile(userID: Int): ModèleProfile? {

        val sourceKelconke = SourceKelconke()
        userDataManager = UtilisateurDataManager(sourceKelconke)
        val profil : ModèleProfile?
            val utilisateur = userDataManager.getUtilisateurById(userID)
             profil = utilisateur?.let {
                ModèleProfile ("arnold", utilisateur.nom, utilisateur.prenom, utilisateur.email,
                    it.telephone, 12, utilisateur.note.toDouble() ,listOf("ang", "esp"),
                    utilisateur.voiture, utilisateur.adresse)

        }

        return profil
    }





}