package com.donovanSergeAimenHatim.uniroute.ecrans.carte

import android.animation.Animator
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.donovanSergeAimenHatim.uniroute.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.WellKnownTileServer
import com.mapbox.mapboxsdk.camera.CameraPosition
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.maps.MapView
import com.mapbox.mapboxsdk.maps.MapboxMap
import java.util.Random


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CarteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CarteFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var mapView: MapView
    private lateinit var mapboxMap: MapboxMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val key = "Kd8fNeAwnaLndC5iyzVm"
        Mapbox.getInstance(requireContext(), key, WellKnownTileServer.MapTiler)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(com.donovanSergeAimenHatim.uniroute.R.layout.fragment_carte, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val key = "Kd8fNeAwnaLndC5iyzVm"
        val mapId = "fc164886-d314-466d-8f45-a987c6a2b659"
        val styleUrl = "https://api.maptiler.com/maps/$mapId/style.json?key=$key"
        Mapbox.getInstance(requireContext())

        mapView = view.findViewById(R.id.mapView)
        mapView!!.getMapAsync { map -> this.mapboxMap = map
            map.setStyle(styleUrl) {
                // Remplacement par une localisation fixe
                val latLng = LatLng(48.8584, 2.2945) // Coordonnées de la Tour Eiffel
                val position = CameraPosition.Builder()
                    .target(latLng)
                    .zoom(10.0)
                    .build()
                map.moveCamera(CameraUpdateFactory.newCameraPosition(position))
                animateCameraRandomly()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }


    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CarteFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CarteFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    override fun onStart() {
        super.onStart()
        mapView.onStart()

    }

    override fun onStop() {
        mapView.onStop()
        super.onStop()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    private fun animateCameraRandomly() {
        mapboxMap.cameraPosition.target?.let { currentLatLng ->
            val random = Random()

            // Calculer une nouvelle position
            val newLat = currentLatLng.latitude + (random.nextDouble() - 1) / 1000 // Changement mineur
            val newLng = currentLatLng.longitude + (random.nextDouble() - 1) / 1000 // Changement mineur
            val newLatLng = LatLng(newLat, newLng)
            val valueAnimator = ValueAnimator.ofFloat(0f, 1f).apply {
                duration = 30000
                addUpdateListener { animation ->
                    val fraction = animation.animatedFraction
                    val interpolatedLatLng = interpolateLatLng(currentLatLng, newLatLng, fraction)
                    mapboxMap.moveCamera(CameraUpdateFactory.newLatLng(interpolatedLatLng))
                }
                addListener(object : Animator.AnimatorListener {
                    override fun onAnimationRepeat(p0: Animator) {}
                    override fun onAnimationCancel(p0: Animator) {}
                    override fun onAnimationStart(animation: Animator, isReverse: Boolean) {}
                    override fun onAnimationStart(p0: Animator) {}

                    override fun onAnimationEnd(animation: Animator, isReverse: Boolean) {
                        animateCameraRandomly()
                    }

                    override fun onAnimationEnd(p0: Animator) {}
                })
            }
            valueAnimator.start()
        }
    }


    private fun interpolateLatLng(start: LatLng, end: LatLng, fraction: Float): LatLng {
        val lat = (end.latitude - start.latitude) * fraction + start.latitude
        val lng = (end.longitude - start.longitude) * fraction + start.longitude
        return LatLng(lat, lng)
    }
}
