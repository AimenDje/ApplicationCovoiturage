package com.donovanSergeAimenHatim.uniroute.ecrans.listTrajets

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.donovanSergeAimenHatim.uniroute.CircleTransform
import com.donovanSergeAimenHatim.uniroute.R
import com.donovanSergeAimenHatim.uniroute.sourceDeDonnées.SourceKelconke
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
 * Use the [TrajetReserverFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TrajetReserverFragment : Fragment(), TrajetsContract.View {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var presenter: TrajetsPresenter
    private lateinit var userDataManager: UtilisateurDataManager
    private lateinit var loadingPanel: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        val critere = mutableListOf<String>()
        val customCondition =  URLEncoder.encode("FIND_IN_SET(\"${context?.getString(R.string.utilisateurID)}\",REPLACE(utilisateursReserves,\";\",\",\"))")
        critere.add("customCondition=$customCondition")
        val finalCriteria = critere.joinToString("&")
        val sourceKelconke = SourceKelconke()
        userDataManager = UtilisateurDataManager(sourceKelconke)
        val dataManager = TrajetDataManager(sourceKelconke)
        val userDataManager = UtilisateurDataManager(sourceKelconke)
        presenter = TrajetsPresenter(this, dataManager, userDataManager)
        presenter.chargerTrajets(finalCriteria)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reservations_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TrajetReserverFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TrajetReserverFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun afficherTrajets(trajets: List<Trajets>) {
        val fadeIn = AnimationUtils.loadAnimation(context, R.anim.fade_in)
        val fadeOut = AnimationUtils.loadAnimation(context, R.anim.fade_out)
        val container = view?.findViewById<LinearLayout>(R.id.reservedTrajetList)
        val titreTrajet = view?.findViewById<TextView>(R.id.textView_listTrajet_Title)
        var trajetId: Int = 0
        var utilisateurId: Int = 0
        container?.removeAllViews()
        val trajetViews = mutableListOf<View>()
        trajets.forEachIndexed { index, trajet ->
            val trajetView = LayoutInflater.from(context).inflate(R.layout.item_trajet_reserver, container, false)
            trajetViews.add(trajetView)
            trajetView.startAnimation(fadeIn)
            var utilisateur: Utilisateur? = null
            val nomConducteurView = trajetView.findViewById<TextView>(R.id.textView_nom_conducteur_trajet_reserver)
            val loadingPanel_item = trajetView.findViewById<LinearLayout>(R.id.loadingPanel_trajetNonSelectionner)
            val villeDepartDestinationView = trajetView.findViewById<TextView>(R.id.textView_destination_trajet)
            val date = trajetView.findViewById<TextView>(R.id.textView_date_trajet_reserver)
            val photo = trajetView.findViewById<ImageView>(R.id.imageView_profile_reserver)
            GlobalScope.launch(Dispatchers.Main) {
                utilisateur = presenter.chargerUtilisateur(trajet.utilisateurID)
                if (utilisateur != null) {
                    utilisateurId = utilisateur!!.id
                    nomConducteurView.text = "${utilisateur!!.nom} ${utilisateur!!.prenom}"
                    Picasso.get()
                        .load("https://donovanbeulze.com/unirouteAPI/img/" + utilisateur!!.photo)
                        .transform(CircleTransform())
                        .into(photo)
                    loadingPanel_item.startAnimation(fadeOut)
                    loadingPanel_item.visibility = View.GONE
                } else {
                    afficherErreur("Erreur utilisateur non trouver")
                }
            }
            trajetId = trajet.id
            titreTrajet?.text = "Trajet disponible:"
            villeDepartDestinationView.text = "${trajet.villeDepart} -> ${trajet.villeDestination}"
            date.text = "${trajet.date.toString()}"
            container?.addView(trajetView)
            trajetView.startAnimation(fadeIn)
        }
    }

    override fun afficherTrajetSelectionne(trajet: Trajets) {
        TODO("Not yet implemented")
    }

    override fun afficherErreur(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun afficherUtilisateur(utilisateur: Utilisateur) {
        TODO("Not yet implemented")
    }

    override fun aucunTrajetDisponible() {
        Toast.makeText(context, "Aucune reservation", Toast.LENGTH_SHORT).show()
    }
}