package com.donovanSergeAimenHatim.uniroute.ecrans.Liste_des_reservations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.donovanSergeAimenHatim.uniroute.R
import com.donovanSergeAimenHatim.uniroute.ecrans.listTrajets.ReservationsModele

/**
 * fragment ReservationsModele.
 */
class ReservationsFragment : Fragment(), ReservationsContract.View {

    private lateinit var presenter: ReservationsPresenter
    private lateinit var adapter: MyReservationsRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialisation du présentateur
        presenter = ReservationsPresenter(this, ReservationsModele())

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
                adapter = MyReservationsRecyclerViewAdapter(presenter.getReservations())
            }
        }
        return view
    }

    override fun displayReservations(reservationsModele: List<ReservationsModele.Reservation>) {
        adapter.updateData(reservationsModele)
    }
}
