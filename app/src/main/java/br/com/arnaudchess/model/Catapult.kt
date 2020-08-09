package br.com.arnaudchess.model

import android.content.Context
import br.com.arnaudchess.*
import br.com.arnaudchess.ui.PieceImageView

class Catapult(color: Boolean) : Piece(color) {

    val knightSpirit = Knight(color)

    override fun createImageView(context: Context): PieceImageView {
        return object : PieceImageView(context, this@Catapult, gold) {
            override var colors = Pair(
                R.drawable.ic_catapult_white,
                R.drawable.ic_catapult_black
            )

            init {
                setupIcon()
            }
        }
    }

    override var priceToMove: Int = 60

    override fun canJumpWhileMoving(): Boolean {
        return false
    }

    override fun canJumpWhenCapturing(): Boolean {
        return true
    }

    override fun setDeadlyPiece(b: Boolean) {
        isDeadly = b
    }

    override var isMoved: Boolean = false

    override fun getLegalEndPositionsFrom(position: Int): ArrayList<Int> {
        return when(position){
            _a1 -> arrayListOf(_a2,_b2,_a3,_b3,_c3)
            _a2 -> arrayListOf(_a3,_b3,_a4,_b4,_c4,_a1,_b1)
            _a3 -> arrayListOf(_a4,_b4,_a5,_b5,_c5,_a2,_b2,_a1,_b1,_c1)
            _a4 -> arrayListOf(_a5,_b5,_a6,_b6,_c6,_a3,_b3,_a2,_b2,_c2)
            _a5 -> arrayListOf(_a6,_b6,_a7,_b7,_c7,_a4,_b4,_a3,_b3,_c3)
            _a6 -> arrayListOf(_a7,_b7,_a8,_b8,_c8,_a5,_b5,_a4,_b4,_c4)
            _a7 -> arrayListOf(_a8,_b8,_a6,_b6,_a5,_b5,_c5)
            _a8 -> arrayListOf(_a7,_b7,_a6,_b6,_c6)

            _b1 -> arrayListOf(_a2,_b2,_c2,_a3,_b3,_c3,_d3)
            _b2 -> arrayListOf(_a3,_b3,_c3,_a4,_b4,_c4,_d4,_a1,_b1,_c1)
            _b3 -> arrayListOf(_a4,_b4,_c4,_a5,_b5,_c5,_d5,_a2,_b2,_c2,_a1,_b1,_c1,_d1)
            _b4 -> arrayListOf(_a5,_b5,_c5,_a6,_b6,_c6,_d6,_a3,_b3,_c3,_a2,_b2,_c2,_d2)
            _b5 -> arrayListOf(_a6,_b6,_c6,_a7,_b7,_c7,_d7,_a4,_b4,_c4,_a3,_b3,_c3,_d3)
            _b6 -> arrayListOf(_a7,_b7,_c7,_a8,_b8,_c8,_d8,_a5,_b5,_c5,_a4,_b4,_c4,_d4)
            _b7 -> arrayListOf(_a8,_b8,_c8,_a6,_b6,_c6,_a5,_b5,_c5,_d5)
            _b8 -> arrayListOf(_a7,_b7,_c7,_a6,_b6,_c6,_d6)

            _c1 -> arrayListOf(_b2,_c2,_d2,_a3,_b3,_c3,_d3,_e3)
            _c2 -> arrayListOf(_b3,_c3,_d3,_a4,_b4,_c4,_d4,_e4,_b1,_c1,_d1)
            _c3 -> arrayListOf(_b4,_c4,_d4,_a5,_b5,_c5,_d5,_e5,_b2,_c2,_d2,_a1,_b1,_c1,_d1,_e1)
            _c4 -> arrayListOf(_b5,_c5,_d5,_a6,_b6,_c6,_d6,_e6,_b3,_c3,_d3,_a2,_b2,_c2,_d2,_e2)
            _c5 -> arrayListOf(_b6,_c6,_d6,_a7,_b7,_c7,_d7,_e7,_b4,_c4,_d4,_a3,_b3,_c3,_d3,_e3)
            _c6 -> arrayListOf(_b7,_c7,_d7,_a8,_b8,_c8,_d8,_e8,_b5,_c5,_d5,_a4,_b4,_c4,_d4,_e4)
            _c7 -> arrayListOf(_b8,_c8,_d8,_b6,_c6,_d6,_a5,_b5,_c5,_d5,_e5)
            _c8 -> arrayListOf(_b7,_c7,_d7,_a6,_b6,_c6,_d6,_e6)

            _d1 -> arrayListOf(_c2,_d2,_e2,_b3,_c3,_d3,_e3,_f3)
            _d2 -> arrayListOf(_c3,_d3,_e3,_b4,_c4,_d4,_e4,_f4,_c1,_d1,_e1)
            _d3 -> arrayListOf(_c4,_d4,_e4,_b5,_c5,_d5,_e5,_f5,_c2,_d2,_e2,_b1,_c1,_d1,_e1,_f1)
            _d4 -> arrayListOf(_c5,_d5,_e5,_b6,_c6,_d6,_e6,_f6,_c3,_d3,_e3,_b2,_c2,_d2,_e2,_f2)
            _d5 -> arrayListOf(_c6,_d6,_e6,_b7,_c7,_d7,_e7,_f7,_c4,_d4,_e4,_b3,_c3,_d3,_e3,_f3)
            _d6 -> arrayListOf(_c7,_d7,_e7,_b8,_c8,_d8,_e8,_f8,_c5,_d5,_e5,_b4,_c4,_d4,_e4,_f4)
            _d7 -> arrayListOf(_c8,_d8,_e8,_c6,_d6,_e6,_b5,_c5,_d5,_e5,_f5)
            _d8 -> arrayListOf(_c7,_d7,_e7,_b6,_c6,_d6,_e6,_f6)

            _e1 -> arrayListOf(_d2,_e2,_f2,_c3,_d3,_e3,_f3,_g3)
            _e2 -> arrayListOf(_d3,_e3,_f3,_c4,_d4,_e4,_f4,_g4,_d1,_e1,_f1)
            _e3 -> arrayListOf(_d4,_e4,_f4,_c5,_d5,_e5,_f5,_g5,_d2,_e2,_f2,_c1,_d1,_e1,_f1,_g1)
            _e4 -> arrayListOf(_d5,_e5,_f5,_c6,_d6,_e6,_f6,_g6,_d3,_e3,_f3,_c2,_d2,_e2,_f2,_g2)
            _e5 -> arrayListOf(_d6,_e6,_f6,_c7,_d7,_e7,_f7,_g7,_d4,_e4,_f4,_c3,_d3,_e3,_f3,_g3)
            _e6 -> arrayListOf(_d7,_e7,_f7,_c8,_d8,_e8,_f8,_g8,_d5,_e5,_f5,_c4,_d4,_e4,_f4,_g4)
            _e7 -> arrayListOf(_d8,_e8,_f8,_d6,_e6,_f6,_c5,_d5,_e5,_f5,_g5)
            _e8 -> arrayListOf(_d7,_e7,_f7,_c6,_d6,_e6,_f6,_g6)

            _f1 -> arrayListOf(_e2,_f2,_g2,_d3,_e3,_f3,_g3,_h3)
            _f2 -> arrayListOf(_e3,_f3,_g3,_d4,_e4,_f4,_g4,_h4,_e1,_f1,_g1)
            _f3 -> arrayListOf(_e4,_f4,_g4,_d5,_e5,_f5,_g5,_h5,_e2,_f2,_g2,_d1,_e1,_f1,_g1,_h1)
            _f4 -> arrayListOf(_e5,_f5,_g5,_d6,_e6,_f6,_g6,_h6,_e3,_f3,_g3,_d2,_e2,_f2,_g2,_h2)
            _f5 -> arrayListOf(_e6,_f6,_g6,_d7,_e7,_f7,_g7,_h7,_e4,_f4,_g4,_d3,_e3,_f3,_g3,_h3)
            _f6 -> arrayListOf(_e7,_f7,_g7,_d8,_e8,_f8,_g8,_h8,_e5,_f5,_g5,_d4,_e4,_f4,_g4,_h4)
            _f7 -> arrayListOf(_e8,_f8,_g8,_e6,_f6,_g6,_d5,_e5,_f5,_g5,_h5)
            _f8 -> arrayListOf(_e7,_f7,_g7,_d6,_e6,_f6,_g6,_h6)

            _g1 -> arrayListOf(_f2,_g2,_h2,_e3,_f3,_g3,_h3,_i3)
            _g2 -> arrayListOf(_f3,_g3,_h3,_e4,_f4,_g4,_h4,_i4,_f1,_g1,_h1)
            _g3 -> arrayListOf(_f4,_g4,_h4,_e5,_f5,_g5,_h5,_i5,_f2,_g2,_h2,_e1,_f1,_g1,_h1,_i1)
            _g4 -> arrayListOf(_f5,_g5,_h5,_e6,_f6,_g6,_h6,_i6,_f3,_g3,_h3,_e2,_f2,_g2,_h2,_i2)
            _g5 -> arrayListOf(_f6,_g6,_h6,_e7,_f7,_g7,_h7,_i7,_f4,_g4,_h4,_e3,_f3,_g3,_h3,_i3)
            _g6 -> arrayListOf(_f7,_g7,_h7,_e8,_f8,_g8,_h8,_i8,_f5,_g5,_h5,_e4,_f4,_g4,_h4,_i4)
            _g7 -> arrayListOf(_f8,_g8,_h8,_f6,_g6,_h6,_e5,_f5,_g5,_h5,_i5)
            _g8 -> arrayListOf(_f7,_g7,_h7,_e6,_f6,_g6,_h6,_i6)

            _h1 -> arrayListOf(_g2,_h2,_i2,_f3,_g3,_h3,_i3,_j3)
            _h2 -> arrayListOf(_g3,_h3,_i3,_f4,_g4,_h4,_i4,_j4,_g1,_h1,_i1)
            _h3 -> arrayListOf(_g4,_h4,_i4,_f5,_g5,_h5,_i5,_j5,_g2,_h2,_i2,_f1,_g1,_h1,_i1,_j1)
            _h4 -> arrayListOf(_g5,_h5,_i5,_f6,_g6,_h6,_i6,_j6,_g3,_h3,_i3,_f2,_g2,_h2,_i2,_j2)
            _h5 -> arrayListOf(_g6,_h6,_i6,_f7,_g7,_h7,_i7,_j7,_g4,_h4,_i4,_f3,_g3,_h3,_i3,_j3)
            _h6 -> arrayListOf(_g7,_h7,_i7,_f8,_g8,_h8,_i8,_j8,_g5,_h5,_i5,_f4,_g4,_h4,_i4,_j4)
            _h7 -> arrayListOf(_g8,_h8,_i8,_g6,_h6,_i6,_f5,_g5,_h5,_i5,_j5)
            _h8 -> arrayListOf(_g7,_h7,_i7,_f6,_g6,_h6,_i6,_j6)

            _i1 -> arrayListOf(_h2,_i2,_j2,_g3,_h3,_i3,_j3)
            _i2 -> arrayListOf(_h3,_i3,_j3,_g4,_h4,_i4,_j4,_h1,_i1,_j1)
            _i3 -> arrayListOf(_h4,_i4,_j4,_g5,_h5,_i5,_j5,_h2,_i2,_j2,_g1,_h1,_i1,_j1)
            _i4 -> arrayListOf(_h5,_i5,_j5,_g6,_h6,_i6,_j6,_h3,_i3,_j3,_g2,_h2,_i2,_j2)
            _i5 -> arrayListOf(_h6,_i6,_j6,_g7,_h7,_i7,_j7,_h4,_i4,_j4,_g3,_h3,_i3,_j3)
            _i6 -> arrayListOf(_h7,_i7,_j7,_g8,_h8,_i8,_j8,_h5,_i5,_j5,_g4,_h4,_i4,_j4)
            _i7 -> arrayListOf(_h8,_i8,_j8,_h6,_i6,_j6,_g5,_h5,_i5,_j5)
            _i8 -> arrayListOf(_h7,_i7,_j7,_g6,_h6,_i6,_j6)

            _j1 -> arrayListOf(_i2,_j2,_h3,_i3,_j3)
            _j2 -> arrayListOf(_i3,_j3,_h4,_i4,_j4,_i1,_j1)
            _j3 -> arrayListOf(_i4,_j4,_h5,_i5,_j5,_i2,_j2,_h1,_i1,_j1)
            _j4 -> arrayListOf(_i5,_j5,_h6,_i6,_j6,_i3,_j3,_h2,_i2,_j2)
            _j5 -> arrayListOf(_i6,_j6,_h7,_i7,_j7,_i4,_j4,_h3,_i3,_j3)
            _j6 -> arrayListOf(_i7,_j7,_h8,_i8,_j8,_i5,_j5,_h4,_i4,_j4)
            _j7 -> arrayListOf(_i8,_j8,_i6,_j6,_h5,_i5,_j5)
            _j8 -> arrayListOf(_i7,_j7,_h6,_i6,_j6)

            else -> arrayListOf()
        }
    }
}