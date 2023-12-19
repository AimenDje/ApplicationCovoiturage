package com.donovanSergeAimenHatim.uniroute.ecrans.accueil

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.donovanSergeAimenHatim.uniroute.R
import com.google.android.material.textfield.TextInputEditText
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.donovanSergeAimenHatim.uniroute.UniRouteApp
import com.donovanSergeAimenHatim.uniroute.ecrans.listTrajets.TrajetDataManager
import com.donovanSergeAimenHatim.uniroute.ecrans.listTrajets.Trajets
import com.donovanSergeAimenHatim.uniroute.ecrans.listTrajets.TrajetsContract
import com.donovanSergeAimenHatim.uniroute.ecrans.listTrajets.TrajetsPresenter
import com.donovanSergeAimenHatim.uniroute.ecrans.listTrajets.listTrajets
import com.donovanSergeAimenHatim.uniroute.sourceDeDonnées.SourceKelconke
import com.donovanSergeAimenHatim.uniroute.utilisateur.Utilisateur
import com.donovanSergeAimenHatim.uniroute.utilisateur.UtilisateurDataManager
import com.google.android.material.button.MaterialButtonToggleGroup
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import com.google.gson.Gson
import kotlinx.coroutines.launch
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AccueilFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AccueilFragment : Fragment(), TrajetsContract.View{
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var presenter: TrajetsPresenter
    private lateinit var userDataManager: UtilisateurDataManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_accueil, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Initialisation du présentateur
        val sourceKelconke = SourceKelconke()
        userDataManager = UtilisateurDataManager(sourceKelconke)
        val dataManager = TrajetDataManager(sourceKelconke)
        val userDataManager = UtilisateurDataManager(sourceKelconke)
        presenter = TrajetsPresenter(this, dataManager, userDataManager)

        val btnAjouterTrajet: Button = view.findViewById(R.id.BtnProposerCoVoiturage)
        btnAjouterTrajet.setOnClickListener {
            val idUtilisateur =context?.getString(R.string.utilisateurID)!!.toInt()
            val depart = view.findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.textFieldDepartProposer).text.toString()
            val destination = view.findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.textFIeldDestinationProposer).text.toString()
            val heureDepart = view.findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.timePickerEditText).text.toString()
            val date = view.findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.datePickerEditTextProposer).text.toString()
            val auto = view.findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.textFieldAutoProposer).text.toString()
            val nbPassagers = view.findViewById<com.google.android.material.slider.Slider>(R.id.nbPassagerProposer)?.value
            val priseCharge =view.findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.textFieldPriseChargeProposer).text.toString()
            val prixTrajet = "0$"
            val dureeTrajet = "0h"
            val distanceTrajet = "0km"
            val utilisateurReserver = ""
            var trajet = Trajets(0,idUtilisateur,depart,"${date} ${heureDepart}",destination,nbPassagers!!.toInt(), priseCharge, prixTrajet, dureeTrajet, distanceTrajet, auto, utilisateurReserver)
            presenter.ajouterTrajet(trajet)
        }
        val datePickerEditText = view.findViewById<TextInputEditText>(R.id.datePickerEditText)
        datePickerEditText.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Date")
                .build()
            datePicker.show(childFragmentManager, datePicker.toString())
            datePicker.addOnPositiveButtonClickListener { selection ->
                val selectedDate = datePicker.headerText
                datePickerEditText.setText(selectedDate)
            }
        }

        val datePickerEditTextProposer = view.findViewById<TextInputEditText>(R.id.datePickerEditTextProposer)
        datePickerEditTextProposer.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Date")
                .build()
            datePicker.show(childFragmentManager, datePicker.toString())
            datePicker.addOnPositiveButtonClickListener { selection ->
                val selectedDate = datePicker.headerText
                datePickerEditTextProposer.setText(selectedDate)
            }
        }
        val fadeIn = AnimationUtils.loadAnimation(context, R.anim.fade_in)
        val fadeOut = AnimationUtils.loadAnimation(context, R.anim.fade_out)
        val toggleGroup = view.findViewById<MaterialButtonToggleGroup>(R.id.toggleButton)
        val trouverSection = view.findViewById<LinearLayout>(R.id.trouverSection)
        val proposeSection = view.findViewById<LinearLayout>(R.id.proposeSection)
        toggleGroup.addOnButtonCheckedListener { group, checkedId, isChecked ->
            when (checkedId) {
                R.id.chercheCoVoiturage -> {
                    if (isChecked) {
                        proposeSection.startAnimation(fadeOut)
                        proposeSection.visibility = View.GONE

                        trouverSection.startAnimation(fadeIn)
                        trouverSection.visibility = View.VISIBLE
                    }
                }
                R.id.proposeCoVoiturage -> {
                    if (isChecked) {
                        trouverSection.startAnimation(fadeOut)
                        trouverSection.visibility = View.GONE

                        proposeSection.startAnimation(fadeIn)
                        proposeSection.visibility = View.VISIBLE
                    }
                }
            }
        }
        val btnChercherCoVoiturage: Button = view.findViewById(R.id.BtnTrouverCoVoiturage)
        btnChercherCoVoiturage.setOnClickListener {
            val destinationInput = view?.findViewById<TextInputEditText>(R.id.destinationTrouver)?.text?.toString()
            val dateInput = view?.findViewById<TextInputEditText>(R.id.datePickerEditText)?.text?.toString()
            val nbPassagers = view?.findViewById<com.google.android.material.slider.Slider>(R.id.nbPassagerSlider)?.value?.toInt()

            val outputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
            val date = dateInput?.let {
                try {
                    val dateFormat = SimpleDateFormat("MMM d, yyyy", Locale.ENGLISH)
                    dateFormat.parse(it)?.let { date -> outputFormat.format(date) }
                } catch (e: ParseException) {
                    null
                }
            }
            val bundle = Bundle().apply {
                putString("villeDestination", destinationInput)
                putString("date", date)
                if (nbPassagers != null) putInt("nbPassager", nbPassagers)
            }
            val listTrajetsFragment = listTrajets().apply {
                arguments = bundle
            }
            activity?.supportFragmentManager?.beginTransaction()?.apply {
                replace(R.id.fragment_container, listTrajetsFragment)
                addToBackStack(null)
                commit()
            }
        }

        val timePickerEditText = view.findViewById<TextInputEditText>(R.id.timePickerEditText)
        timePickerEditText.setOnClickListener {
            val timePicker = MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_12H)
                .setHour(12)
                .setMinute(10)
                .setTitleText("Select Appointment Time")
                .build()

            timePicker.show(childFragmentManager, timePicker.toString())
            timePicker.addOnPositiveButtonClickListener {
                val selectedTime = String.format("%02d:%02d %s",
                    timePicker.hour,
                    timePicker.minute,
                    if (timePicker.hour < 12) "AM" else "PM")
                timePickerEditText.setText(selectedTime)
            }
        }

    }
    fun afficherSuccesAjout() {
        Toast.makeText(context, "Trajet ajouté avec succès", Toast.LENGTH_LONG).show()
    }

    fun afficherErreurAjout(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    fun convertirDate(dateString: String): String {
        val originalFormat = SimpleDateFormat("MMM d, yyyy hh:mm a", Locale.ENGLISH)
        val targetFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)

        return try {
            val date = originalFormat.parse(dateString)
            targetFormat.format(date)
        } catch (e: Exception) {
            e.printStackTrace()
            "Erreur de formatage"
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AccueilFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AccueilFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun afficherTrajets(trajets: List<Trajets>) {
        TODO("Not yet implemented")
    }
    override fun afficherTrajetSelectionne(trajet: Trajets) {
        TODO("Not yet implemented")
    }
    override fun afficherErreur(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
    override fun afficherUtilisateur(utilisateur: Utilisateur) {
        TODO("Not yet implemented")
    }

    override fun aucunTrajetDisponible() {
        TODO("Not yet implemented")
    }
}