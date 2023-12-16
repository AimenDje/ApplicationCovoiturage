package com.donovanSergeAimenHatim.uniroute.ecrans.listTrajets

class ReservationsModele {
    val reservationsList: MutableList<Reservation> = mutableListOf()

    fun ajouterReservation(id: Int, utilisateurID: Int, trajet: Trajets) {
        val reservation = Reservation(id, utilisateurID, trajet)
        reservationsList.add(reservation)
    }

    fun getReservationsForUser(utilisateurID: Int): List<Reservation> {
        return reservationsList.filter { it.utilisateurID == utilisateurID }
    }

    inner class Reservation(
        val id: Int,
        val utilisateurID: Int,
        val trajet: Trajets
    ) {

        fun getReservationInfo(): String {
            return "Réservation Info - ID: $id, UtilisateurID: $utilisateurID, Trajet: ${trajet.getTrajetDescription()}"
        }
    }
}
