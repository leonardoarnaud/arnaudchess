package br.com.arnaudchess.model

import android.content.Context
import br.com.arnaudchess.R
import br.com.arnaudchess.ui.PieceImageView

class Viking : Knight(true) {

    val queenSpirit = Queen(color)

    override fun getLegalEndPositionsFrom(position: Int): ArrayList<Int> {
        return arrayListOf<Int>().apply {
            addAll(queenSpirit.getLegalEndPositionsFrom(position))
            addAll(super.getLegalEndPositionsFrom(position))
        }
    }

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

    override var isDeadly: Boolean = false


}