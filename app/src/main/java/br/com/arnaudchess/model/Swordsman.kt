package br.com.arnaudchess.model

import android.content.Context
import br.com.arnaudchess.R
import br.com.arnaudchess.ui.PieceImageView

class Swordsman(color: Boolean) : Piece(color) {

    override fun getLegalEndPositionsFrom(position: Int): ArrayList<Int> {
        return arrayListOf()
    }

    override fun createImageView(context: Context): PieceImageView {
        return object : PieceImageView(context, this@Swordsman) {
            override var colors = Pair(
                R.drawable.ic_swordsman_white,
                R.drawable.ic_swordsman_black
            )

            init {
                setupIcon()
            }
        }
    }
}