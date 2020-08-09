package br.com.arnaudchess.model

import android.content.Context
import br.com.arnaudchess.R
import br.com.arnaudchess.ui.PieceImageView


class Treasure: Piece(null) {

    lateinit var currentPieceImageView: PieceImageView

    override fun createImageView(context: Context): PieceImageView {
        currentPieceImageView = object : PieceImageView(context, this@Treasure, gold) {
            override var colors = Pair(
                R.drawable.ic_spoils,
                R.drawable.ic_spoils
            )

            init {
                setupIcon()
            }
        }
        return currentPieceImageView

    }

    override fun canJumpWhileMoving(): Boolean {
        return false
    }

    override fun canJumpWhenCapturing(): Boolean {
        return false
    }

    override var isMoved: Boolean = false

    override fun setDeadlyPiece(b: Boolean) {
        isDeadly = b
    }

    override fun getLegalEndPositionsFrom(position: Int): ArrayList<Int> {
        return arrayListOf()
    }

}