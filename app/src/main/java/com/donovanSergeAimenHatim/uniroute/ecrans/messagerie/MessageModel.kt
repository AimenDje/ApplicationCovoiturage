package com.donovanSergeAimenHatim.uniroute.ecrans.messagerie
data class Message(val émmeteur: String, val contenu_du_message: String)

class MessageModel {
    private val listeDesMessages= mutableListOf<Message>()
    fun reccupererMessages():List<Message>{
        return listeDesMessages
    }
    fun ajouterMessage(message: Message){
       listeDesMessages.add(message)
    }
    fun supprimerUnMessage(message: Message){
        listeDesMessages.remove(message)
    }
}