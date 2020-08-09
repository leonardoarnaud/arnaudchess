package br.com.arnaudchess.model

import android.content.Context
import br.com.arnaudchess.R
import br.com.arnaudchess.ui.PieceImageView

class Queen(color: Boolean) : Piece(color) {

    private val rookSpirit = Rook(color)
    private val bishopSpirit = Bishop(color)

    override fun getLegalEndPositionsFrom(position: Int): ArrayList<Int> {
        return arrayListOf<Int>().apply {
            addAll(rookSpirit.getLegalEndPositionsFrom(position))
            addAll(bishopSpirit.getLegalEndPositionsFrom(position))
        }
    }

    override var priceToMove: Int = 100

    override fun createImageView(context: Context): PieceImageView {
        return object : PieceImageView(context, this@Queen, gold) {
            override var colors = Pair(
                R.drawable.ic_queen_white,
                R.drawable.ic_queen_black
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

    override fun setDeadlyPiece(b: Boolean) {
        isDeadly = b
    }

    override var isMoved: Boolean = false
}