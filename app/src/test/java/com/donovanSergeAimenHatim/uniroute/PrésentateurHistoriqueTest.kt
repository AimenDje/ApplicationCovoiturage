import com.donovanSergeAimenHatim.uniroute.ecrans.historique.HistoriqueFragment
import com.donovanSergeAimenHatim.uniroute.ecrans.historique.ModèleHistorique
import com.donovanSergeAimenHatim.uniroute.ecrans.historique.PrésentateurHistorique
import com.donovanSergeAimenHatim.uniroute.ecrans.listTrajets.TrajetDataManager
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.stubbing.OngoingStubbing

class PrésentateurHistoriqueTest {

    @Mock
    private lateinit var mockVue: HistoriqueFragment

    @Mock
    private lateinit var mockTrajetDataManager: TrajetDataManager

    @InjectMocks
    private lateinit var présentateurHistorique: PrésentateurHistorique

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        présentateurHistorique.trajetDataManager = mockTrajetDataManager
    }

    @Test
    fun testChargerHistorique() = runBlocking {
        val mockUtilisateurId = 1

        // Utilisez un vrai modèle pour chargerHistorique
        val mockTrajet = ModèleHistorique(
            "Historique des trajets",
            "Montréal",
            "2023-01-01",
            "Toronto"
        )

        `when`(mockTrajetDataManager.getTrajetById(mockUtilisateurId)).thenReturn(mockTrajet)

        val historique = présentateurHistorique.ChargerHistorique(mockUtilisateurId)

        // Assurez-vous que les assertions nécessaires sont effectuées après le chargement de l'historique
        assertEquals("Historique des trajets", historique?.titre)
        assertEquals("Montréal", historique?.villeDepart)
        assertEquals("2023-01-01", historique?.date)
        assertEquals("Toronto", historique?.villeDestination)
    }
}

private fun <T> OngoingStubbing<T>.thenReturn(mockTrajet: ModèleHistorique) {
    TODO("Not yet implemented")
}
