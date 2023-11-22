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
import com.donovanSergeAimenHatim.uniroute.sourceDeDonnées.SourceDeDonnées
import com.donovanSergeAimenHatim.uniroute.sourceDeDonnées.SourceKelconke

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




}