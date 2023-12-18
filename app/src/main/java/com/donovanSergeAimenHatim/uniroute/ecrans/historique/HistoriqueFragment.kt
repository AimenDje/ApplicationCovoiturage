
package com.donovanSergeAimenHatim.uniroute.ecrans.historique

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.donovanSergeAimenHatim.uniroute.R
import com.donovanSergeAimenHatim.uniroute.animation.anim
import com.donovanSergeAimenHatim.uniroute.ecrans.listTrajets.TrajetDataManager
import com.donovanSergeAimenHatim.uniroute.ecrans.listTrajets.Trajets
import com.donovanSergeAimenHatim.uniroute.ecrans.listTrajets.TrajetsPresenter
import com.donovanSergeAimenHatim.uniroute.sourceDeDonnées.SourceKelconke
import com.donovanSergeAimenHatim.uniroute.utilisateur.UtilisateurDataManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HistoriqueFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HistoriqueFragment : Fragment(), HistoriqueInterface.View {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var presentateur: PrésentateurHistorique
    private lateinit var userDataManager: UtilisateurDataManager
    private lateinit var animation: anim
    val critere = StringBuilder()
    var loadingLogo: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        val sourceKelconke = SourceKelconke()
        userDataManager = UtilisateurDataManager(sourceKelconke)
        val dataManager = TrajetDataManager(sourceKelconke)
        val userDataManager = UtilisateurDataManager(sourceKelconke)
        presentateur = PrésentateurHistorique(this, dataManager, userDataManager)
        GlobalScope.launch(Dispatchers.Main) {
            try {
                presentateur.ChargerHistorique(99)


            } catch (e: Exception) { afficherErreur(e.message ?: "Erreur inconnue")
            }
        }
        animation = anim()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_historique, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        //Stockage et affichage dinamyque des données sur le fragemnt de la liste des trajets
        ///var présentateur_historique = PrésentateurHistorique(this)
        loadingLogo = view.findViewById<ProgressBar>(R.id.progressBar_loading)
    }

    @SuppressLint("MissingInflatedId")
     override fun afficherHistorique(trajets: List<Trajets>){
        val fadeIn = AnimationUtils.loadAnimation(context, R.anim.fade_in)
        val fadeOut = AnimationUtils.loadAnimation(context, R.anim.fade_out)
        val containerListe = view?.findViewById<LinearLayout>(R.id.historiques_trajets_layout)
        val titreHitorique = view?.findViewById<TextView>(R.id.titre_historique_trajets)
        val trajetViews = mutableListOf<View>()
        containerListe?.removeAllViews()
        trajets.forEachIndexed { index, trajets ->
            val trajetView =
                LayoutInflater.from(context).inflate(R.layout.fragment_historique, containerListe, false)
            //trajetViews.add(trajetView)
            //trajetView.startAnimation(fadeIn)
            //loadingLogo?.visibility = View.GONE

            val nomConducteur = trajetView.findViewById<TextView>(R.id.textView_nom_conducteur_trajet)
            val villeDepartDestination = trajetView.findViewById<TextView>(R.id.textView_destination_trajet)
            val dateTrajet = trajetView.findViewById<TextView>(R.id.textView_date_trajet)

            GlobalScope.launch(Dispatchers.Main) {
                val utilisateur = presentateur.chargerUtilisateur(trajets.utilisateurID)
                if (utilisateur != null) {
                    nomConducteur.text = "${utilisateur.nom} ${utilisateur.prenom}"
                } else {
                    afficherErreur("Erreur utilisateur non trouver")
                }
            }
            titreHitorique?.text = "Historique des trajets"
            villeDepartDestination.text = "${trajets.villeDepart} ${trajets.villeDestination}"
            dateTrajet.text = "${trajets.date}"

            trajetView.findViewById<LinearLayout>(R.id.historiques_trajets_layout).setOnClickListener {
                trajetViews.forEach { view ->
                    view.findViewById<LinearLayout>(R.id.linearLayout_item_trajets).visibility = View.GONE
                }
            }
            containerListe?.addView(trajetView)
        }
    }
    override fun afficherErreur(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HistoriqueFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HistoriqueFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}