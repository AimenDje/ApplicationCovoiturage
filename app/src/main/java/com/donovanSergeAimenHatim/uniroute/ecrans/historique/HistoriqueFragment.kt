
package com.donovanSergeAimenHatim.uniroute.ecrans.historique

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.donovanSergeAimenHatim.uniroute.R
import com.donovanSergeAimenHatim.uniroute.ecrans.listTrajets.Trajets
import com.donovanSergeAimenHatim.uniroute.trajet.Trajet





// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HistoriqueFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HistoriqueFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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
        return inflater.inflate(R.layout.fragment_historique, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var titre_vue1 : TextView = view.findViewById(R.id.titreHistoriqueTrajet)
        var premier_trajet : TextView = view.findViewById(R.id.trajetMontrealToronto)
        var deuxieme_trajet : TextView = view.findViewById(R.id.trajetCalgaryVancouver)
        var troisieme_trajet : TextView = view.findViewById(R.id.trajetMississaugeHamilton)
        var quatrieme_trajet : TextView = view.findViewById(R.id.trajetEdmontonWinnipeg)
        var bouton_Trajet1 : FloatingActionButton = view.findViewById(R.id.btn_Trajet1)
        var bouton_Trajet2 : FloatingActionButton = view.findViewById(R.id.btn_trajet2)
        var bouton_Trajet3 : FloatingActionButton = view.findViewById(R.id.btn_trajet3)
        var bouton_Trajet4 : FloatingActionButton = view.findViewById(R.id.btn_trajet4)
        // Stockage et affichage dynamique des données sur le fragment de la liste des trajets
        var présentateur_historique = PrésentateurHistorique(this);

    /*
    override fun afficherHistorique(trajets: List<Trajet>){
        val container = view?.findViewById<LinearLayout>(R.id.linear_layout_for_items)
        val trajetViews = mutableListOf<View>()
        container?.removeAllViews()
        trajets.forEachIndexed { index, trajet ->
            val trajetView =
                LayoutInflater.from(context).inflate(R.layout.item_trajet, container, false)
            trajetViews.add(trajetView)

            container?.addView(trajetView)
        }
    }*/

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