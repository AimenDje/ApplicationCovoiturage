import com.donovanSergeAimenHatim.uniroute.ecrans.connexion.Model

class Présentateur(private val view: Model.View) : Model.Presenter {

    override fun seConnecter(username: String, password: String) {
        // Simuler une opération de connexion (vous devriez effectuer cela de manière asynchrone dans un thread séparé)
        view.afficherChargement()

        // Validation simple (à remplacer par une vérification réelle avec votre backend)
        if (username == "root" && password == "root") {
            // Simulation d'une connexion réussie après une courte attente (2 secondes)
            android.os.Handler().postDelayed({
                view.cacherChargement()
                view.affichageConnexionRéussie("Bienvenue "+username +"!")
            }, 2000)
        } else {
            // Simulation d'une erreur de connexion après une courte attente (2 secondes)
            android.os.Handler().postDelayed({
                view.cacherChargement()
                view.affichageConnexionÉchouée("Nom d'utilisateur ou mot de passe incorrect")
            }, 2000)
        }
    }
}
