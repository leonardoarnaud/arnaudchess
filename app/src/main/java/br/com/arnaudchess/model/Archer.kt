package br.com.arnaudchess.model

import android.content.Context
import br.com.arnaudchess.*
import br.com.arnaudchess.ui.PieceImageView


class Archer(color: Boolean) : Piece(color) {

    val kingSpirit = King(color)

    override fun createImageView(context: Context): PieceImageView {
        return object : PieceImageView(context, this@Archer) {
            override var colors = Pair(
                R.drawable.ic_archer_white,
                R.drawable.ic_archer_black
            )

            init {
                setupIcon()
            }
        }
    }

    override fun canJumpWhileMoving(): Boolean {
        return true
    }

    override fun canJumpWhenCapturing(): Boolean {
        return false
    }

    override var isDeadly: Boolean = false

    override var isMoved: Boolean = false

    override fun getLegalEndPositionsFrom(position: Int): ArrayList<Int> {
        return arrayListOf<Int>().apply {
            addAll(kingSpirit.getLegalEndPositionsFrom(position))
            addAll(when (position) {
                _a1 -> arrayListOf(_a3, _c1, _c3)
                _a2 -> arrayListOf(_a4, _c2, _c4)
                _a3 -> arrayListOf(_a5, _c3, _a1, _c5, _c1)
                _a4 -> arrayListOf(_a6, _c4, _a2, _c6, _c2)
                _a5 -> arrayListOf(_a7, _c5, _a3, _c7, _c3)
                _a6 -> arrayListOf(_a8, _c6, _a4, _c8, _c4)
                _a7 -> arrayListOf(_c7, _a5, _c5)
                _a8 -> arrayListOf(_c8, _a6, _c6)

                _b1 -> arrayListOf(_b3, _d1, _d3)
                _b2 -> arrayListOf(_b4, _d2, _d4)
                _b3 -> arrayListOf(_b5, _d3, _b1, _d5, _d1)
                _b4 -> arrayListOf(_b6, _d4, _b2, _d6, _d2)
                _b5 -> arrayListOf(_b7, _d5, _b3, _d7, _d3)
                _b6 -> arrayListOf(_b8, _d6, _b4, _d8, _d4)
                _b7 -> arrayListOf(_d7, _b5, _d5)
                _b8 -> arrayListOf(_d8, _b6, _d6)

                _c1 -> arrayListOf(_c3, _e1, _a1          , _e3, _a3)
                _c2 -> arrayListOf(_c4, _e2, _a2          , _e4, _a4)
                _c3 -> arrayListOf(_c5, _e3, _c1, _a3, _e5, _e1, _a5, _a1)
                _c4 -> arrayListOf(_c6, _e4, _c2, _a4, _e6, _e2, _a6, _a2)
                _c5 -> arrayListOf(_c7, _e5, _c3, _a5, _e7, _e3, _a7, _a3)
                _c6 -> arrayListOf(_c8, _e6, _c4, _a6, _e8, _e4, _a8, _a4)
                _c7 -> arrayListOf(_e7, _c5, _a7          , _e5, _a5)
                _c8 -> arrayListOf(_e8, _c6, _a8          , _e6, _a6)

                _d1 -> arrayListOf(_d3, _f1, _b1     , _f3, _b3)
                _d2 -> arrayListOf(_d4, _f2, _b2     , _f4, _b4)
                _d3 -> arrayListOf(_d5, _f3, _d1, _b3, _f1, _b5, _b1, _f5)
                _d4 -> arrayListOf(_d6, _f4, _d2, _b4, _f2, _b6, _b2, _f6)
                _d5 -> arrayListOf(_d7, _f5, _d3, _b5, _f3, _b7, _b3, _f7)
                _d6 -> arrayListOf(_d8, _f6, _d4, _b6, _f4, _b8, _b4, _f8)
                _d7 -> arrayListOf(_f7, _d5, _b7     , _f5, _b5)
                _d8 -> arrayListOf(_f8, _d6, _b8     , _f6, _b6)

                _e1 -> arrayListOf(_e3, _g1, _c1      , _g3, _c3)
                _e2 -> arrayListOf(_e4, _g2, _c2      , _g4, _c4)
                _e3 -> arrayListOf(_e5, _g3, _e1, _c3 , _g1, _c5, _c1, _g5)
                _e4 -> arrayListOf(_e6, _g4, _e2, _c4 , _g2, _c6, _c2, _g6)
                _e5 -> arrayListOf(_e7, _g5, _e3, _c5 , _g3, _c7, _c3, _g7)
                _e6 -> arrayListOf(_e8, _g6, _e4, _c6 , _g4, _c8, _c4, _g8)
                _e7 -> arrayListOf(_g7, _e5, _c7      , _g5, _c5)
                _e8 -> arrayListOf(_g8, _e6, _c8      , _g6, _c6)

                _f1 -> arrayListOf(_f3, _h1, _d1     , _h3, _d3)
                _f2 -> arrayListOf(_f4, _h2, _d2     , _h4, _d4)
                _f3 -> arrayListOf(_f5, _h3, _f1, _d3, _h1, _d5, _d1, _h5)
                _f4 -> arrayListOf(_f6, _h4, _f2, _d4, _h2, _d6, _d2, _h6)
                _f5 -> arrayListOf(_f7, _h5, _f3, _d5, _h3, _d7, _d3, _h7)
                _f6 -> arrayListOf(_f8, _h6, _f4, _d6, _h4, _d8, _d4, _h8)
                _f7 -> arrayListOf(_h7, _f5, _d7     , _h5, _d5)
                _f8 -> arrayListOf(_h8, _f6, _d8     , _h6, _d6)

                _g1 -> arrayListOf(_g3, _i1, _e1     , _i3, _e3)
                _g2 -> arrayListOf(_g4, _i2, _e2     , _i4, _e4)
                _g3 -> arrayListOf(_g5, _i3, _g1, _e3, _i1, _e5, _e1, _i5)
                _g4 -> arrayListOf(_g6, _i4, _g2, _e4, _i2, _e6, _e2, _i6)
                _g5 -> arrayListOf(_g7, _i5, _g3, _e5, _i3, _e7, _e3, _i7)
                _g6 -> arrayListOf(_g8, _i6, _g4, _e6, _i4, _e8, _e4, _i8)
                _g7 -> arrayListOf(_i7, _g5, _e7     , _i5, _e5)
                _g8 -> arrayListOf(_i8, _g6, _e8     , _i6, _e6)

                _h1 -> arrayListOf(_h3, _j1, _f1     , _j3, _f3)
                _h2 -> arrayListOf(_h4, _j2, _f2     , _j4, _f4)
                _h3 -> arrayListOf(_h5, _j3, _h1, _f3, _j1, _f5, _f1, _j5)
                _h4 -> arrayListOf(_h6, _j4, _h2, _f4, _j2, _f6, _f2, _j6)
                _h5 -> arrayListOf(_h7, _j5, _h3, _f5, _j3, _f7, _f3, _j7)
                _h6 -> arrayListOf(_h8, _j6, _h4, _f6, _j4, _f8, _f4, _j8)
                _h7 -> arrayListOf(_j7, _h5, _f7     , _j5, _f5)
                _h8 -> arrayListOf(_j8, _h6, _f8     , _j6, _f6)

                _i1 -> arrayListOf(_i3, _g1     , _g3)
                _i2 -> arrayListOf(_i4, _g2     , _g4)
                _i3 -> arrayListOf(_i5, _i1, _g3, _g5, _g1)
                _i4 -> arrayListOf(_i6, _i2, _g4, _g6, _g2)
                _i5 -> arrayListOf(_i7, _i3, _g5, _g7, _g3)
                _i6 -> arrayListOf(_i8, _i4, _g6, _g8, _g4)
                _i7 -> arrayListOf(_i5, _g7     , _g5)
                _i8 -> arrayListOf(_i6, _g8     , _g6)

                _j1 -> arrayListOf(_j3, _h1      , _h3)
                _j2 -> arrayListOf(_j4, _h2      , _h4)
                _j3 -> arrayListOf(_j5, _j1, _h3 , _h5, _h1)
                _j4 -> arrayListOf(_j6, _j2, _h4 , _h6, _h2)
                _j5 -> arrayListOf(_j7, _j3, _h5 , _h7, _h3)
                _j6 -> arrayListOf(_j8, _j4, _h6 , _h8, _h4)
                _j7 -> arrayListOf(_j5, _h7      , _h5)
                _j8 -> arrayListOf(_j6, _h8      , _h6)

                else -> arrayListOf()
            })
        }
    }

}