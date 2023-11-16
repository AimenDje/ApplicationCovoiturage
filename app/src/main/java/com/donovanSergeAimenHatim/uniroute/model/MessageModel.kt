package com.donovanSergeAimenHatim.uniroute.model
data class Message(val émmeteur: String, val contenu_du_message: String)

class MessageModel {
    private val listeDesMessages= mutableListOf<Message>()
    fun reccupererMessage():List<Message>{
        return listeDesMessages
    }
}