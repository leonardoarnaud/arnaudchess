package br.com.arnaudchess.ui

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.annotation.Nullable
import androidx.core.content.ContextCompat
import br.com.arnaudchess.R


class BoardPositionFrameLayout : FrameLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, @Nullable attrs: AttributeSet?) : super(context, attrs)
    constructor(
        context: Context,
        @Nullable attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec)
    }

    fun putPiece(piece: PieceImageView) {
        removeAllViews()
        addView(piece)
    }

    fun removePiece() {
        removeAllViews()
    }

    fun select() {
        foreground = ContextCompat.getDrawable(context, R.drawable.border_light)
    }

    fun hint() {
        foreground = ContextCompat.getDrawable(context, R.drawable.border_green)
    }

    fun removeBorder() {
        foreground = null
    }

    fun containsPiece(): Boolean {
        return childCount > 0
    }

    fun getPieceImageView(): PieceImageView? {
        return try {
            getChildAt(0) as PieceImageView
        } catch (e: Exception) {
            null
        }
    }
}