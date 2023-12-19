import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.donovanSergeAimenHatim.uniroute.R
import com.donovanSergeAimenHatim.uniroute.ecrans.accueil.AccueilFragment
import com.donovanSergeAimenHatim.uniroute.ecrans.connexion.Model


class ConnexionFragment : Fragment(), Model.View {

    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var progressBar: ProgressBar

    private lateinit var presenter: Model.Presenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_connexion, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        usernameEditText = view.findViewById(R.id.usernameconnexion)
        passwordEditText = view.findViewById(R.id.motdepasseconnexion)
        loginButton = view.findViewById(R.id.loginButton)
        progressBar = view.findViewById(R.id.progressBar)


        presenter = Présentateur(this)

        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()
            presenter.seConnecter(username, password)
        }
    }

    override fun afficherChargement() {
        progressBar.visibility = View.VISIBLE
    }

    override fun cacherChargement() {
        progressBar.visibility = View.GONE
    }

    override fun afficherConnexionRéussie(username: String) {
        // Affichez le message de réussite ou naviguez vers la page suivante
        Toast.makeText(requireContext(), username, Toast.LENGTH_SHORT).show()
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val homeFragment = AccueilFragment()

        // Pour passer des données au fragment Home si nécessaire
        // val bundle = Bundle()
        // bundle.putString("username", username)
        // homeFragment.arguments = bundle

        fragmentTransaction.replace(R.id.fragment_container, homeFragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    override fun afficherConnexionÉchouée(error: String) {
        // Affichez le message d'erreur à l'utilisateur
        Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()

    }
}