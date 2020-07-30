package br.com.arnaudchess.model

import android.content.Context
import br.com.arnaudchess.R
import br.com.arnaudchess.ui.PieceImageView

class King(color: Boolean) : Piece(color) {



    override fun createImageView(context: Context): PieceImageView {
        return object : PieceImageView(context, this@King) {
            override var colors = Pair(
                R.drawable.ic_king_white,
                R.drawable.ic_king_black
            )

            init {
                setupIcon()
            }
        }
    }

    override fun canJumpWhileMoving(): Boolean {
        return false
    }

    override fun canJumpWhenCapturing(): Boolean {
        return false
    }

    override fun getLegalEndPositionsFrom(position: Int): ArrayList<Int> {
        return arrayListOf()
    }

    override var isInvulnerable: Boolean = false
}