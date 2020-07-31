package br.com.arnaudchess.model

import android.content.Context
import br.com.arnaudchess.ui.PieceImageView

abstract class Piece(
    val color: Boolean
) {
    abstract fun getLegalEndPositionsFrom(position: Int): ArrayList<Int>
    abstract fun createImageView(context: Context): PieceImageView
    abstract fun canJumpWhileMoving(): Boolean
    abstract fun canJumpWhenCapturing(): Boolean
    abstract var isInvulnerable: Boolean

    companion object {
        const val WHITE = true
        const val BLACK = false
        const val UP = true
        const val DOWN = false
    }
}