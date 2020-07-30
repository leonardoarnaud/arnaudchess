package br.com.arnaudchess.model

import android.content.Context
import br.com.arnaudchess.R
import br.com.arnaudchess.ui.PieceImageView

class Catapult(color: Boolean) : Piece(color) {



    override fun createImageView(context: Context): PieceImageView {
        return object : PieceImageView(context, this@Catapult) {
            override var colors = Pair(
                R.drawable.ic_catapult_white,
                R.drawable.ic_catapult_black
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
        return true
    }

    override var isInvulnerable: Boolean = false

    override fun getLegalEndPositionsFrom(position: Int): ArrayList<Int> {
        return arrayListOf()
    }
}