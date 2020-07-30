package br.com.arnaudchess.model

import android.content.Context
import br.com.arnaudchess.R
import br.com.arnaudchess.ui.PieceImageView


class Archer(color: Boolean) : Piece(color) {

    override fun createImageView(context: Context): PieceImageView {
        return object : PieceImageView(context, this@Archer) {
            override var colors = Pair(
                R.drawable.ic_archer_white,
                R.drawable.ic_archer_black
            )

            init {
                setupIcon()
            }
        }
    }

    override fun canJumpWhileMoving(): Boolean {
        return true
    }

    override fun canJumpWhenCapturing(): Boolean {
        return true
    }

    override var isInvulnerable: Boolean = false

    override fun getLegalEndPositionsFrom(position: Int): ArrayList<Int> {
        return when (position) {

            else -> arrayListOf()
        }
    }

}