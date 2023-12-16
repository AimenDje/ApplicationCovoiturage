package com.donovanSergeAimenHatim.uniroute.ecrans.Liste_des_reservations

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.donovanSergeAimenHatim.uniroute.R
import com.donovanSergeAimenHatim.uniroute.ecrans.listTrajets.Reservations

/**
 * fragment Reservations.
 */
class ReservationsFragment : Fragment() {

    private lateinit var reservations: Reservations

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Créez une instance de la classe Reservations ici
        reservations = Reservations()

        // TODO: Chargez les réservations depuis une source de données
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_reservations_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = LinearLayoutManager(context)
                adapter = MyreservationsRecyclerViewAdapter(reservations)
            }
        }
        return view
    }
}
