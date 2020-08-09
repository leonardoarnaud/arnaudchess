package br.com.arnaudchess.ui

import android.app.AlertDialog
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import br.com.arnaudchess.R
import br.com.arnaudchess.model.Piece
import br.com.arnaudchess.model.Piece.Companion.BLACK
import br.com.arnaudchess.model.Piece.Companion.WHITE
import br.com.arnaudchess.ui.BoardViewModel.Companion.CHECK
import br.com.arnaudchess.ui.BoardViewModel.Companion.CHECKMATE
import br.com.arnaudchess.ui.BoardViewModel.Companion.DRAW
import kotlinx.android.synthetic.main.fragment_board.*
import java.util.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class BoardFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    private var pieceOnHand: Piece? = null

    private var selectedBoardPositionFrameLayout: BoardPositionFrameLayout? = null

    val vm: BoardViewModel by viewModels()

    lateinit var boardPositions: ArrayList<BoardPositionFrameLayout>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_board, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        boardPositions = arrayListOf(
            a1, b1, c1, d1, e1, f1, g1, h1, i1, j1,
            a2, b2, c2, d2, e2, f2, g2, h2, i2, j2,
            a3, b3, c3, d3, e3, f3, g3, h3, i3, j3,
            a4, b4, c4, d4, e4, f4, g4, h4, i4, j4,
            a5, b5, c5, d5, e5, f5, g5, h5, i5, j5,
            a6, b6, c6, d6, e6, f6, g6, h6, i6, j6,
            a7, b7, c7, d7, e7, f7, g7, h7, i7, j7,
            a8, b8, c8, d8, e8, f8, g8, h8, i8, j8
        )

        vm.boardConfiguration.observe(viewLifecycleOwner, Observer {
            it?.let {
                clearBoard()
                clearBorders()
                putPiecesOnBoard(it)
            }
        })

        vm.message.observe(viewLifecycleOwner, Observer {it?.let {
            Toast.makeText(context, getString(it), Toast.LENGTH_SHORT).show()
        }})

        vm.event.observe(viewLifecycleOwner, Observer { it?.let {
            when (it) {
                CHECK -> Toast.makeText(
                    context,
                    getString(R.string.check_message),
                    Toast.LENGTH_SHORT
                ).show()
                DRAW -> AlertDialog.Builder(context)
                    .setTitle(R.string.game_over_dialog_title)
                    .setMessage(R.string.draw_dialog_message)
                    .setIcon(if (vm.turn) R.drawable.ic_king_white else R.drawable.ic_king_black)
                    .setPositiveButton(android.R.string.ok) { _, _ -> }
                    .show()
                CHECKMATE -> AlertDialog.Builder(context)
                    .setTitle(R.string.game_over_dialog_title)
                    .setMessage(String.format(
                        getString(R.string.checkmate_dialog_message),
                        if (vm.turn) getString(R.string.blacks) else getString(R.string.whites)
                    )).setIcon(if (vm.turn) R.drawable.ic_king_black else R.drawable.ic_king_white)
                    .setPositiveButton(android.R.string.ok) { _, _ -> }
                    .show()
                else -> Log.i("as","")
            }
        }})

        vm.myGold.observe(viewLifecycleOwner, Observer { it?.let {
            (requireActivity() as MainActivity).setGold(it)
        }})

        boardPositions.map {
            it.setOnClickListener {
                selectToMove(it as BoardPositionFrameLayout)
            }
        }
    }

    private fun putPiecesOnBoard(pieces: HashMap<Int, Piece>) {
        pieces.map { pieceEntry ->
            context?.let { ctx ->
                view?.findViewById<BoardPositionFrameLayout>(pieceEntry.key)
                    ?.putPiece(pieceEntry.value.createImageView(ctx))
            }
        }
    }

    private fun clearBoard() {
        boardPositions.map {
            it.removePiece()
        }
    }

    private fun clearBorders() {
        boardPositions.map {
            it.removeBorder()
        }
    }

    private fun selectToMove(sbf: BoardPositionFrameLayout) {
        if (selectedBoardPositionFrameLayout != null
            && (selectedBoardPositionFrameLayout!!.getPieceImageView()?.piece?.color == sbf.getPieceImageView()?.piece?.color ||
                    selectedBoardPositionFrameLayout!!.getPieceImageView()?.piece?.color != vm.turn)
        ) {
            clearBorders()
            selectedBoardPositionFrameLayout = null
        }
        if (selectedBoardPositionFrameLayout == null) {
            if (!sbf.containsPiece()) return
            selectedBoardPositionFrameLayout = sbf
            sbf.select()
            AsyncTask.execute{
                try {
                    val boardPositions = arrayListOf<BoardPositionFrameLayout>()
                    sbf.getPieceImageView()?.piece?.getLegalEndPositionsFrom(sbf.id)
                        ?.map { legalEndPosition ->

                            view?.findViewById<BoardPositionFrameLayout>(legalEndPosition)
                                ?.let { legalEndBoardPosition ->
                                    if (vm.validateMove(
                                            start = selectedBoardPositionFrameLayout!!.id,
                                            end = legalEndPosition
                                        ).isValid
                                    ) {
                                        boardPositions.add(legalEndBoardPosition)
                                    }
                                }
                        }
                    activity?.runOnUiThread {
                        if (sbf.containsPiece()){
                            boardPositions.map {
                                it.hint()
                            }
                        }
                    }
                } catch (e: Exception){
                    e.printStackTrace()
                }
            }
        } else {
            vm.validateMove(
                start = selectedBoardPositionFrameLayout!!.id,
                end = sbf.id
            ).execute()
            clearBorders()
            selectedBoardPositionFrameLayout = null
            boardPositions.map {
                val square = vm.boardConfiguration.value?.get(it.id)
                if (square?.isDeadly == true){
                    it.getPieceImageView()?.setDeadly(true)
                } else {
                    it.getPieceImageView()?.setDeadly(false)
                }
            }
        }
    }

    fun startWhiteBottom() {
        vm.start(WHITE)
    }

    fun startBlackBottom() {
        vm.start(BLACK)
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BoardFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}