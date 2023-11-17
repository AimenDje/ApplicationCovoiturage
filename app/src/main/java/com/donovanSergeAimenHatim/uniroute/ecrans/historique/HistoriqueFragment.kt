package com.donovanSergeAimenHatim.uniroute.ecrans.historique

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.donovanSergeAimenHatim.uniroute.R
import com.donovanSergeAimenHatim.uniroute.ecrans.listTrajets.listTrajets
import com.google.android.material.floatingactionbutton.FloatingActionButton
/*// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [listTrajets.newInstance] factory method to
 * create an instance of this fragment.
 */*/

class HistoriqueFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_historique, container, false)
    }

    override fun onViewCreated(view : View, savedInstanceState: Bundle?){
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
        titre_vue1.text = présentateur_historique.trajets.titre
        premier_trajet.text = présentateur_historique.trajets.trajetMontrealToronto
        deuxieme_trajet.text = présentateur_historique.trajets.trajetCalgaryVancouver
        troisieme_trajet.text = présentateur_historique.trajets.trajetMississaugaHamilton
        quatrieme_trajet.text = présentateur_historique.trajets.trajetEdmontonWinnipeg

        // Les bouttons de redirection vers le fragment des details d'un trajet
        bouton_Trajet1.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.apply {
                replace(R.id.fragment_container, DétailsHistoriqueFragment())
                addToBackStack(null)
                commit()
            }
        }

        bouton_Trajet2.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.apply {
                replace(R.id.fragment_container, DétailsHistoriqueFragment())
                addToBackStack(null)
                commit()
            }
        }

        bouton_Trajet3.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.apply {
                replace(R.id.fragment_container, DétailsHistoriqueFragment())
                addToBackStack(null)
                commit()
            }
        }

        bouton_Trajet4.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.apply {
                replace(R.id.fragment_container, DétailsHistoriqueFragment())
                addToBackStack(null)
                commit()
            }
        }
    }
}