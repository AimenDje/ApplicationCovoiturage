package com.donovanSergeAimenHatim.uniroute.ecrans.Liste_des_reservations

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.donovanSergeAimenHatim.uniroute.databinding.FragmentItemBinding
import com.donovanSergeAimenHatim.uniroute.ecrans.listTrajets.ReservationsModele

class MyReservationsRecyclerViewAdapter(reservationsModele: List<ReservationsModele.Reservation>) :
    RecyclerView.Adapter<MyReservationsRecyclerViewAdapter.ViewHolder>() {

    private var reservationsModele: List<ReservationsModele.Reservation> = emptyList()

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newReservationsModele: List<ReservationsModele.Reservation>) {
        reservationsModele = newReservationsModele
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            FragmentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = reservationsModele[position]
        holder.idView.text = item.id.toString()
        holder.contentView.text = item.getReservationInfo()
    }

    override fun getItemCount(): Int = reservationsModele.size

    inner class ViewHolder(binding: FragmentItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val idView: TextView = binding.itemNumber
        val contentView: TextView = binding.content

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }
}
