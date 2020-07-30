package br.com.arnaudchess.model

import android.content.Context
import br.com.arnaudchess.R
import br.com.arnaudchess.ui.PieceImageView

class Queen(color: Boolean) : Piece(color) {

    private val rook = Rook(color)
    private val bishop = Bishop(color)

    override fun getLegalEndPositionsFrom(position: Int): ArrayList<Int> {
        return arrayListOf<Int>().apply {
            addAll(rook.getLegalEndPositionsFrom(position))
            addAll(bishop.getLegalEndPositionsFrom(position))
        }
    }

    override fun createImageView(context: Context): PieceImageView {
        return object : PieceImageView(context, this@Queen) {
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

    override var isInvulnerable: Boolean = false
}