package br.com.arnaudchess.ui

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import br.com.arnaudchess.model.Piece

abstract class PieceImageView(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int,
    open var piece: Piece?
) : AppCompatImageView(context, attrs, defStyleAttr) {
    constructor(context: Context, piece: Piece) : this(context, null, 0, piece)
    constructor(context: Context, attrs: AttributeSet?, piece: Piece) : this(
        context,
        attrs,
        0,
        piece
    )

    abstract var colors: Pair<Int, Int>

    fun setupIcon() {
        setImageResource(if (piece?.color == true) colors.first else colors.second)
    }

}