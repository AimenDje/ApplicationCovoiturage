package com.donovanSergeAimenHatim.uniroute.ecrans.listTrajets

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.donovanSergeAimenHatim.uniroute.R
import com.donovanSergeAimenHatim.uniroute.sourceDeDonnées.SourceKelconke
import com.donovanSergeAimenHatim.uniroute.animation.anim
import com.donovanSergeAimenHatim.uniroute.utilisateur.Utilisateur
import com.donovanSergeAimenHatim.uniroute.utilisateur.UtilisateurDataManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [listTrajets.newInstance] factory method to
 * create an instance of this fragment.
 */
class listTrajets : Fragment(), TrajetsContract.View{
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var presenter: TrajetsPresenter
    private lateinit var userDataManager: UtilisateurDataManager
    private lateinit var animation: anim
    private var trajetSelectionneActuel: View? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        val destination = arguments?.getString("DESTINATION")
        val date = arguments?.getString("DATE")
        val nbPassagers = arguments?.getInt("NB_PASSAGERS")
        // Construire la chaîne de critères
        val critere = StringBuilder()
        if (!destination.isNullOrEmpty()) {
            critere.append("villeDestination=$destination")
        }

        if (!date.isNullOrEmpty()) {
            if (critere.isNotEmpty()) {
                critere.append("&")
            }
            critere.append("date=$date")
        }

        if (nbPassagers != null) {
            if (critere.isNotEmpty()) {
                critere.append("&")
            }
            critere.append("nbPassager=$nbPassagers")
        }


        val sourceKelconke = SourceKelconke()
        userDataManager = UtilisateurDataManager(sourceKelconke)
        val dataManager = TrajetDataManager(sourceKelconke)
        val userDataManager = UtilisateurDataManager(sourceKelconke)
        presenter = TrajetsPresenter(this, dataManager, userDataManager)
     //   presenter.chargerTrajets(critere.toString())
        presenter.chargerTrajets("utilisateurID=2")
        animation = anim()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_trajets, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fadeIn = AnimationUtils.loadAnimation(context, R.anim.fade_in)
        val fadeOut = AnimationUtils.loadAnimation(context, R.anim.fade_out)


        val listTrajetsSection = view.findViewById<LinearLayout>(R.id.containerListTrajets)
        listTrajetsSection.startAnimation(fadeIn)
        listTrajetsSection.visibility = View.VISIBLE
    }

    @SuppressLint("MissingInflatedId")
    override fun afficherTrajets(trajets: List<Trajets>) {
        val fadeIn = AnimationUtils.loadAnimation(context, R.anim.fade_in)
        val fadeOut = AnimationUtils.loadAnimation(context, R.anim.fade_out)
        val container = view?.findViewById<LinearLayout>(R.id.linear_layout_for_items)
        val titreTrajet = view?.findViewById<TextView>(R.id.textView_listTrajet_Title)
        container?.removeAllViews()
        val trajetViews = mutableListOf<View>()
        trajets.forEachIndexed { index, trajet ->
            val trajetView = LayoutInflater.from(context).inflate(R.layout.item_trajet, container, false)
            trajetViews.add(trajetView)
            trajetView.startAnimation(fadeIn)
            val nomConducteurView = trajetView.findViewById<TextView>(R.id.textView_trajetPrenomSelectionner)
            val nomConduteurNonSelectioner = trajetView.findViewById<TextView>(R.id.textView_NomTrajetNonSelectionner)
            val villeDepartDestinationView = trajetView.findViewById<TextView>(R.id.textView_trajetSelectionner_departDestination)
            val dateNonSelectionner = trajetView.findViewById<TextView>(R.id.textView_DateTrajetNonSelectionner)
            val date = trajetView.findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.dateTrajetSelectionnerEditText)
            val autoView = trajetView.findViewById<TextView>(R.id.textView_trajetSelectionnerAuto)
            val priseCharge = trajetView.findViewById<TextView>(R.id.textView_trajetSelectionnerDetail)
            val contactBouton = trajetView.findViewById<Button>(R.id.contactBtn)
            GlobalScope.launch(Dispatchers.Main) {
                val utilisateur = presenter.chargerUtilisateur(trajet.utilisateurId)
                if (utilisateur != null) {
                    nomConduteurNonSelectioner.text = "${utilisateur.nom} ${utilisateur.prenom}"
                    nomConducteurView.text = "${utilisateur.nom} ${utilisateur.prenom}"
                    contactBouton.setText("Contact ${utilisateur.prenom}")
                } else {
                    afficherErreur("Erreur utilisateur non trouver")
                }
            }
            titreTrajet?.text = "Trajet disponible:"
            priseCharge.text = "Prise en charge:\n${trajet.priseCharge}"
            dateNonSelectionner.setText("${trajet.date}")
            villeDepartDestinationView.text = "${trajet.villeDepart} -> ${trajet.villeDestination}"
            date.setText("${trajet.date}")
            autoView.text = "Voiture: Avenir"

            trajetView.findViewById<LinearLayout>(R.id.linearLayout_trajet).setOnClickListener {
                trajetViews.forEach { view ->
                    view.findViewById<LinearLayout>(R.id.linearLayout_trajetSelectionner).visibility = View.GONE
                    view.findViewById<LinearLayout>(R.id.linearLayout_trajet).visibility = View.VISIBLE
                }
                trajetView.findViewById<LinearLayout>(R.id.linearLayout_trajetSelectionner).visibility = View.VISIBLE
                trajetView.findViewById<LinearLayout>(R.id.linearLayout_trajet).visibility = View.GONE
            }
            if (index == 0) {
                trajetView.findViewById<LinearLayout>(R.id.linearLayout_trajetSelectionner).visibility = View.VISIBLE
                trajetView.findViewById<LinearLayout>(R.id.linearLayout_trajet).visibility = View.GONE
            } else {
                trajetView.findViewById<LinearLayout>(R.id.linearLayout_trajetSelectionner).visibility = View.GONE
                trajetView.findViewById<LinearLayout>(R.id.linearLayout_trajet).visibility = View.VISIBLE
            }

            container?.addView(trajetView)
        }
    }




    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment listTrajets.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            listTrajets().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun afficherTrajetSelectionne(trajet: Trajets) {
        activity?.runOnUiThread {
            val layoutTrajetSelectionne = view?.findViewById<LinearLayout>(R.id.linearLayout_trajetSelectionner)
            view?.findViewById<TextView>(R.id.textView_trajetPrenomSelectionner)?.text = "${trajet.utilisateurId}"
            view?.findViewById<TextView>(R.id.textView_trajetSelectionner_departDestination)?.text = "${trajet.villeDepart} -> ${trajet.villeDestination}"
            view?.findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.dateTrajetSelectionnerEditText)?.setText(trajet.date)
            layoutTrajetSelectionne?.visibility = View.VISIBLE
        }
    }
    override fun afficherErreur(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun afficherUtilisateur(utilisateur: Utilisateur) {
        TODO("Not yet implemented")
    }

}