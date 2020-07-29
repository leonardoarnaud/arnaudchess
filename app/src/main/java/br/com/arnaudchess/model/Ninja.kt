package br.com.arnaudchess.model

import android.content.Context
import br.com.arnaudchess.R
import br.com.arnaudchess.ui.PieceImageView

class Ninja : Piece(false) {

    override fun getLegalEndPositionsFrom(position: Int): ArrayList<Int> {
        return arrayListOf()
    }

    override fun createImageView(context: Context): PieceImageView {
        return object : PieceImageView(context, this@Ninja) {
            override var colors = Pair(
                R.drawable.ic_ninja_black,
                R.drawable.ic_ninja_black
            )

            init {
                setupIcon()
            }
        }
    }
}