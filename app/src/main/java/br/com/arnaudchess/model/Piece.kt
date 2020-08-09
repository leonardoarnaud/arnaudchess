package br.com.arnaudchess.model

import android.content.Context
import br.com.arnaudchess.ui.PieceImageView

abstract class Piece(
    var color: Boolean?
): Cloneable {
    abstract fun getLegalEndPositionsFrom(position: Int): ArrayList<Int>
    abstract fun createImageView(context: Context): PieceImageView
    abstract fun canJumpWhileMoving(): Boolean
    abstract fun canJumpWhenCapturing(): Boolean
    abstract var isMoved: Boolean

    abstract fun setDeadlyPiece(b: Boolean)

    var isDeadly: Boolean = false
    var gold: Int = 0

    public override fun clone(): Piece {
        return super.clone() as Piece
    }

    companion object {
        const val WHITE = true
        const val BLACK = false
        const val UP = true
        const val DOWN = false
    }

}