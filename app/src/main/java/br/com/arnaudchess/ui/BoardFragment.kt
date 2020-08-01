package br.com.arnaudchess.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import br.com.arnaudchess.R
import br.com.arnaudchess.model.Piece
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
                putPiecesOnBoard(it)
            }
        })

        vm.message.observe(viewLifecycleOwner, Observer {
            Toast.makeText(context, getString(it), Toast.LENGTH_SHORT).show()
        })

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
            && selectedBoardPositionFrameLayout!!.getPieceImageView()?.piece?.color == sbf.getPieceImageView()?.piece?.color
        ) {
            clearBorders()
            selectedBoardPositionFrameLayout = null
        }
        if (selectedBoardPositionFrameLayout == null) {
            if (!sbf.containsPiece()) return
            selectedBoardPositionFrameLayout = sbf
            sbf.select()
            sbf.getPieceImageView()?.piece?.getLegalEndPositionsFrom(sbf.id)?.map { legalEndPosition ->
                view?.findViewById<BoardPositionFrameLayout>(legalEndPosition)?.let { legalEndBoardPosition ->
                    if (vm.validateMove(
                        start = selectedBoardPositionFrameLayout!!.id,
                        end = legalEndPosition).isValid
                    ){
                        legalEndBoardPosition.hint()
                    }
                }
            }
        } else {
            vm.validateMove(
                start = selectedBoardPositionFrameLayout!!.id,
                end = sbf.id
            ).execute()
            clearBorders()
            selectedBoardPositionFrameLayout = null
        }
    }

    fun startWhite() {
        vm.startWhite()
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