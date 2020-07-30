package br.com.arnaudchess.model

import android.content.Context
import br.com.arnaudchess.R
import br.com.arnaudchess.ui.PieceImageView

class Viking : Piece(true) {

    override fun createImageView(context: Context): PieceImageView {
        return object : PieceImageView(context, this@Viking) {
            override var colors = Pair(
                R.drawable.ic_viking_white,
                R.drawable.ic_viking_white
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

    override var isInvulnerable: Boolean = false

    override fun getLegalEndPositionsFrom(position: Int): ArrayList<Int> {
        return arrayListOf()
    }
}