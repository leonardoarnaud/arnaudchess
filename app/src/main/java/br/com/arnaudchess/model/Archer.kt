package br.com.arnaudchess.model

import android.content.Context
import br.com.arnaudchess.R
import br.com.arnaudchess.ui.PieceImageView


class Archer(color: Boolean) : Piece(color) {

    override fun getLegalEndPositionsFrom(position: Int): ArrayList<Int> {
        return arrayListOf()
    }

    override fun createImageView(context: Context): PieceImageView {
        return object : PieceImageView(context, this@Archer) {
            override var colors = Pair(
                R.drawable.ic_archer_white,
                R.drawable.ic_archer_black
            )

            init {
                setupIcon()
                context.resources.getDimensionPixelOffset(R.dimen.piece_padding).let {
                    setPadding(it, it, it, it)
                }
            }
        }
    }
}