package br.com.arnaudchess.ui

import android.os.Bundle
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

        startWhiteButton.setOnClickListener {
            boardFragment?.startWhite()
        }

    }
}