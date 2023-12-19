package com.donovanSergeAimenHatim.uniroute.ecrans.préférences

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.Toast
import androidx.fragment.app.Fragment

import com.donovanSergeAimenHatim.uniroute.R
import com.donovanSergeAimenHatim.uniroute.ecrans.profil.ModèleProfile
import com.donovanSergeAimenHatim.uniroute.ecrans.profil.ProfileFragment
import com.donovanSergeAimenHatim.uniroute.ecrans.profil.PrésentateurProfil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PreferenceFragment : Fragment() {

    private lateinit var nouveauNom: EditText
    private lateinit var nouveauPrénom: EditText
    private lateinit var nouvelEmail: EditText
    private lateinit var nouvelleVoiture: EditText
    private lateinit var nouvelleAdresse: EditText
    private lateinit var themeClair: RadioButton
    private lateinit var themeSombre: RadioButton
    private lateinit var buttonEnregistrer: Button
    private lateinit var présentateur: PrésentateurPréférences
    private lateinit var présentateurProfil : PrésentateurProfil
    private lateinit var loadingPanel: LinearLayout
    //private lateinit var utilisateurÀmodifier :String


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
        themeClair = view.findViewById(R.id.radioButtonThemeClair)
        themeSombre = view.findViewById(R.id.radioButtonThemeSombre)
        buttonEnregistrer = view.findViewById(R.id.buttonEnregistrer)
        présentateur = PrésentateurPréférences(this)
        présentateurProfil = PrésentateurProfil(ProfileFragment())

        loadingPanel = view.findViewById(R.id.loadingPanel_trajetNonSelectionner)

        présentateur.chargerProfilUtilisateur(99)


        themeClair.isChecked = true
        désactiverOuActiverRaddionButton()

        //récupération des données
        var nom = nouveauNom.text.toString()
        var prénom = nouveauPrénom.text.toString()
        var email = nouvelEmail.text.toString()
        var voiture = nouvelleVoiture.text.toString()
        var adresse = nouvelleAdresse.text.toString()
        var themeClairAffichage : Boolean


        if(themeClair.isChecked){
            themeClairAffichage = true

        }else {
            themeClairAffichage = false
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
                val nouvellesDonnees = mapOf(
                    "nom" to nouveauPrénom.text.toString() ,
                    "prenom" to nouveauNom.text.toString(),
                    "email" to nouvelEmail.text.toString(),
                    "voiture" to  nouvelleVoiture.text.toString(),
                    "adresse" to nouvelleAdresse.text.toString())
                GlobalScope.launch(Dispatchers.Main) {
                    try {
                        présentateur.modifierUtilisateurApi("99" , nouvellesDonnees)
                        Toast.makeText(requireContext(), "Vos informations seront mises à jour", Toast.LENGTH_SHORT).show()
                        présentateur.modifierTheme(themeClair.isChecked)

                    } catch (e: Exception) {
                        // Affichage d'une erreur sur la vue en cas d'exception
                        afficherErreur(e)
                    }
                }
            }
            }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_preference, container, false)
    }

    private fun désactiverOuActiverRaddionButton(){

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

    fun modifierUnUtilisateur(modification: Boolean){
        if(modification){
        }else {
            // Affiche un message en cas d'erreur (si la modification ne s'effectue pas)
            afficherMessage("Une erreur est survenue")
        }
    }
    fun afficherInformations(profil: ModèleProfile?) {
        // Vérifie si l'objet profil n'est pas null
        val fadeOut = AnimationUtils.loadAnimation(context, R.anim.fade_out)
        if (profil != null) {
            // Obtient le nom de la photo de profil à partir de l'objet profil

            // Définit le nom et le prénom dans l'interface utilisateur
            nouveauNom.setText(profil?.nom)
            // Définit l'email dans l'interface utilisateur
            nouveauPrénom.setText(profil?.prénom)
            // Définit le numéro de téléphone dans l'interface utilisateur
            nouvelEmail.setText(profil?.email)
            nouvelleVoiture.setText(profil?.typeVoiture)
            nouvelleAdresse.setText(profil?.adresse)

            loadingPanel.startAnimation(fadeOut)
            loadingPanel.visibility = View.GONE

        } else {
            // Affiche un message en cas d'erreur (si profil est null)
            afficherMessage("Une erreur est survenue")
        }
    }
    fun afficherMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    fun afficherErreur(e: Exception) {
        Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
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