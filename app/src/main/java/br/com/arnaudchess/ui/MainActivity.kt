package br.com.arnaudchess.ui

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import br.com.arnaudchess.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var boardFragment: BoardFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null || boardFragment == null) {
            boardFragment = BoardFragment.newInstance("", "")
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.boardContainerFrameLayout, boardFragment!!)
                .commitNow()
        }

        boardContainerFrameLayout.post {
            boardContainerFrameLayout.layoutParams = LinearLayout.LayoutParams(
                boardContainerFrameLayout.height + (boardContainerFrameLayout.height * 2.6 / 10).toInt(),
                boardContainerFrameLayout.height
            )
        }

        startWhiteButton.setOnClickListener {
            boardFragment?.startWhiteBottom()
        }
        startBlackButton.setOnClickListener {
            boardFragment?.startBlackBottom()
        }

    }

    fun setGold(amount: Int) {
        goldTextView.text = "Meu Gold: $$amount"
    }

    fun setEnemyGold(amount: Int) {
        enemyGoldTextView.text = "Inimigo Gold: $$amount"
    }
}