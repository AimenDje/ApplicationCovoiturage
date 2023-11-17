package com.donovanSergeAimenHatim.uniroute.ecrans.profil

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import com.donovanSergeAimenHatim.uniroute.R
import com.donovanSergeAimenHatim.uniroute.ecrans.historique.HistoriqueFragment
import com.donovanSergeAimenHatim.uniroute.péférences.PreferenceFragment
import com.donovanSergeAimenHatim.uniroute.utilisateur.UtilisateurDataManager
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


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)


    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        photoProfil = view.findViewById(R.id.photo)
        nomPrénom = view.findViewById(R.id.nomPrenomText)
        email = view.findViewById(R.id.emailText)
        téléphone = view.findViewById(R.id.textTéléphone)
        textCovoiturage = view.findViewById(R.id.textCovoiturage)
        notes = view.findViewById(R.id.textNotes)
        lagueParlée1 = view.findViewById(R.id.imageLangue)
        lagueParlée2 = view.findViewById(R.id.imageLangue1)
        typeVoiture= view.findViewById(R.id.textVoiture)
        adresse = view.findViewById(R.id.adresseView)
        var id_utilisateur = 3 // ID utilisateur
        //on récupère les données a afficher à partir du présentateur
        var présentateur = PrésentateurProfil(this,)
        var profil: ModèleProfile
        GlobalScope.launch(Dispatchers.Main) {
            profil = présentateur.chargerProfile(id_utilisateur)!!
            if (profil != null) {
                val nomPhotoProfil: String = profil.photo

                // Obtenez l'ID de ressource correspondant au nom de l'image
                val resId: Int = resources.getIdentifier(nomPhotoProfil, "drawable", requireContext().packageName)
                // Vérifiez si l'ID de ressource est valide
                if (resId != 0) {
                    // Chargez la photo de profil à partir des ressources et définissez-la dans l'ImageView
                    photoProfil.setImageResource(resId)

                } else {
                    // Si l'ID de ressource n'est pas valide, utilisez une image par défaut ou affichez un message d'erreur
                    photoProfil.setImageResource(R.drawable.adresse)
                }
                // on modifie le nom et prénom
                nomPrénom.setText(profil.prénom+ " "+ profil.nom)
                // on modifie l'émail
                email.setText(profil.email)
                // on modifie le téléphone
                téléphone.setText(profil.téléphone)
                textCovoiturage.setText("Nombre de covoiturage: "+profil.nombre_covoiturage)
                notes.setText("Notes en moyenne: " + profil.notes)

                //Langues Parléés
                var languesParlees: List<String> = profil.languesParlées
                val premiereLangueImage: String? = languesParlees.get(0)

                // Première Langue
                val langue1: Int = resources.getIdentifier(premiereLangueImage, "drawable", requireContext().packageName)
                // Vérifiez si l'ID de ressource est valide
                if (resId != 0) {
                    // Chargez la photo de profil à partir des ressources et définissez-la dans l'ImageView
                    lagueParlée1.setImageResource(langue1)

                }

                // Deuxième langue
                val deuxièmeLangueImage: String? = languesParlees.get(1)

                // Obtenez l'ID de ressource correspondant au nom de l'image
                val langue2: Int = resources.getIdentifier(deuxièmeLangueImage, "drawable", requireContext().packageName)
                // Vérifiez si l'ID de ressource est valide
                if (resId != 0) {
                    // Chargez la photo de profil à partir des ressources et définissez-la dans l'ImageView
                    lagueParlée2.setImageResource(langue2)

                }

                //On change le type de voiture
                typeVoiture.setText("Type de voiture: " + profil.typeVoiture)
                //On change l'adresse
                val adresseImage: String? = profil.adresse

                // Obtenez l'ID de ressource correspondant au nom de l'image
                val adresseId: Int = resources.getIdentifier(adresseImage, "drawable", requireContext().packageName)
                // Vérifiez si l'ID de ressource est valide
                if (resId != 0) {
                    // Chargez la photo de profil à partir des ressources et définissez-la dans l'ImageView
                    adresse.setBackgroundResource(adresseId)


                }
            } else {
                null
            }
        }


        val btnPréférences: Button = view.findViewById(R.id.btnPréférences)
        btnPréférences.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.apply {
                replace(R.id.fragment_container, PreferenceFragment())
                addToBackStack(null)
                commit()
            }
        }

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


