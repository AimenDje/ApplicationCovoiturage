package com.donovanSergeAimenHatim.uniroute.péférences

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.Toast
import androidx.fragment.app.Fragment

import com.donovanSergeAimenHatim.uniroute.R
import com.donovanSergeAimenHatim.uniroute.ecrans.profil.ProfileFragment
import com.donovanSergeAimenHatim.uniroute.ecrans.profil.PrésentateurProfil

class PreferenceFragment : Fragment() {

    private lateinit var nouveauNom: EditText
    private lateinit var nouveauPrénom: EditText
    private lateinit var nouvelEmail: EditText
    private lateinit var nouvelleVoiture: EditText
    private lateinit var nouvelleAdresse: EditText
    private lateinit var affichagekm: RadioButton
    private lateinit var affichageMiles: RadioButton
    private lateinit var themeClair: RadioButton
    private lateinit var themeSombre: RadioButton
    private lateinit var buttonEnregistrer: Button
    private lateinit var présentateur: PrésentateurPréférences
    private lateinit var présentateurProfil : PrésentateurProfil




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nouveauNom = view.findViewById(R.id.editTextNom)
        nouveauPrénom = view.findViewById(R.id.editTextPrénom)
        nouvelEmail = view.findViewById(R.id.editTextEmail)
        nouvelleVoiture = view.findViewById(R.id.editTextTypeVoiture)
        nouvelleAdresse = view.findViewById(R.id.editTextAdresse)
        affichagekm = view.findViewById(R.id.radioButtonKm)
        affichageMiles = view.findViewById(R.id.radioButtonMiles)
        themeClair = view.findViewById(R.id.radioButtonThemeClair)
        themeSombre = view.findViewById(R.id.radioButtonThemeSombre)
        buttonEnregistrer = view.findViewById(R.id.buttonEnregistrer)
        présentateur = PrésentateurPréférences(this)
        présentateurProfil = PrésentateurProfil(ProfileFragment())

        nouveauNom.setText(présentateurProfil.obrenirUnProfilUtilisateur("Gauthier")?.nom)
        nouveauPrénom.setText(présentateurProfil.obrenirUnProfilUtilisateur("Gauthier")?.prénom)
        nouvelEmail.setText(présentateurProfil.obrenirUnProfilUtilisateur("Gauthier")?.email)
        nouvelleVoiture.setText(présentateurProfil.obrenirUnProfilUtilisateur("Gauthier")?.typeVoiture)
        nouvelleAdresse.setText(présentateurProfil.obrenirUnProfilUtilisateur("Gauthier")?.adresse)

        affichagekm.isChecked = true
        themeClair.isChecked = true
        désactiverOuActiverRaddionButton()

        //récupération des données
        val nom = nouveauNom.text.toString()
        val prénom = nouveauPrénom.text.toString()
        val email = nouvelEmail.text.toString()
        val voiture = nouvelleVoiture.text.toString()
        val adresse = nouvelleAdresse.text.toString()
        val km : Boolean
        val clair : Boolean
        if(themeClair.isChecked) {
            km = true
        }else {
            km = false
        }
        if(themeClair.isChecked){
            clair = true
        }else {
            clair = false
        }



        buttonEnregistrer.setOnClickListener {
            // Vérifier si les champs sont vides
            if (nouveauNom.text.toString().isEmpty() ||
                nouveauPrénom.text.toString().isEmpty() ||
                nouvelEmail.text.toString().isEmpty() ||
                nouvelleVoiture.text.toString().isEmpty() ||
                nouvelleAdresse.text.toString().isEmpty()
            ) {
                // Afficher une alerte si les champs sont vides
                Toast.makeText(requireContext(), "Vueillez remplir tous les champs", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Vos informations seront mises à jour", Toast.LENGTH_SHORT).show()
                présentateur.mettreAJourPréférence(nom, prénom, email, voiture, adresse, km, clair  )

            }
            }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_preference, container, false)
    }

    private fun désactiverOuActiverRaddionButton(){
        affichagekm.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                affichageMiles.isChecked = false
            }
        }

        affichageMiles.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                affichagekm.isChecked = false
            }
        }

        themeClair.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                themeSombre.isChecked = false
            }
        }

        themeSombre.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                themeClair.isChecked = false
            }
        }
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PreferenceFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PreferenceFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}