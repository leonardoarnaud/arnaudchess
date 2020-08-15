package br.com.arnaudchess.model

class Room(
    val player1: Player,
    val player2: Player? = null,
    val plays: ArrayList<Play> = arrayListOf()
){

    class Play (
        val color: Boolean,
        val start: String,
        val end: String
    )

    class Player (
        val uid: String,
        val name: String
    )
}