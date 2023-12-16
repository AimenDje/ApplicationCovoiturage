package com.donovanSergeAimenHatim.uniroute.ecrans.Liste_des_reservations

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView

import com.donovanSergeAimenHatim.uniroute.ecrans.Liste_des_reservations.placeholder.PlaceholderContent.PlaceholderItem
import com.donovanSergeAimenHatim.uniroute.databinding.FragmentItemBinding
import com.donovanSergeAimenHatim.uniroute.ecrans.listTrajets.Reservations

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyreservationsRecyclerViewAdapter(
        private val values: List <Reservations.Reservation>
)
    : RecyclerView.Adapter<MyreservationsRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

    return ViewHolder(FragmentItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.idView.text = item.idReservation.toString()
        holder.contentView.text = item.getReservationInfo()
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val idView: TextView = binding.itemNumber
        val contentView: TextView = binding.content

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

}