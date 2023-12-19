package com.donovanSergeAimenHatim.uniroute.ecrans.listTrajets

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.donovanSergeAimenHatim.uniroute.CircleTransform
import com.donovanSergeAimenHatim.uniroute.R
import com.donovanSergeAimenHatim.uniroute.sourceDeDonnées.SourceKelconke
import com.donovanSergeAimenHatim.uniroute.animation.anim
import com.donovanSergeAimenHatim.uniroute.ecrans.accueil.AccueilFragment
import com.donovanSergeAimenHatim.uniroute.utilisateur.Utilisateur
import com.donovanSergeAimenHatim.uniroute.utilisateur.UtilisateurDataManager
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.net.URLEncoder

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
    var loadingLogo: ProgressBar? = null
    var confirmationBtn: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        val destination = arguments?.getString("villeDestination")
        val date = arguments?.getString("date")
        val nbPassagers = arguments?.getInt("nbPassager")
        val critere = mutableListOf<String>()
        if (!destination.isNullOrEmpty()) {
            critere.add("villeDestination=$destination")
        }
        if (!date.isNullOrEmpty()) {
            critere.add("date=$date")
        }
        if (nbPassagers != null) {
            critere.add("nbPassager=$nbPassagers")
        }
        val customCondition =  URLEncoder.encode("NOT FIND_IN_SET(\"${context?.getString(R.string.utilisateurID)!!.toInt()}\",REPLACE(utilisateursReserves,\";\",\",\"))")
        critere.add("customCondition=$customCondition")
        val finalCriteria = critere.joinToString("&")
        Log.d("SQL requete", "SQL String: $finalCriteria")
        val sourceKelconke = SourceKelconke()
        userDataManager = UtilisateurDataManager(sourceKelconke)
        val dataManager = TrajetDataManager(sourceKelconke)
        val userDataManager = UtilisateurDataManager(sourceKelconke)
        presenter = TrajetsPresenter(this, dataManager, userDataManager)
        presenter.chargerTrajets(finalCriteria)
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
        loadingLogo = view.findViewById<ProgressBar>(R.id.progressBar_loading)
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

        var utilisateurId: Int = 0
        container?.removeAllViews()
        val trajetViews = mutableListOf<View>()
        trajets.forEachIndexed { index, trajet ->
            val trajetView = LayoutInflater.from(context).inflate(R.layout.item_trajet, container, false)
            trajetViews.add(trajetView)
            trajetView.startAnimation(fadeIn)
            loadingLogo?.visibility = View.GONE
            var utilisateur: Utilisateur? = null
            var trajetId: Int = 0
            val nomConducteurView = trajetView.findViewById<TextView>(R.id.textView_trajetPrenomSelectionner)
            val nomConduteurNonSelectioner = trajetView.findViewById<TextView>(R.id.textView_NomTrajetNonSelectionner)
            val villeDepartDestinationView = trajetView.findViewById<TextView>(R.id.textView_trajetSelectionner_departDestination)
            val dateNonSelectionner = trajetView.findViewById<TextView>(R.id.textView_DateTrajetNonSelectionner)
            val date = trajetView.findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.dateTrajetSelectionnerEditText)
            val autoView = trajetView.findViewById<TextView>(R.id.textView_trajetSelectionnerAuto)
            val priseCharge = trajetView.findViewById<TextView>(R.id.textView_trajetSelectionnerDetail)
            val loadingPanelTrajetSelectionner = trajetView.findViewById<LinearLayout>(R.id.loadingPanel_trajetSelectionner)
            val loadingPanelNonTrajetSelectionner = trajetView.findViewById<LinearLayout>(R.id.loadingPanel_trajetNonSelectionner)
            val contactBouton = trajetView.findViewById<Button>(R.id.contactBtn)
            val photoProfileSelectionner = trajetView.findViewById<ImageView>(R.id.imageView_profilePicTrajetSelectionner)
            val photoProfileNonSelectionner = trajetView.findViewById<ImageView>(R.id.imageView_profilePicNonSelectionner)
            trajetId = trajet.id
            GlobalScope.launch(Dispatchers.Main) {
                utilisateur = presenter.chargerUtilisateur(trajet.utilisateurID)
                if (utilisateur != null) {
                    utilisateurId = utilisateur!!.id
                    nomConduteurNonSelectioner.text = "${utilisateur!!.nom} ${utilisateur!!.prenom}"
                    nomConducteurView.text = "${utilisateur!!.nom} ${utilisateur!!.prenom}"
                    contactBouton.setText("Contact ${utilisateur!!.prenom}")
                    Picasso.get()
                        .load("https://donovanbeulze.com/unirouteAPI/img/" + utilisateur!!.photo)
                        .transform(CircleTransform())
                        .into(photoProfileSelectionner)
                    Picasso.get()
                        .load("https://donovanbeulze.com/unirouteAPI/img/" + utilisateur!!.photo)
                        .transform(CircleTransform())
                        .into(photoProfileNonSelectionner)
                    loadingPanelNonTrajetSelectionner.startAnimation(fadeOut)
                    loadingPanelNonTrajetSelectionner.visibility = View.GONE
                    loadingPanelTrajetSelectionner.startAnimation(fadeOut)
                    loadingPanelTrajetSelectionner.visibility = View.GONE
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
            val confirmationBtnLocal = trajetView.findViewById<Button>(R.id.BtnParticiper)
            confirmationBtnLocal.setOnClickListener {
                afficherConfirmation(utilisateur!!.prenom)
                presenter.reserverTrajet(trajetId, getString(R.string.utilisateurID).toInt())
            }
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
            trajetView.startAnimation(fadeIn)
        }
    }


    fun afficherConfirmation(nomConducteur: String){
        val bundle = Bundle().apply {
            putString("nomConducteur", nomConducteur)
        }
        val confirmationFragment = confirmationTrajetFragment().apply {
        arguments = bundle
        }
        activity?.supportFragmentManager?.beginTransaction()?.apply {
            replace(R.id.fragment_container, confirmationFragment)
            addToBackStack(null)
            commit()
        }
    }

    override fun aucunTrajetDisponible(){
        activity?.supportFragmentManager?.beginTransaction()?.apply {
            replace(R.id.fragment_container, AccueilFragment())
            addToBackStack(null)
            commit()
        }
        afficherErreur("Aucun trajet de disponible")
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
            view?.findViewById<TextView>(R.id.textView_trajetPrenomSelectionner)?.text = "${trajet.utilisateurID}"
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