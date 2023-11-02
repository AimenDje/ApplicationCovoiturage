package com.donovanSergeAimenHatim.uniroute.ecrans.accueil

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.donovanSergeAimenHatim.uniroute.R
import com.google.android.material.textfield.TextInputEditText
import android.app.DatePickerDialog
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import com.google.android.material.button.MaterialButtonToggleGroup
import com.google.android.material.datepicker.MaterialDatePicker
import java.util.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AccueilFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AccueilFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_accueil, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val datePickerEditText = view.findViewById<TextInputEditText>(R.id.datePickerEditText)
        datePickerEditText.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Date")
                .build()
            datePicker.show(childFragmentManager, datePicker.toString())
            datePicker.addOnPositiveButtonClickListener { selection ->
                val selectedDate = datePicker.headerText
                datePickerEditText.setText(selectedDate)
            }
        }
        val fadeIn = AnimationUtils.loadAnimation(context, R.anim.fade_in)
        val fadeOut = AnimationUtils.loadAnimation(context, R.anim.fade_out)
        val toggleGroup = view.findViewById<MaterialButtonToggleGroup>(R.id.toggleButton)
        val trouverSection = view.findViewById<LinearLayout>(R.id.trouverSection)
        val proposeSection = view.findViewById<LinearLayout>(R.id.proposeSection)
        toggleGroup.addOnButtonCheckedListener { group, checkedId, isChecked ->
            when (checkedId) {
                R.id.chercheCoVoiturage -> {
                    if (isChecked) {
                        proposeSection.startAnimation(fadeOut)
                        proposeSection.visibility = View.GONE

                        trouverSection.startAnimation(fadeIn)
                        trouverSection.visibility = View.VISIBLE
                    }
                }
                R.id.proposeCoVoiturage -> {
                    if (isChecked) {
                        trouverSection.startAnimation(fadeOut)
                        trouverSection.visibility = View.GONE

                        proposeSection.startAnimation(fadeIn)
                        proposeSection.visibility = View.VISIBLE
                    }
                }
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
         * @return A new instance of fragment AccueilFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AccueilFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}