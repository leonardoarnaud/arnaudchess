package br.com.arnaudchess.ui

import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.arnaudchess.*
import br.com.arnaudchess.model.*
import br.com.arnaudchess.model.Piece.Companion.BLACK
import br.com.arnaudchess.model.Piece.Companion.DOWN
import br.com.arnaudchess.model.Piece.Companion.UP
import br.com.arnaudchess.model.Piece.Companion.WHITE

class BoardViewModel : ViewModel() {

    private var simulatedEnPassantCapturePosition: Int = 0
    private var simulatedEnPassantMovePosition: Int = 0
    private var enPassantCapturePosition: Int = 0
    private var enPassantMovePosition = 0
    var turn = WHITE
    val boardConfiguration = MutableLiveData<HashMap<Int, Piece>>()
    val message = MutableLiveData<Int>()
    val event = MutableLiveData<Int>()
    val treasurePositions = ArrayList<Int>()

    var whiteGold = 0
    var blackGold = 0
    val myGold = MutableLiveData<Int>()

    init {
        whiteGold = 50000
        blackGold = 50000
    }

    private val boardLines = ArrayList<ArrayList<Int>>().apply {
        add(arrayListOf(_a1, _a2, _a3, _a4, _a5, _a6, _a7, _a8))
        add(arrayListOf(_b1, _b2, _b3, _b4, _b5, _b6, _b7, _b8))
        add(arrayListOf(_c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8))
        add(arrayListOf(_d1, _d2, _d3, _d4, _d5, _d6, _d7, _d8))
        add(arrayListOf(_e1, _e2, _e3, _e4, _e5, _e6, _e7, _e8))
        add(arrayListOf(_f1, _f2, _f3, _f4, _f5, _f6, _f7, _f8))
        add(arrayListOf(_g1, _g2, _g3, _g4, _g5, _g6, _g7, _g8))
        add(arrayListOf(_h1, _h2, _h3, _h4, _h5, _h6, _h7, _h8))
        add(arrayListOf(_i1, _i2, _i3, _i4, _i5, _i6, _i7, _i8))
        add(arrayListOf(_j1, _j2, _j3, _j4, _j5, _j6, _j7, _j8))
        add(arrayListOf(_a1, _b1, _c1, _d1, _e1, _f1, _g1, _h1, _i1, _j1))
        add(arrayListOf(_a2, _b2, _c2, _d2, _e2, _f2, _g2, _h2, _i2, _j2))
        add(arrayListOf(_a3, _b3, _c3, _d3, _e3, _f3, _g3, _h3, _i3, _j3))
        add(arrayListOf(_a4, _b4, _c4, _d4, _e4, _f4, _g4, _h4, _i4, _j4))
        add(arrayListOf(_a5, _b5, _c5, _d5, _e5, _f5, _g5, _h5, _i5, _j5))
        add(arrayListOf(_a6, _b6, _c6, _d6, _e6, _f6, _g6, _h6, _i6, _j6))
        add(arrayListOf(_a7, _b7, _c7, _d7, _e7, _f7, _g7, _h7, _i7, _j7))
        add(arrayListOf(_a8, _b8, _c8, _d8, _e8, _f8, _g8, _h8, _i8, _j8))
        add(arrayListOf(_a2, _b1))
        add(arrayListOf(_a3, _b2, _c1))
        add(arrayListOf(_a4, _b3, _c2, _d1))
        add(arrayListOf(_a5, _b4, _c3, _d2, _e1))
        add(arrayListOf(_a6, _b5, _c4, _d3, _e2, _f1))
        add(arrayListOf(_a7, _b6, _c5, _d4, _e3, _f2, _g1))
        add(arrayListOf(_a8, _b7, _c6, _d5, _e4, _f3, _g2, _h1))
        add(arrayListOf(_b8, _c7, _d6, _e5, _f4, _g3, _h2, _i1))
        add(arrayListOf(_c8, _d7, _e6, _f5, _g4, _h3, _i2, _j1))
        add(arrayListOf(_a7, _b8))
        add(arrayListOf(_a6, _b7, _c8))
        add(arrayListOf(_a5, _b6, _c7, _d8))
        add(arrayListOf(_a4, _b5, _c6, _d7, _e8))
        add(arrayListOf(_a3, _b4, _c5, _d6, _e7, _f8))
        add(arrayListOf(_a2, _b3, _c4, _d5, _e6, _f7, _g8))
        add(arrayListOf(_a1, _b2, _c3, _d4, _e5, _f6, _g7, _h8))
        add(arrayListOf(_b1, _c2, _d3, _e4, _f5, _g6, _h7, _i8))
        add(arrayListOf(_c1, _d2, _e3, _f4, _g5, _h6, _i7, _j8))
        add(arrayListOf(_d1, _e2, _f3, _g4, _h5, _i6, _j7))
        add(arrayListOf(_e1, _f2, _g3, _h4, _i5, _j6))
        add(arrayListOf(_f1, _g2, _h3, _i4, _j5))
        add(arrayListOf(_g1, _h2, _i3, _j4))
        add(arrayListOf(_h1, _i2, _j3))
        add(arrayListOf(_i1, _j2))

        add(arrayListOf(_j2, _i3, _h4, _g5, _f6, _e7, _d8))
        add(arrayListOf(_j3, _i4, _h5, _g6, _f7, _e8))
        add(arrayListOf(_j4, _i5, _h6, _g7, _f8))
        add(arrayListOf(_j5, _i6, _h7, _g8))
        add(arrayListOf(_j6, _i7, _h8))
        add(arrayListOf(_j7, _i8))
    }

    val topBoardPositions = arrayListOf(_a8, _b8, _c8, _d8, _e8, _f8, _g8, _h8, _i8, _j8)
    val bottomBoardPositions = arrayListOf(_a1, _b1, _c1, _d1, _e1, _f1, _g1, _h1, _i1, _j1)

    val topSideBoardPositions = arrayListOf(
        _a8, _b8, _c8, _d8, _e8, _f8, _g8, _h8, _i8, _j8,
        _a7, _b7, _c7, _d7, _e7, _f7, _g7, _h7, _i7, _j7,
        _a6, _b6, _c6, _d6, _e6, _f6, _g6, _h6, _i6, _j6,
        _a5, _b5, _c5, _d5, _e5, _f5, _g5, _h5, _i5, _j5
    )

    val bottomSideBoardPositions = arrayListOf(
        _a1, _b1, _c1, _d1, _e1, _f1, _g1, _h1, _i1, _j1,
        _a2, _b2, _c2, _d2, _e2, _f2, _g2, _h2, _i2, _j2,
        _a3, _b3, _c3, _d3, _e3, _f3, _g3, _h3, _i3, _j3,
        _a4, _b4, _c4, _d4, _e4, _f4, _g4, _h4, _i4, _j4
    )

    val allBoardPositions = arrayListOf(
        _a1, _b1, _c1, _d1, _e1, _f1, _g1, _h1, _i1, _j1,
        _a2, _b2, _c2, _d2, _e2, _f2, _g2, _h2, _i2, _j2,
        _a3, _b3, _c3, _d3, _e3, _f3, _g3, _h3, _i3, _j3,
        _a4, _b4, _c4, _d4, _e4, _f4, _g4, _h4, _i4, _j4,
        _a5, _b5, _c5, _d5, _e5, _f5, _g5, _h5, _i5, _j5,
        _a6, _b6, _c6, _d6, _e6, _f6, _g6, _h6, _i6, _j6,
        _a7, _b7, _c7, _d7, _e7, _f7, _g7, _h7, _i7, _j7,
        _a8, _b8, _c8, _d8, _e8, _f8, _g8, _h8, _i8, _j8
    )

    val capturedPieces = ArrayList<Piece>()
    var whiteDirection = false
    var blackDirection = false

    var defaultBet = 3000

    fun start(color: Boolean) {
        var gold = if (color) whiteGold else blackGold
        if (gold < defaultBet) {
            message.postValue(R.string.insuficient_gold)
            return
        }
        gold -= defaultBet
        if (color) whiteGold = gold else blackGold = gold
        myGold.postValue(gold)

        reset()
        whiteDirection = if (color) UP else DOWN
        blackDirection = if (color) DOWN else UP
        val colorDirection = if (color) whiteDirection else blackDirection

        boardConfiguration.postValue(HashMap<Int, Piece>().apply {
            put(_a1, Rook(color))
            put(_b1, Catapult(color))
            put(_c1, Archer(color))
            put(_d1, Bishop(color))
            put(_e1, Queen(color))
            put(_f1, King(color))
            put(_g1, Bishop(color))
            put(_h1, Archer(color))
            put(_i1, Catapult(color))
            put(_j1, Rook(color))
            put(_a2, Pawn(color, colorDirection))
            put(_b2, Pawn(color, colorDirection))
            put(_c2, Pawn(color, colorDirection))
            put(_d2, Knight(color))
            put(_e2, Swordsman(color, colorDirection))
            put(_f2, Swordsman(color, colorDirection))
            put(_g2, Knight(color))
            put(_h2, Pawn(color, colorDirection))
            put(_i2, Swordsman(color, colorDirection))
            put(_j2, Pawn(color, colorDirection))
            put(_a8, Rook(!color))
            put(_b8, Catapult(!color))
            put(_c8, Archer(!color))
            put(_d8, Bishop(!color))
            put(_e8, Queen(!color))
            put(_f8, King(!color))
            put(_g8, Bishop(!color))
            put(_h8, Archer(!color))
            put(_i8, Catapult(!color))
            put(_j8, Rook(!color))
            put(_a7, Pawn(!color, !colorDirection))
            put(_b7, Pawn(!color, !colorDirection))
            put(_c7, Pawn(!color, !colorDirection))
            put(_d7, Knight(!color))
            put(_e7, Swordsman(!color, !colorDirection))
            put(_f7, Swordsman(!color, !colorDirection))
            put(_g7, Knight(!color))
            put(_h7, Pawn(!color, !colorDirection))
            put(_i7, Pawn(!color, !colorDirection))
            put(_j7, Pawn(!color, !colorDirection))
            get(_f1)?.gold = defaultBet
            get(_f8)?.gold = defaultBet
        })
    }

    private fun reset() {
        capturedPieces.clear()
        message.value = null
        turn = WHITE
        simulatedEnPassantCapturePosition = 0
        simulatedEnPassantMovePosition = 0
        enPassantCapturePosition = 0
        enPassantMovePosition = 0
        event.value = null
        treasurePositions.clear()
    }

    fun validateMove(start: Int, end: Int): Move{
        boardConfiguration.value?.let { bc ->
            return try {
                validateMove(start, end, bc)
            } catch (e: java.lang.Exception){
                return Move(start, end, false)
            }
        }
        return Move(start, end, false)
    }

    private fun validateMove(start: Int, end: Int, bc: HashMap<Int, Piece>, isSimulation: Boolean = false): Move {
        val king = bc.values.single{ it is King && it.color == bc[start]!!.color }
        val pieceAtStart = bc[start]
        val isNotYourTurn = pieceAtStart?.color != turn && !isSimulation
        val isInsuficientGoldToMove = king.gold < bc[start]?.priceToMove ?: 0
        if (isNotYourTurn || isInsuficientGoldToMove) return Move(start, end, false)

        val legalPositions = pieceAtStart?.getLegalEndPositionsFrom(start)
        val isCapturing = bc[end] != null
        val isMoving = bc[end] == null
        val isPieceLegalMove = legalPositions?.contains(end) == true
        val canJumpCapturing = pieceAtStart?.canJumpWhenCapturing() == true
        val canJumpMoving = pieceAtStart?.canJumpWhileMoving() == true
        val isPathFree = getPiecesBetween(start, end, bc).isEmpty()
        val isEnemyPieceOrEmptySquare = bc[end]?.color != bc[start]?.color
        val isCapturingAndCanJumpCapturing = isCapturing && canJumpCapturing
        val isMovingAndCanJumpMoving = (isMoving && canJumpMoving)
        val isPawnAndCapturingAtFront = isPawnAndCapturingAtFront(pieceAtStart, start, end, bc)
        val isPawnAndMovingDiagonally = isPawnAndMovingDiagonally(pieceAtStart, start, end, bc)
        val isCapatultAndMovingLikeKnight = isCapatultAndMovingLikeKnight(pieceAtStart, start, end, bc)
        val isMovingKing = pieceAtStart is King
        val isKingCapturingDeadlyPeace = isMovingKing && bc[end]?.isDeadly == true
        val pieceOnKingStartPosition = start == _f1 || start == _f8
        val isCastleEndPosition = isCastleEndPosition(end)
        val isKingTryingCastle = !isSimulation && isMovingKing && pieceOnKingStartPosition && isCastleEndPosition
        val isValidCastleMove = if (isSimulation || !isKingTryingCastle || kingIsInCheck(bc, pieceAtStart?.color) ) false else
            isPathFree && isPossibleCastleMove(bc, start, end, pieceAtStart?.color)

        val b = isSimulation || isValidCastleMove
        var moveLeavesKingInCheck = false
        if (!b){
            moveLeavesKingInCheck = simulateMoveAndVerifyKingCheck(start, end, pieceAtStart?.color)
        }

        val isValidPiecePath = isPathFree
                || (isCapturingAndCanJumpCapturing && !isPathFree)
                || (isMovingAndCanJumpMoving && !isPathFree)

        val isValid = isPieceLegalMove
                && isValidPiecePath
                && isEnemyPieceOrEmptySquare
                && !isPawnAndCapturingAtFront
                && !((isPawnAndMovingDiagonally) && end != enPassantMovePosition)
                && !isCapatultAndMovingLikeKnight
                && !isKingCapturingDeadlyPeace
                && !moveLeavesKingInCheck
                && !(isKingTryingCastle && !isValidCastleMove)
        return Move(start, end, isValid)
    }

    private fun isPossibleCastleMove(
        bc: HashMap<Int, Piece>,
        start: Int,
        end: Int,
        color: Boolean?
    ): Boolean {
        return (
                start == _f1 &&
                        end == _i1 &&
                        bc[_j1] is Rook &&
                        bc[_j1]?.isMoved == false &&
                        !simulateMoveAndVerifyKingCheck(start, _g1, color) &&
                        !simulateMoveAndVerifyKingCheck(start, _h1, color)
                ) || (
                start == _f1 &&
                        end == _c1 &&
                        bc[_a1] is Rook &&
                        bc[_a1]?.isMoved == false &&
                        !simulateMoveAndVerifyKingCheck(start, _e1, color) &&
                        !simulateMoveAndVerifyKingCheck(start, _d1, color)
                ) || (
                start == _f8 &&
                        end == _i8 &&
                        bc[_j8] is Rook &&
                        bc[_j8]?.isMoved == false &&
                        !simulateMoveAndVerifyKingCheck(start, _g8, color) &&
                        !simulateMoveAndVerifyKingCheck(start, _h8, color)
                ) || (
                start == _f8 &&
                        end == _c8 &&
                        bc[_a8] is Rook &&
                        bc[_a8]?.isMoved == false &&
                        !simulateMoveAndVerifyKingCheck(start, _e8, color) &&
                        !simulateMoveAndVerifyKingCheck(start, _d8, color)
                )
    }

    private fun isCastleEndPosition(end: Int): Boolean {
        return end == _c1 || end == _i1 || end == _c8 || end == _i8
    }

    private fun isCapatultAndMovingLikeKnight(
        pieceAtStart: Piece?,
        start: Int,
        end: Int,
        bc: java.util.HashMap<Int, Piece>
    ): Boolean {
        return try {
            val knightMove = (pieceAtStart as Catapult)
                .knightSpirit
                .getLegalEndPositionsFrom(start)
                .singleOrNull{
                    it == end
                }
            val knightMoveToEmptyPosition = knightMove != null && bc[knightMove] == null
            knightMoveToEmptyPosition
        } catch (e: java.lang.Exception){
            false
        }
    }

    private fun isPawnAndMovingDiagonally(
        pieceAtStart: Piece?,
        start: Int,
        end: Int,
        bc: java.util.HashMap<Int, Piece>
    ): Boolean {
        return try {
            val diagonalPositions = (pieceAtStart as Pawn).getDiagonalPositions(start)
            var isDiagonal = false
            diagonalPositions.map {
                val enable = it == end && bc[it] == null
                isDiagonal = isDiagonal || enable
            }
            isDiagonal
        } catch (e: Exception){
            false
        }
    }

    private fun isPawnAndCapturingAtFront(pieceAtStart: Piece?, start: Int, end: Int, bc: HashMap<Int,Piece>): Boolean {
        return try {
            (pieceAtStart as Pawn).getFrontPositions(start).singleOrNull{
                bc[it] != null
            } == end
        } catch (e: Exception){
            false
        }
    }

    private fun simulateMoveAndVerifyKingCheck(start: Int, end: Int, startPieceColor: Boolean?): Boolean {
        Log.i("ARNAUD","SIMULAÇÃO INICIADA")
        val newBc = HashMap<Int, Piece>()
        val originalBoardConfiguration = boardConfiguration.value ?: HashMap()
        originalBoardConfiguration.map { newBc.put(it.key, it.value.clone()) }
        movePiece(start, end, newBc, true)
        return kingIsInCheck(newBc, startPieceColor)
    }

    private fun kingIsInCheck(bc: HashMap<Int, Piece>, color: Boolean?): Boolean{
        bc.filterValues {
            it.color != color
        }.map { enemyPiece ->
            val positions = enemyPiece.value.getLegalEndPositionsFrom(enemyPiece.key)
            positions.map { pos ->
                if (validateMove(enemyPiece.key, pos, bc, true).isValid) {
                    val foundKingAsPossibleEnemyMove = bc[pos] is King
                    if (foundKingAsPossibleEnemyMove) {
                        val pieceIsSameColor = bc[pos]?.color == color
                        if (pieceIsSameColor) {
                            return true
                        }
                    }
                }
            }
        }
        return false
    }

    fun movePiece(start: Int, end: Int): HashMap<Int,Piece> {
        boardConfiguration.value?.let { bc ->
            return movePiece(start, end, bc, false)
        }
        return boardConfiguration.value!!
    }

    private fun forcePieceMove(start: Int, end: Int, bc: HashMap<Int, Piece>, isSimulation: Boolean){
        bc[end] = bc[start]!!
        bc.remove(start)
        if (!isSimulation){
            bc[end]?.isMoved = true
        }
    }

    private fun movePiece(
        start: Int,
        end: Int,
        bc: HashMap<Int, Piece>,
        isSimulation: Boolean
    ): HashMap<Int,Piece> {
        var lastPieceCaptured: Piece? = null
        val isCapturing = bc[end] != null && bc[end]!!.color != bc[start]!!.color
        val isKnightCapturing = bc[start] is Knight && isCapturing
        val isEnpassantCapturing = end == enPassantMovePosition && bc[start]?.color != bc[enPassantCapturePosition]?.color
        val isPawnCapturing = bc[start] is Pawn && (isCapturing || isEnpassantCapturing)
        val isSwordsmanCapturing = bc[start] is Swordsman && (isCapturing || isEnpassantCapturing)
        val isPawnDoubleMoving = isPawnDoubleMoving(bc, start, end) && bc[end]?.isDeadly != true
        val king = bc.values.single{ it is King && it.color == bc[start]!!.color }
        val price = bc[start]!!.priceToMove
        bc[start]!!.gold += price
        king.gold -= price
        val totalBattleGold = bc[start]!!.gold + (bc[end]?.gold ?: 0)

        if (isPawnDoubleMoving){
            if (isSimulation){
                simulatedEnPassantMovePosition = getEnpassantMovePosition(end)
                simulatedEnPassantCapturePosition = end
            } else {
                enPassantMovePosition = getEnpassantMovePosition(end)
                enPassantCapturePosition = end
            }
        }

        bc[end]?.let {
            if (!isSimulation) capturedPieces.add(it)
            lastPieceCaptured = it
        }
        bc[end] = bc[start]!!
        bc.remove(start)

        if (isEnpassantCapturing){
            if (!isSimulation) {
                bc[enPassantCapturePosition]?.let {
                    capturedPieces.add(it)
                    lastPieceCaptured = it
                }
                bc.remove(enPassantCapturePosition)
            }
        }

        if (!isPawnDoubleMoving && !isEnpassantCapturing){
            if (isSimulation){
                simulatedEnPassantMovePosition = 0
                simulatedEnPassantCapturePosition = 0
            } else {
                enPassantMovePosition = 0
                enPassantCapturePosition = 0
            }
        }

        if (bc[end] is King && isCastleEndPosition(end) && bc[end]?.isMoved == false){
            when (end){
                _c1 -> forcePieceMove(_a1, _d1, bc, isSimulation)
                _i1 -> forcePieceMove(_j1, _h1, bc, isSimulation)
                _c8 -> forcePieceMove(_a8, _d8, bc, isSimulation)
                _i8 -> forcePieceMove(_j8, _h8, bc, isSimulation)
            }
        }

        bc[end]?.isMoved = true
        if (bc[end]?.color != null && isKnightCapturing && lastPieceCaptured?.isDeadly == false && isFromYourBoardSide(start, bc[end]!!.color!!)){
            val color = bc[end]!!.color!!
            bc[start] = Pawn(color, if (color == WHITE) whiteDirection else blackDirection)
        }

        var successHeroPromotion = false
        topBoardPositions.singleOrNull{
            (bc[it] is Swordsman && (bc[it] as Swordsman).direction == UP)
        }?.let {
            bc[it] = if (bc[it]!!.color == WHITE) Viking() else Ninja()
            successHeroPromotion = true
        }

        bottomBoardPositions.singleOrNull{
            (bc[it] is Swordsman && (bc[it] as Swordsman).direction == DOWN)
        }?.let {
            bc[it] = if (bc[it]!!.color == WHITE) Viking() else Ninja()
            successHeroPromotion = true
        }

        if (!successHeroPromotion) {
            val endColor = bc[end]!!.color
            if (endColor != null && isPawnCapturing) {
                bc[end] = Swordsman(endColor, if (endColor == WHITE) whiteDirection else blackDirection)
            } else if (lastPieceCaptured?.color != null && isSwordsmanCapturing && lastPieceCaptured != null && lastPieceCaptured !is Swordsman) {
                bc[end] = when (lastPieceCaptured) {
                    is Viking -> Ninja().apply { setDeadlyPiece(!lastPieceCaptured!!.isDeadly) }
                    is Ninja -> Viking().apply { setDeadlyPiece(!lastPieceCaptured!!.isDeadly) }
                    else ->
                        lastPieceCaptured!!.clone().apply {
                        color = !(lastPieceCaptured!!.color!!)
                    }
                }
            }
        }

        bc[end]?.setDeadlyPiece(if (bc[end] is King) false else
                    (bc[end] is Ninja && getSquareColor(end) == BLACK_SQUARE) ||
                    (bc[end] is Viking && getSquareColor(end) == WHITE_SQUARE) ||
                    (bc[end]?.color == WHITE && getSquareColor(end) == WHITE_SQUARE && isFromYourBoardSide(end, WHITE)) ||
                    (bc[end]?.color == BLACK && getSquareColor(end) == BLACK_SQUARE && isFromYourBoardSide(end, BLACK)))

        if (lastPieceCaptured?.isDeadly == true){
            if (!isSimulation) capturedPieces.add(lastPieceCaptured!!)
            bc.remove(end)
            bc[end] = Treasure()
            bc[end]?.gold = totalBattleGold
        } else {
            king.gold += lastPieceCaptured?.gold ?: 0
        }

        return bc
    }

    private fun isFromYourBoardSide(position: Int, color: Boolean): Boolean {
        val direction = if (color == WHITE) whiteDirection else blackDirection
        val sidePositions = if (direction == UP) bottomSideBoardPositions else topSideBoardPositions
        return sidePositions.contains(position)
    }


    private fun getEnpassantMovePosition(end: Int): Int {
        return when (end){
            _a4 -> _a3
            _b4 -> _b3
            _c4 -> _c3
            _d4 -> _d3
            _e4 -> _e3
            _f4 -> _f3
            _g4 -> _g3
            _h4 -> _h3
            _i4 -> _i3
            _j4 -> _j3
            _a5 -> _a6
            _b5 -> _b6
            _c5 -> _c6
            _d5 -> _d6
            _e5 -> _e6
            _f5 -> _f6
            _g5 -> _g6
            _h5 -> _h6
            _i5 -> _i6
            _j5 -> _j6
            else -> 0
        }
    }

    private fun isPawnDoubleMoving(bc: HashMap<Int, Piece>, start: Int, end: Int): Boolean {
        return bc[start] is Swordsman && (
                (start == _a2 && end == _a4) ||
                (start == _b2 && end == _b4) ||
                (start == _c2 && end == _c4) ||
                (start == _d2 && end == _d4) ||
                (start == _e2 && end == _e4) ||
                (start == _f2 && end == _f4) ||
                (start == _g2 && end == _g4) ||
                (start == _h2 && end == _h4) ||
                (start == _i2 && end == _i4) ||
                (start == _j2 && end == _j4) ||
                (start == _a7 && end == _a5) ||
                (start == _b7 && end == _b5) ||
                (start == _c7 && end == _c5) ||
                (start == _d7 && end == _d5) ||
                (start == _e7 && end == _e5) ||
                (start == _f7 && end == _f5) ||
                (start == _g7 && end == _g5) ||
                (start == _h7 && end == _h5) ||
                (start == _i7 && end == _i5) ||
                (start == _j7 && end == _j5)
        )
    }

    private fun getSquareColor(position: Int): Boolean {
        return arrayListOf(
            _b1, _d1, _f1, _h1, _j1,
            _a2, _c2, _e2, _g2, _i2,
            _b3, _d3, _f3, _h3, _j3,
            _a4, _c4, _e4, _g4, _i4,
            _b5, _d5, _f5, _h5, _j5,
            _a6, _c6, _e6, _g6, _i6,
            _b7, _d7, _f7, _h7, _j7,
            _a8, _c8, _e8, _g8, _i8
        ).contains(position)
    }

    private fun getPiecesBetween(start: Int, end: Int): ArrayList<Piece> {
        boardConfiguration.value?.let { bc ->
            return getPiecesBetween(start, end, bc)
        }
        return arrayListOf()
    }

    private fun getPiecesBetween(start: Int, end: Int, bc: HashMap<Int, Piece>): ArrayList<Piece> {
        boardLines.singleOrNull {
            it.contains(start) && it.contains(end)
        }?.let { currentLinePositions ->
            val startIndex = currentLinePositions.indexOf(start)
            val endIndex = currentLinePositions.indexOf(end)
            return if (startIndex - endIndex !in -1..1) {
                val isCrescent = startIndex < endIndex
                ArrayList<Piece>().apply {
                    currentLinePositions.subList(
                        (if (isCrescent) startIndex else endIndex) + 1,
                        (if (isCrescent) endIndex else startIndex)
                    ).map { linePosition ->
                        bc[linePosition]?.let { piece ->
                            add(piece)
                        }
                    }
                }
            } else {
                arrayListOf()
            }
        }
        return arrayListOf()
    }

    inner class Move(
        val start: Int,
        val end: Int,
        val isValid: Boolean
    ){

        fun execute(){
            if (isValid){
                boardConfiguration.value = movePiece(start, end)
                turn = !turn
                verifyCheckMateOrDraw()
            } else {
                message.postValue(R.string.illegal_move)
            }

        }

    }

    private fun verifyCheckMateOrDraw() {
        AsyncTask.execute {
            boardConfiguration.value?.let { bc ->
                allBoardPositions.map { pos ->
                    if (bc[pos]?.color == turn){
                        bc[pos]?.getLegalEndPositionsFrom(pos)?.map { legalPos ->
                            if (validateMove(pos, legalPos).isValid){
                                if (kingIsInCheck(bc, turn)){
                                    event.postValue(CHECK)
                                }
                                return@execute
                            }
                        }
                    }
                }
                if (kingIsInCheck(bc, turn)){
                    event.postValue(CHECKMATE)
                } else {
                    event.postValue(DRAW)
                }
            }
        }
    }


    companion object{
        const val WHITE_SQUARE = true
        const val BLACK_SQUARE = false
        const val CHECK = 1
        const val DRAW = 2
        const val CHECKMATE = 3
    }
}