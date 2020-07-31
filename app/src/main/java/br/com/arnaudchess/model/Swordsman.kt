package br.com.arnaudchess.model

import android.content.Context
import br.com.arnaudchess.*
import br.com.arnaudchess.ui.PieceImageView

class Swordsman(
    color: Boolean,
    val direction: Boolean
) : Piece(color) {

    override fun createImageView(context: Context): PieceImageView {
        return object : PieceImageView(context, this@Swordsman) {
            override var colors = Pair(
                R.drawable.ic_swordsman_white,
                R.drawable.ic_swordsman_black
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

    override fun getLegalEndPositionsFrom(position: Int): ArrayList<Int> {
        return when(direction){
            UP -> when (position){
                _a2 -> arrayListOf(_a3,_a4,_b3)
                _b2 -> arrayListOf(_b3,_b4,_a3,_c3)
                _c2 -> arrayListOf(_c3,_c4,_b3,_d3)
                _d2 -> arrayListOf(_d3,_d4,_c3,_e3)
                _e2 -> arrayListOf(_e3,_e4,_d3,_f3)
                _f2 -> arrayListOf(_f3,_f4,_e3,_g3)
                _g2 -> arrayListOf(_g3,_g4,_f3,_h3)
                _h2 -> arrayListOf(_h3,_h4,_g3,_i3)
                _i2 -> arrayListOf(_i3,_i4,_h3,_j3)
                _j2 -> arrayListOf(_j3,_j4,_i3)

                _a3 -> arrayListOf(_a4,_b4)
                _b3 -> arrayListOf(_b4,_a4,_c4)
                _c3 -> arrayListOf(_c4,_b4,_d4)
                _d3 -> arrayListOf(_d4,_c4,_e4)
                _e3 -> arrayListOf(_e4,_d4,_f4)
                _f3 -> arrayListOf(_f4,_e4,_g4)
                _g3 -> arrayListOf(_g4,_f4,_h4)
                _h3 -> arrayListOf(_h4,_g4,_i4)
                _i3 -> arrayListOf(_i4,_h4,_j4)
                _j3 -> arrayListOf(_j4,_i4)

                _a4 -> arrayListOf(_a5,_b5)
                _b4 -> arrayListOf(_b5,_a5,_c5)
                _c4 -> arrayListOf(_c5,_b5,_d5)
                _d4 -> arrayListOf(_d5,_c5,_e5)
                _e4 -> arrayListOf(_e5,_d5,_f5)
                _f4 -> arrayListOf(_f5,_e5,_g5)
                _g4 -> arrayListOf(_g5,_f5,_h5)
                _h4 -> arrayListOf(_h5,_g5,_i5)
                _i4 -> arrayListOf(_i5,_h5,_j5)
                _j4 -> arrayListOf(_j5,_i5)

                _a5 -> arrayListOf(_a6,_b6)
                _b5 -> arrayListOf(_b6,_a6,_c6)
                _c5 -> arrayListOf(_c6,_b6,_d6)
                _d5 -> arrayListOf(_d6,_c6,_e6)
                _e5 -> arrayListOf(_e6,_d6,_f6)
                _f5 -> arrayListOf(_f6,_e6,_g6)
                _g5 -> arrayListOf(_g6,_f6,_h6)
                _h5 -> arrayListOf(_h6,_g6,_i6)
                _i5 -> arrayListOf(_i6,_h6,_j6)
                _j5 -> arrayListOf(_j6,_i6)

                _a6 -> arrayListOf(_a7,_b7)
                _b6 -> arrayListOf(_b7,_a7,_c7)
                _c6 -> arrayListOf(_c7,_b7,_d7)
                _d6 -> arrayListOf(_d7,_c7,_e7)
                _e6 -> arrayListOf(_e7,_d7,_f7)
                _f6 -> arrayListOf(_f7,_e7,_g7)
                _g6 -> arrayListOf(_g7,_f7,_h7)
                _h6 -> arrayListOf(_h7,_g7,_i7)
                _i6 -> arrayListOf(_i7,_h7,_j7)
                _j6 -> arrayListOf(_j7,_i7)

                _a7 -> arrayListOf(_a8,_b8)
                _b7 -> arrayListOf(_b8,_a8,_c8)
                _c7 -> arrayListOf(_c8,_b8,_d8)
                _d7 -> arrayListOf(_d8,_c8,_e8)
                _e7 -> arrayListOf(_e8,_d8,_f8)
                _f7 -> arrayListOf(_f8,_e8,_g8)
                _g7 -> arrayListOf(_g8,_f8,_h8)
                _h7 -> arrayListOf(_h8,_g8,_i8)
                _i7 -> arrayListOf(_i8,_h8,_j8)
                _j7 -> arrayListOf(_j8,_i8)

                else -> arrayListOf()
            }
            DOWN -> when (position){
                _a2 -> arrayListOf(_a1,_a3,_b2)
                _b2 -> arrayListOf(_b1,_b3,_a2,_c2)
                _c2 -> arrayListOf(_c1,_c3,_b2,_d2)
                _d2 -> arrayListOf(_d1,_d3,_c2,_e2)
                _e2 -> arrayListOf(_e1,_e3,_d2,_f2)
                _f2 -> arrayListOf(_f1,_f3,_e2,_g2)
                _g2 -> arrayListOf(_g1,_g3,_f2,_h2)
                _h2 -> arrayListOf(_h1,_h3,_g2,_i2)
                _i2 -> arrayListOf(_i1,_i3,_h2,_j2)
                _j2 -> arrayListOf(_j1,_j3,_i2)

                _a3 -> arrayListOf(_a2,_b2)
                _b3 -> arrayListOf(_b2,_a2,_c2)
                _c3 -> arrayListOf(_c2,_b2,_d2)
                _d3 -> arrayListOf(_d2,_c2,_e2)
                _e3 -> arrayListOf(_e2,_d2,_f2)
                _f3 -> arrayListOf(_f2,_e2,_g2)
                _g3 -> arrayListOf(_g2,_f2,_h2)
                _h3 -> arrayListOf(_h2,_g2,_i2)
                _i3 -> arrayListOf(_i2,_h2,_j2)
                _j3 -> arrayListOf(_j2,_i2)

                _a4 -> arrayListOf(_a3,_b3)
                _b4 -> arrayListOf(_b3,_a3,_c3)
                _c4 -> arrayListOf(_c3,_b3,_d3)
                _d4 -> arrayListOf(_d3,_c3,_e3)
                _e4 -> arrayListOf(_e3,_d3,_f3)
                _f4 -> arrayListOf(_f3,_e3,_g3)
                _g4 -> arrayListOf(_g3,_f3,_h3)
                _h4 -> arrayListOf(_h3,_g3,_i3)
                _i4 -> arrayListOf(_i3,_h3,_j3)
                _j4 -> arrayListOf(_j3,_i3)

                _a5 -> arrayListOf(_a4,_b4)
                _b5 -> arrayListOf(_b4,_a4,_c4)
                _c5 -> arrayListOf(_c4,_b4,_d4)
                _d5 -> arrayListOf(_d4,_c4,_e4)
                _e5 -> arrayListOf(_e4,_d4,_f4)
                _f5 -> arrayListOf(_f4,_e4,_g4)
                _g5 -> arrayListOf(_g4,_f4,_h4)
                _h5 -> arrayListOf(_h4,_g4,_i4)
                _i5 -> arrayListOf(_i4,_h4,_j4)
                _j5 -> arrayListOf(_j4,_i4)

                _a6 -> arrayListOf(_a5,_b5)
                _b6 -> arrayListOf(_b5,_a5,_c5)
                _c6 -> arrayListOf(_c5,_b5,_d5)
                _d6 -> arrayListOf(_d5,_c5,_e5)
                _e6 -> arrayListOf(_e5,_d5,_f5)
                _f6 -> arrayListOf(_f5,_e5,_g5)
                _g6 -> arrayListOf(_g5,_f5,_h5)
                _h6 -> arrayListOf(_h5,_g5,_i5)
                _i6 -> arrayListOf(_i5,_h5,_j5)
                _j6 -> arrayListOf(_j5,_i5)

                _a7 -> arrayListOf(_a6,_b6)
                _b7 -> arrayListOf(_b6,_a6,_c6)
                _c7 -> arrayListOf(_c6,_b6,_d6)
                _d7 -> arrayListOf(_d6,_c6,_e6)
                _e7 -> arrayListOf(_e6,_d6,_f6)
                _f7 -> arrayListOf(_f6,_e6,_g6)
                _g7 -> arrayListOf(_g6,_f6,_h6)
                _h7 -> arrayListOf(_h6,_g6,_i6)
                _i7 -> arrayListOf(_i6,_h6,_j6)
                _j7 -> arrayListOf(_j6,_i6)
                else -> arrayListOf()
            }
            else -> arrayListOf()
        }

    }
}