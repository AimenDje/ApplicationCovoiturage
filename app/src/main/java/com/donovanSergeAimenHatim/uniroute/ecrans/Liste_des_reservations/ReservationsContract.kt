package com.donovanSergeAimenHatim.uniroute.ecrans.Liste_des_reservations

import com.donovanSergeAimenHatim.uniroute.ecrans.listTrajets.ReservationsModele

interface ReservationsContract {
    interface View {
        fun displayReservations(reservationsModele: List<ReservationsModele.Reservation>)
    }
}
