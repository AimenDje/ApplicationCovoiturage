package com.donovanSergeAimenHatim.uniroute.ecrans.Liste_des_reservations

import com.donovanSergeAimenHatim.uniroute.ecrans.listTrajets.ReservationsModele

class ReservationsPresenter(
    private val view: ReservationsContract.View,
    private val model: ReservationsModele
) {

    fun getReservations(): List<ReservationsModele.Reservation> {
        // Retourne les réservations du modèle
        return model.reservationsList
    }

    // Méthode pour charger les réservations depuis le modèle et les présenter à la vue
    fun loadReservations() {
        val reservationsList = model.reservationsList
        view.displayReservations(reservationsList)
    }
}
