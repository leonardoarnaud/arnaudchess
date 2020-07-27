package br.com.arnaudchess.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.arnaudchess.*
import br.com.arnaudchess.model.*
import br.com.arnaudchess.model.Piece.Companion.BLACK
import br.com.arnaudchess.model.Piece.Companion.WHITE

class BoardViewModel : ViewModel() {

    val boardConfiguration = MutableLiveData<HashMap<Int, Piece>>()

    val capturedPieces = ArrayList<Piece>()

    fun startWhite() {
        boardConfiguration.postValue(HashMap<Int, Piece>().apply {
            put(_a1, Rook(WHITE))
            put(_b1, Ninja(WHITE))
            put(_c1, Archer(WHITE))
            put(_d1, Bishop(WHITE))
            put(_e1, Queen(WHITE))
            put(_f1, King(WHITE))
            put(_g1, Bishop(WHITE))
            put(_h1, Archer(WHITE))
            put(_i1, Catapult(WHITE))
            put(_j1, Rook(WHITE))
            put(_a2, Pawn(WHITE))
            put(_b2, Pawn(WHITE))
            put(_c2, Knight(WHITE))
            put(_d2, Pawn(WHITE))
            put(_e2, Pawn(WHITE))
            put(_f2, Pawn(WHITE))
            put(_g2, Pawn(WHITE))
            put(_h2, Knight(WHITE))
            put(_i2, Pawn(WHITE))
            put(_j2, Pawn(WHITE))
            put(_a8, Rook(BLACK))
            put(_b8, Ninja(BLACK))
            put(_c8, Archer(BLACK))
            put(_d8, Bishop(BLACK))
            put(_e8, Queen(BLACK))
            put(_f8, King(BLACK))
            put(_g8, Bishop(BLACK))
            put(_h8, Archer(BLACK))
            put(_i8, Catapult(BLACK))
            put(_j8, Rook(BLACK))
            put(_a7, Pawn(BLACK))
            put(_b7, Pawn(BLACK))
            put(_c7, Knight(BLACK))
            put(_d7, Pawn(BLACK))
            put(_e7, Pawn(BLACK))
            put(_f7, Pawn(BLACK))
            put(_g7, Pawn(BLACK))
            put(_h7, Knight(BLACK))
            put(_i7, Pawn(BLACK))
            put(_j7, Pawn(BLACK))
        })
    }

    fun movePiece(start: Int, end: Int) {
        boardConfiguration.value?.let { bc ->
            val legalPositions = bc[start]?.getLegalEndPositionsFrom(start)
            if (legalPositions?.contains(end) == true
                && bc[end]?.color != bc[start]?.color
            ) {
                bc[end]?.let { capturedPieces.add(it) }
                bc[end] = bc[start]!!
                bc.remove(start)
                boardConfiguration.value = bc
            }
        }

        /*
        val pieceAtEnd = boardConfiguration.value?.singleOrNull { it.position == end }

        if (pieceAtStart == null) throw Exception("Não existe peça na posição inicial")

        val bcBusyPositions = boardConfiguration.value?.mapTo(arrayListOf()){
            it.position
        }!!
        val vmp = pieceAtStart.getValidMovePositions()
        val rangeBusyPositions = ArrayList(vmp.intersect(bcBusyPositions.asIterable())).apply {
            remove(end)
        }
        if (!vmp.contains(end) || rangeBusyPositions.isNotEmpty()){
            throw Exception("Movimento ilegal: Esta peça não pode ir para esta posição")
        }

        if(pieceAtStart.color == pieceAtEnd?.color){
            throw Exception("Movimento ilegal: Você não pode capturar sua própria peça")
        }
        pieceAtEnd?.let {
            boardConfiguration.value?.remove(it)
        }
        pieceAtStart.position = end
        boardConfiguration.value = boardConfiguration.value
        */
    }

}