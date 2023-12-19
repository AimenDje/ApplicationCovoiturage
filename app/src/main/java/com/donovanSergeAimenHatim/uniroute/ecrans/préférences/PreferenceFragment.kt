package com.donovanSergeAimenHatim.uniroute.ecrans.préférences

import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment

import com.donovanSergeAimenHatim.uniroute.R
import com.donovanSergeAimenHatim.uniroute.ecrans.profil.ProfileFragment
import com.donovanSergeAimenHatim.uniroute.ecrans.profil.PrésentateurProfil
import com.donovanSergeAimenHatim.uniroute.model.ModelUniRoute
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.Response
import java.io.File
import java.io.IOException

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
    private lateinit var boutonImageProfile: Button
    //private lateinit var utilisateurÀmodifier :String

    private lateinit var imagePickerLauncher: ActivityResultLauncher<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
        imagePickerLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let {
                // Ici, au lieu d'afficher l'image, envoyez-la à votre API
                uploadImageToServer(uri)
            }
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
        boutonImageProfile = view.findViewById<Button>(R.id.button_changeProfilePic)
        loadingPanel = view.findViewById(R.id.loadingPanel_trajetNonSelectionner)

        présentateur.chargerProfilUtilisateur(context?.getString(R.string.utilisateurID)!!.toInt())


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
                        présentateur.modifierUtilisateurApi("${context?.getString(R.string.utilisateurID)}" , nouvellesDonnees)
                        Toast.makeText(requireContext(), "Vos informations seront mises à jour", Toast.LENGTH_SHORT).show()
                        présentateur.modifierTheme(themeClair.isChecked)

                    } catch (e: Exception) {
                        // Affichage d'une erreur sur la vue en cas d'exception
                        afficherErreur(e)
                    }
                }
            }
        }
        boutonImageProfile.setOnClickListener {
            imagePickerLauncher.launch("image/*")
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

    private fun uploadImageToServer(imageUri: Uri) {
        val imagePath = getRealPathFromURI(imageUri)
        val file = File(imagePath)

        val requestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("image", file.name, file.asRequestBody("image/*".toMediaTypeOrNull()))
            .build()

        val request = Request.Builder()
            .url("https://donovanbeulze.com/unirouteAPI/uploadImg.php")
            .post(requestBody)
            .build()

        OkHttpClient().newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                afficherErreur(e)
            }

            override fun onResponse(call: Call, response: Response) {
                try {
                    if (response.isSuccessful) {
                        val responseBody = response.body?.string()
                        Log.d("UploadImage", "Réponse réussie: $responseBody")
                        GlobalScope.launch(Dispatchers.Main) {
                            try {
                                val nouvellesDonnees = mapOf(
                                "image" to file.name)
                                présentateur.modifierUtilisateurApi("${context?.getString(R.string.utilisateurID)}" , nouvellesDonnees)
                                Toast.makeText(requireContext(), "Photo mise à jour", Toast.LENGTH_SHORT).show()

                            } catch (e: Exception) {
                                // Affichage d'une erreur sur la vue en cas d'exception
                                afficherErreur(e)
                            }
                        }
                    } else {
                        Log.e("UploadImage", "Réponse non réussie: ${response.message}")
                    }
                } catch (e: IOException) {
                    Log.e("UploadImage", "Erreur lors de la lecture de la réponse", e)
                } finally {
                    response.close()
                }
            }
        })
    }
    private fun getRealPathFromURI(contentUri: Uri): String? {
        val context = this.context ?: return null
        var cursor: Cursor? = null
        try {
            val proj = arrayOf(MediaStore.Images.Media.DATA)
            cursor = context.contentResolver.query(contentUri, proj, null, null, null)
            val column_index = cursor?.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            cursor?.moveToFirst()
            return cursor?.getString(column_index ?: 0) ?: ""
        } finally {
            cursor?.close()
        }
    }


    fun modifierUnUtilisateur(modification: Boolean){
        if(modification){
        }else {
            // Affiche un message en cas d'erreur (si la modification ne s'effectue pas)
            afficherMessage("Une erreur est survenue")
        }
    }
    fun afficherInformations(profil: ModelUniRoute.Profil?) {
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