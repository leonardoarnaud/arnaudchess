package br.com.arnaudchess.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import br.com.arnaudchess.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    lateinit var boardFragment: BoardFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.boardContainerFrameLayout, BoardFragment.newInstance("", ""), BoardFragment.tag)
                .commitNow()
        }

        boardFragment = supportFragmentManager.findFragmentByTag(BoardFragment.tag) as BoardFragment

        boardContainerFrameLayout.post {
            boardContainerFrameLayout.layoutParams = LinearLayout.LayoutParams(
                boardContainerFrameLayout.height + (boardContainerFrameLayout.height * 2.6 / 10).toInt(),
                boardContainerFrameLayout.height
            )
        }

        searchOpponentButton.setOnClickListener {
            boardFragment.searchOpponent()
        }

        signoutButton.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            startLoginActivity()
        }
    }

    override fun onStart() {
        super.onStart()

        if (FirebaseAuth.getInstance().currentUser == null){
            startLoginActivity()
        }
    }

    private fun startLoginActivity() {
        startActivity(Intent(this, LoginAcitivty::class.java))
        finish()
    }

    fun setGold(amount: Int) {
        goldTextView.text = "Meu Gold: $$amount"
    }

    fun setEnemyGold(amount: Int) {
        enemyGoldTextView.text = "Inimigo Gold: $$amount"
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK && requestCode == LOGIN_REQUEST_CODE){
            myNameTextView.text = FirebaseAuth.getInstance().currentUser?.displayName
        } else {
            finish()
        }
    }

    companion object {
        const val LOGIN_REQUEST_CODE = 999
    }
}