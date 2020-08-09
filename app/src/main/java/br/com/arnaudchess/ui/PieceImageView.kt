package br.com.arnaudchess.ui

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import br.com.arnaudchess.R
import br.com.arnaudchess.model.Piece
import br.com.arnaudchess.model.Piece.Companion.WHITE
import br.com.arnaudchess.tips.SimpleTooltip


abstract class PieceImageView(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int,
    open var piece: Piece?,
    var gold: Int
) : AppCompatImageView(context, attrs, defStyleAttr) {
    constructor(context: Context, piece: Piece, gold: Int) : this(context, null, 0, piece, gold)
    constructor(context: Context, attrs: AttributeSet?, piece: Piece, gold: Int) : this(context, attrs, 0, piece, gold)

    abstract var colors: Pair<Int, Int>

    fun setupIcon() {
        setImageResource(if (piece?.color == true) colors.first else colors.second)
    }

    fun setDeadly(value: Boolean) {
        background = if (value){
            ContextCompat.getDrawable(context,
                if (piece?.color == WHITE) R.drawable.ic_shine_blued else R.drawable.ic_shine_golden
            )
        } else null
    }

    fun showGoldAmount(){
        SimpleTooltip.Builder(context)
            .anchorView(this)
            .text(" $$gold ")
            .gravity(Gravity.BOTTOM)
            .animated(false)
            .transparentOverlay(true)
            .setHeight(context.resources.getDimensionPixelOffset(R.dimen.tooltip_height))
            .apply { ContextCompat.getColor(context, R.color.text_gold_piece_color).let {
                arrowColor(it)
                backgroundColor(it)
            }}.build()
            .show()

    }

}