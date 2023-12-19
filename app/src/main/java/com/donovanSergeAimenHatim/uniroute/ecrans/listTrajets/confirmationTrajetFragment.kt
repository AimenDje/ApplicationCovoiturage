package com.donovanSergeAimenHatim.uniroute.ecrans.listTrajets

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.donovanSergeAimenHatim.uniroute.R
import com.donovanSergeAimenHatim.uniroute.ecrans.accueil.AccueilFragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [confirmationTrajetFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class confirmationTrajetFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var detailText: TextView? = null
    var conducteur: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        conducteur = arguments?.getString("nomConducteur")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_confirmation_trajet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fadeIn = AnimationUtils.loadAnimation(context, R.anim.fade_in)
        val fadeOut = AnimationUtils.loadAnimation(context, R.anim.fade_out)
        val confirmationTrajetSection = view.findViewById<LinearLayout>(R.id.containerConfirmation)
        val button_viewReservation = view.findViewById<Button>(R.id.button_viewReservation)
        confirmationTrajetSection.startAnimation(fadeIn)
        confirmationTrajetSection.visibility = View.VISIBLE
        var detailText: TextView? = view.findViewById(R.id.textView_confirmation_detail)
        detailText?.text = "Super ! Votre trajet a été réservé. \n" +
                "En cas de modification  de la part de ${conducteur} vous serez notifié.\n" +
                "Vous pouvez voir l’état de votre réservation en appuyant sur le bouton “Voir mes réservation”"
        button_viewReservation.setOnClickListener{
            activity?.supportFragmentManager?.beginTransaction()?.apply {
                replace(R.id.fragment_container, TrajetReserverFragment())
                addToBackStack(null)
                commit()
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
         * @return A new instance of fragment confirmationTrajetFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            confirmationTrajetFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}