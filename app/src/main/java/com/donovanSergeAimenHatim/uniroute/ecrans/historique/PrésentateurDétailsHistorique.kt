package com.donovanSergeAimenHatim.uniroute.ecrans.historique

import android.util.Log
import com.donovanSergeAimenHatim.uniroute.ecrans.listTrajets.TrajetDataManager
import com.donovanSergeAimenHatim.uniroute.model.ModelUniRoute
import com.donovanSergeAimenHatim.uniroute.sourceDeDonnées.SourceKelconke
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PrésentateurDétailsHistorique (val vue : DétailsHistoriqueFragment){
    private lateinit var trajetDataManager : TrajetDataManager
    suspend fun ChargerDetailsHistorique(trajetId : Int): ModelUniRoute.DetailsHistorique? {
            val sourceKelconque = SourceKelconke()
            trajetDataManager = TrajetDataManager(sourceKelconque)
            val trajeth = withContext(Dispatchers.IO) {
                trajetDataManager.getTrajetById(trajetId)
            }
        return if (trajeth != null) {
            Log.d("historiqueTrajet", "NON NULL ${trajeth.villeDepart}")
            ModelUniRoute.DetailsHistorique(
                "Détails du trajets", trajeth.villeDepart, trajeth.date, trajeth.villeDestination,
                trajeth.nbPassager, trajeth.priseCharge, trajeth.prixTrajet, trajeth.dureeTrajet,
                trajeth.distanceTrajet, trajeth.modelVehicule
            )
        } else {
            Log.d("getTrajetById", "NULL")
            null
        }
    }


}