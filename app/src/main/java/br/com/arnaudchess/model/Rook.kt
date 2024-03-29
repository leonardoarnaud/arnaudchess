package br.com.arnaudchess.model

import android.content.Context
import br.com.arnaudchess.*
import br.com.arnaudchess.ui.PieceImageView

class Rook(color: Boolean) : Piece(color) {

    override fun canJumpWhileMoving(): Boolean {
        return false
    }

    override fun canJumpWhenCapturing(): Boolean {
        return false
    }

    override fun createImageView(context: Context): PieceImageView {
        return object : PieceImageView(context, this@Rook, gold) {
            override var colors = Pair(
                R.drawable.ic_rook_white,
                R.drawable.ic_rook_black
            )

            init {
                setupIcon()
            }
        }
    }

    override var priceToMove: Int = 70

    override fun setDeadlyPiece(b: Boolean) {
        isDeadly = b
    }

    override var isMoved: Boolean = false

    override fun getLegalEndPositionsFrom(position: Int): ArrayList<Int> {
        return when (position) {
            _a1 -> arrayListOf(_a2, _a3, _a4, _a5, _a6, _a7, _a8, _b1, _c1, _d1, _e1, _f1, _g1, _h1,_i1,_j1)
            _a2 -> arrayListOf(_a1, _a3, _a4, _a5, _a6, _a7, _a8, _b2, _c2, _d2, _e2, _f2, _g2, _h2,_i2,_j2)
            _a3 -> arrayListOf(_a1, _a2, _a4, _a5, _a6, _a7, _a8, _b3, _c3, _d3, _e3, _f3, _g3, _h3,_i3,_j3)
            _a4 -> arrayListOf(_a1, _a2, _a3, _a5, _a6, _a7, _a8, _b4, _c4, _d4, _e4, _f4, _g4, _h4,_i4,_j4)
            _a5 -> arrayListOf(_a1, _a2, _a3, _a4, _a6, _a7, _a8, _b5, _c5, _d5, _e5, _f5, _g5, _h5,_i5,_j5)
            _a6 -> arrayListOf(_a1, _a2, _a3, _a4, _a5, _a7, _a8, _b6, _c6, _d6, _e6, _f6, _g6, _h6,_i6,_j6)
            _a7 -> arrayListOf(_a1, _a2, _a3, _a4, _a5, _a6, _a8, _b7, _c7, _d7, _e7, _f7, _g7, _h7,_i7,_j7)
            _a8 -> arrayListOf(_a1, _a2, _a3, _a4, _a5, _a6, _a7, _b8, _c8, _d8, _e8, _f8, _g8, _h8,_i8,_j8)

            _b1 -> arrayListOf(_b2, _b3, _b4, _b5, _b6, _b7, _b8, _a1, _c1, _d1, _e1, _f1, _g1, _h1,_i1,_j1)
            _b2 -> arrayListOf(_b1, _b3, _b4, _b5, _b6, _b7, _b8, _a2, _c2, _d2, _e2, _f2, _g2, _h2,_i2,_j2)
            _b3 -> arrayListOf(_b1, _b2, _b4, _b5, _b6, _b7, _b8, _a3, _c3, _d3, _e3, _f3, _g3, _h3,_i3,_j3)
            _b4 -> arrayListOf(_b1, _b2, _b3, _b5, _b6, _b7, _b8, _a4, _c4, _d4, _e4, _f4, _g4, _h4,_i4,_j4)
            _b5 -> arrayListOf(_b1, _b2, _b3, _b4, _b6, _b7, _b8, _a5, _c5, _d5, _e5, _f5, _g5, _h5,_i5,_j5)
            _b6 -> arrayListOf(_b1, _b2, _b3, _b4, _b5, _b7, _b8, _a6, _c6, _d6, _e6, _f6, _g6, _h6,_i6,_j6)
            _b7 -> arrayListOf(_b1, _b2, _b3, _b4, _b5, _b6, _b8, _a7, _c7, _d7, _e7, _f7, _g7, _h7,_i7,_j7)
            _b8 -> arrayListOf(_b1, _b2, _b3, _b4, _b5, _b6, _b7, _a8, _c8, _d8, _e8, _f8, _g8, _h8,_i8,_j8)

            _c1 -> arrayListOf(_c2, _c3, _c4, _c5, _c6, _c7, _c8, _a1, _b1, _d1, _e1, _f1, _g1, _h1,_i1,_j1)
            _c2 -> arrayListOf(_c1, _c3, _c4, _c5, _c6, _c7, _c8, _a2, _b2, _d2, _e2, _f2, _g2, _h2,_i2,_j2)
            _c3 -> arrayListOf(_c1, _c2, _c4, _c5, _c6, _c7, _c8, _a3, _b3, _d3, _e3, _f3, _g3, _h3,_i3,_j3)
            _c4 -> arrayListOf(_c1, _c2, _c3, _c5, _c6, _c7, _c8, _a4, _b4, _d4, _e4, _f4, _g4, _h4,_i4,_j4)
            _c5 -> arrayListOf(_c1, _c2, _c3, _c4, _c6, _c7, _c8, _a5, _b5, _d5, _e5, _f5, _g5, _h5,_i5,_j5)
            _c6 -> arrayListOf(_c1, _c2, _c3, _c4, _c5, _c7, _c8, _a6, _b6, _d6, _e6, _f6, _g6, _h6,_i6,_j6)
            _c7 -> arrayListOf(_c1, _c2, _c3, _c4, _c5, _c6, _c8, _a7, _b7, _d7, _e7, _f7, _g7, _h7,_i7,_j7)
            _c8 -> arrayListOf(_c1, _c2, _c3, _c4, _c5, _c6, _c7, _a8, _b8, _d8, _e8, _f8, _g8, _h8,_i8,_j8)

            _d1 -> arrayListOf(_d2, _d3, _d4, _d5, _d6, _d7, _d8, _a1, _b1, _c1, _e1, _f1, _g1, _h1,_i1,_j1)
            _d2 -> arrayListOf(_d1, _d3, _d4, _d5, _d6, _d7, _d8, _a2, _b2, _c2, _e2, _f2, _g2, _h2,_i2,_j2)
            _d3 -> arrayListOf(_d1, _d2, _d4, _d5, _d6, _d7, _d8, _a3, _b3, _c3, _e3, _f3, _g3, _h3,_i3,_j3)
            _d4 -> arrayListOf(_d1, _d2, _d3, _d5, _d6, _d7, _d8, _a4, _b4, _c4, _e4, _f4, _g4, _h4,_i4,_j4)
            _d5 -> arrayListOf(_d1, _d2, _d3, _d4, _d6, _d7, _d8, _a5, _b5, _c5, _e5, _f5, _g5, _h5,_i5,_j5)
            _d6 -> arrayListOf(_d1, _d2, _d3, _d4, _d5, _d7, _d8, _a6, _b6, _c6, _e6, _f6, _g6, _h6,_i6,_j6)
            _d7 -> arrayListOf(_d1, _d2, _d3, _d4, _d5, _d6, _d8, _a7, _b7, _c7, _e7, _f7, _g7, _h7,_i7,_j7)
            _d8 -> arrayListOf(_d1, _d2, _d3, _d4, _d5, _d6, _d7, _a8, _b8, _c8, _e8, _f8, _g8, _h8,_i8,_j8)

            _e1 -> arrayListOf(_e2, _e3, _e4, _e5, _e6, _e7, _e8, _a1, _b1, _c1, _d1, _f1, _g1, _h1,_i1,_j1)
            _e2 -> arrayListOf(_e1, _e3, _e4, _e5, _e6, _e7, _e8, _a2, _b2, _c2, _d2, _f2, _g2, _h2,_i2,_j2)
            _e3 -> arrayListOf(_e1, _e2, _e4, _e5, _e6, _e7, _e8, _a3, _b3, _c3, _d3, _f3, _g3, _h3,_i3,_j3)
            _e4 -> arrayListOf(_e1, _e2, _e3, _e5, _e6, _e7, _e8, _a4, _b4, _c4, _d4, _f4, _g4, _h4,_i4,_j4)
            _e5 -> arrayListOf(_e1, _e2, _e3, _e4, _e6, _e7, _e8, _a5, _b5, _c5, _d5, _f5, _g5, _h5,_i5,_j5)
            _e6 -> arrayListOf(_e1, _e2, _e3, _e4, _e5, _e7, _e8, _a6, _b6, _c6, _d6, _f6, _g6, _h6,_i6,_j6)
            _e7 -> arrayListOf(_e1, _e2, _e3, _e4, _e5, _e6, _e8, _a7, _b7, _c7, _d7, _f7, _g7, _h7,_i7,_j7)
            _e8 -> arrayListOf(_e1, _e2, _e3, _e4, _e5, _e6, _e7, _a8, _b8, _c8, _d8, _f8, _g8, _h8,_i8,_j8)

            _f1 -> arrayListOf(_f2, _f3, _f4, _f5, _f6, _f7, _f8, _a1, _b1, _c1, _d1, _e1, _g1, _h1,_i1,_j1)
            _f2 -> arrayListOf(_f1, _f3, _f4, _f5, _f6, _f7, _f8, _a2, _b2, _c2, _d2, _e2, _g2, _h2,_i2,_j2)
            _f3 -> arrayListOf(_f1, _f2, _f4, _f5, _f6, _f7, _f8, _a3, _b3, _c3, _d3, _e3, _g3, _h3,_i3,_j3)
            _f4 -> arrayListOf(_f1, _f2, _f3, _f5, _f6, _f7, _f8, _a4, _b4, _c4, _d4, _e4, _g4, _h4,_i4,_j4)
            _f5 -> arrayListOf(_f1, _f2, _f3, _f4, _f6, _f7, _f8, _a5, _b5, _c5, _d5, _e5, _g5, _h5,_i5,_j5)
            _f6 -> arrayListOf(_f1, _f2, _f3, _f4, _f5, _f7, _f8, _a6, _b6, _c6, _d6, _e6, _g6, _h6,_i6,_j6)
            _f7 -> arrayListOf(_f1, _f2, _f3, _f4, _f5, _f6, _f8, _a7, _b7, _c7, _d7, _e7, _g7, _h7,_i7,_j7)
            _f8 -> arrayListOf(_f1, _f2, _f3, _f4, _f5, _f6, _f7, _a8, _b8, _c8, _d8, _e8, _g8, _h8,_i8,_j8)

            _g1 -> arrayListOf(_g2, _g3, _g4, _g5, _g6, _g7, _g8, _a1, _b1, _c1, _d1, _e1, _f1, _h1,_i1,_j1)
            _g2 -> arrayListOf(_g1, _g3, _g4, _g5, _g6, _g7, _g8, _a2, _b2, _c2, _d2, _e2, _f2, _h2,_i2,_j2)
            _g3 -> arrayListOf(_g1, _g2, _g4, _g5, _g6, _g7, _g8, _a3, _b3, _c3, _d3, _e3, _f3, _h3,_i3,_j3)
            _g4 -> arrayListOf(_g1, _g2, _g3, _g5, _g6, _g7, _g8, _a4, _b4, _c4, _d4, _e4, _f4, _h4,_i4,_j4)
            _g5 -> arrayListOf(_g1, _g2, _g3, _g4, _g6, _g7, _g8, _a5, _b5, _c5, _d5, _e5, _f5, _h5,_i5,_j5)
            _g6 -> arrayListOf(_g1, _g2, _g3, _g4, _g5, _g7, _g8, _a6, _b6, _c6, _d6, _e6, _f6, _h6,_i6,_j6)
            _g7 -> arrayListOf(_g1, _g2, _g3, _g4, _g5, _g6, _g8, _a7, _b7, _c7, _d7, _e7, _f7, _h7,_i7,_j7)
            _g8 -> arrayListOf(_g1, _g2, _g3, _g4, _g5, _g6, _g7, _a8, _b8, _c8, _d8, _e8, _f8, _h8,_i8,_j8)

            _h1 -> arrayListOf(_h2, _h3, _h4, _h5, _h6, _h7, _h8, _a1, _b1, _c1, _d1, _e1, _f1, _g1,_i1,_j1)
            _h2 -> arrayListOf(_h1, _h3, _h4, _h5, _h6, _h7, _h8, _a2, _b2, _c2, _d2, _e2, _f2, _g2,_i2,_j2)
            _h3 -> arrayListOf(_h1, _h2, _h4, _h5, _h6, _h7, _h8, _a3, _b3, _c3, _d3, _e3, _f3, _g3,_i3,_j3)
            _h4 -> arrayListOf(_h1, _h2, _h3, _h5, _h6, _h7, _h8, _a4, _b4, _c4, _d4, _e4, _f4, _g4,_i4,_j4)
            _h5 -> arrayListOf(_h1, _h2, _h3, _h4, _h6, _h7, _h8, _a5, _b5, _c5, _d5, _e5, _f5, _g5,_i5,_j5)
            _h6 -> arrayListOf(_h1, _h2, _h3, _h4, _h5, _h7, _h8, _a6, _b6, _c6, _d6, _e6, _f6, _g6,_i6,_j6)
            _h7 -> arrayListOf(_h1, _h2, _h3, _h4, _h5, _h6, _h8, _a7, _b7, _c7, _d7, _e7, _f7, _g7,_i7,_j7)
            _h8 -> arrayListOf(_h1, _h2, _h3, _h4, _h5, _h6, _h7, _a8, _b8, _c8, _d8, _e8, _f8, _g8,_i8,_j8)

            _i1 -> arrayListOf(_i2, _i3, _i4, _i5, _i6, _i7, _i8, _a1, _b1, _c1, _d1, _e1, _f1, _g1,_h1,_j1)
            _i2 -> arrayListOf(_i1, _i3, _i4, _i5, _i6, _i7, _i8, _a2, _b2, _c2, _d2, _e2, _f2, _g2,_h2,_j2)
            _i3 -> arrayListOf(_i1, _i2, _i4, _i5, _i6, _i7, _i8, _a3, _b3, _c3, _d3, _e3, _f3, _g3,_h3,_j3)
            _i4 -> arrayListOf(_i1, _i2, _i3, _i5, _i6, _i7, _i8, _a4, _b4, _c4, _d4, _e4, _f4, _g4,_h4,_j4)
            _i5 -> arrayListOf(_i1, _i2, _i3, _i4, _i6, _i7, _i8, _a5, _b5, _c5, _d5, _e5, _f5, _g5,_h5,_j5)
            _i6 -> arrayListOf(_i1, _i2, _i3, _i4, _i5, _i7, _i8, _a6, _b6, _c6, _d6, _e6, _f6, _g6,_h6,_j6)
            _i7 -> arrayListOf(_i1, _i2, _i3, _i4, _i5, _i6, _i8, _a7, _b7, _c7, _d7, _e7, _f7, _g7,_h7,_j7)
            _i8 -> arrayListOf(_i1, _i2, _i3, _i4, _i5, _i6, _i7, _a8, _b8, _c8, _d8, _e8, _f8, _g8,_h8,_j8)

            _j1 -> arrayListOf(_j2, _j3, _j4, _j5, _j6, _j7, _j8, _a1, _b1, _c1, _d1, _e1, _f1, _g1,_h1,_i1)
            _j2 -> arrayListOf(_j1, _j3, _j4, _j5, _j6, _j7, _j8, _a2, _b2, _c2, _d2, _e2, _f2, _g2,_h2,_i2)
            _j3 -> arrayListOf(_j1, _j2, _j4, _j5, _j6, _j7, _j8, _a3, _b3, _c3, _d3, _e3, _f3, _g3,_h3,_i3)
            _j4 -> arrayListOf(_j1, _j2, _j3, _j5, _j6, _j7, _j8, _a4, _b4, _c4, _d4, _e4, _f4, _g4,_h4,_i4)
            _j5 -> arrayListOf(_j1, _j2, _j3, _j4, _j6, _j7, _j8, _a5, _b5, _c5, _d5, _e5, _f5, _g5,_h5,_i5)
            _j6 -> arrayListOf(_j1, _j2, _j3, _j4, _j5, _j7, _j8, _a6, _b6, _c6, _d6, _e6, _f6, _g6,_h6,_i6)
            _j7 -> arrayListOf(_j1, _j2, _j3, _j4, _j5, _j6, _j8, _a7, _b7, _c7, _d7, _e7, _f7, _g7,_h7,_i7)
            _j8 -> arrayListOf(_j1, _j2, _j3, _j4, _j5, _j6, _j7, _a8, _b8, _c8, _d8, _e8, _f8, _g8,_h8,_i8)

            else -> arrayListOf()
        }
    }

}