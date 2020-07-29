package br.com.arnaudchess.model

import android.content.Context
import br.com.arnaudchess.R
import br.com.arnaudchess.ui.PieceImageView

class Viking : Piece(true) {

    override fun getLegalEndPositionsFrom(position: Int): ArrayList<Int> {
        return arrayListOf()
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
}