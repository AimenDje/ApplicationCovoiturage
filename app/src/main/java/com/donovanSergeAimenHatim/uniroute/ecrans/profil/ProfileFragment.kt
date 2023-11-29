package com.donovanSergeAimenHatim.uniroute.ecrans.profil

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.donovanSergeAimenHatim.uniroute.R
import com.donovanSergeAimenHatim.uniroute.UniRouteApp
import com.donovanSergeAimenHatim.uniroute.péférences.PreferenceFragment
import com.google.android.gms.maps.MapView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class ProfileFragment : Fragment() {

    private lateinit var photoProfil: ImageView
    private lateinit var nomPrénom: TextView
    private lateinit var email: TextView
    private lateinit var téléphone: TextView
    private lateinit var textCovoiturage: TextView
    private lateinit var notes: TextView
    private lateinit var lagueParlée1: ImageView
    private lateinit var lagueParlée2: ImageView
    private lateinit var typeVoiture: TextView
    private lateinit var adresse: MapView

    private lateinit var presentateur: PrésentateurProfil
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Obtient une référence à l'application globale pour accéder à ses méthodes et propriétés
        val app = activity?.application as UniRouteApp
        // Récupère l'identifiant de l'utilisateur stocké dans l'application globale
        val id_utilisateur: Int = app.getUtilisateurID()
        // Instancie le présentateur pour ce fragment
        presentateur = PrésentateurProfil(this)
        // Demande au présentateur de charger le profil de l'utilisateur
        presentateur.chargerProfilUtilisateur(id_utilisateur)
        // Trouve les vues dans le layout et les associe aux variables correspondantes
        photoProfil = view.findViewById(R.id.photo)
        nomPrénom = view.findViewById(R.id.nomPrenomText)
        email = view.findViewById(R.id.emailText)
        téléphone = view.findViewById(R.id.textTéléphone)
        textCovoiturage = view.findViewById(R.id.textCovoiturage)
        notes = view.findViewById(R.id.textNotes)
        lagueParlée1 = view.findViewById(R.id.imageLangue)
        lagueParlée2 = view.findViewById(R.id.imageLangue1)
        typeVoiture = view.findViewById(R.id.textVoiture)
        adresse = view.findViewById(R.id.adresseView)
        // Configuration du bouton "Préférences" pour ouvrir le fragment de préférences
        val btnPréférences: Button = view.findViewById(R.id.btnPréférences)
        btnPréférences.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.apply {
                replace(R.id.fragment_container, PreferenceFragment())
                addToBackStack(null)
                commit()
            }
        }
    }

    fun mettreAJourProfil(profil: ModèleProfile?) {
        // Vérifie si l'objet profil n'est pas null
        if (profil != null) {
            // Obtient le nom de la photo de profil à partir de l'objet profil
            val nomPhotoProfil: String = profil.photo
            // Obtient l'ID de ressource de l'image basé sur son nom
            val resId: Int = resources.getIdentifier(nomPhotoProfil, "drawable", requireContext().packageName)
            // Vérifie si l'ID de ressource est valide
            if (resId != 0) {
                // Définit la photo de profil dans l'ImageView si l'ID est valide
                photoProfil.setImageResource(resId)
            } else {
                // Utilise une image par défaut si l'ID n'est pas valide
                photoProfil.setImageResource(R.drawable.adresse)
            }
            // Définit le nom et le prénom dans l'interface utilisateur
            nomPrénom.setText(profil.prénom + " " + profil.nom)
            // Définit l'email dans l'interface utilisateur
            email.setText(profil.email)
            // Définit le numéro de téléphone dans l'interface utilisateur
            téléphone.setText(profil.téléphone)
            // Affiche le nombre de covoiturages
            textCovoiturage.setText("Nombre de covoiturage: " + profil.nombre_covoiturage)
            // Affiche les notes moyennes
            notes.setText("Notes en moyenne: " + profil.notes)
            // Gère l'affichage des langues parlées
            var languesParlees: List<String> = profil.languesParlées
            // Première langue parlée
            val premiereLangueImage: String? = languesParlees.getOrNull(0)
            premiereLangueImage?.let {
                val langue1: Int = resources.getIdentifier(it, "drawable", requireContext().packageName)
                if (langue1 != 0) {
                    lagueParlée1.setImageResource(langue1)
                }
            }
            // Deuxième langue parlée
            val deuxièmeLangueImage: String? = languesParlees.getOrNull(1)
            deuxièmeLangueImage?.let {
                val langue2: Int = resources.getIdentifier(it, "drawable", requireContext().packageName)
                if (langue2 != 0) {
                    lagueParlée2.setImageResource(langue2)
                }
            }
            // Définit le type de voiture dans l'interface utilisateur
            typeVoiture.setText("Type de voiture: " + profil.typeVoiture)
            // Gère l'affichage de l'adresse
            val adresseImage: String? = profil.adresse
            adresseImage?.let {
                val adresseId: Int = resources.getIdentifier(it, "drawable", requireContext().packageName)
                if (adresseId != 0) {
                    adresse.setBackgroundResource(adresseId)
                }
            }
        } else {
            // Affiche un message en cas d'erreur (si profil est null)
            afficherMessage("Une erreur est survenue")
        }
    }


    fun afficherErreur(e: Exception) {
        Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
    }
    fun afficherMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    /* companion object {
         /**
          * Use this factory method to create a new instance of
          * this fragment using the provided parameters.
          *
          * @param param1 Parameter 1.
          * @param param2 Parameter 2.
          * @return A new instance of fragment ProfileFragment.
          */
         // TODO: Rename and change types and number of parameters
         @JvmStatic
         fun newInstance(param1: String, param2: String) =
             ProfileFragment().apply {
                 arguments = Bundle().apply {
                     putString(ARG_PARAM1, param1)
                     putString(ARG_PARAM2, param2)
                 }
             }
     }*/
}


