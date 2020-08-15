package br.com.arnaudchess.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import br.com.arnaudchess.R
import br.com.arnaudchess.mirrorMap
import br.com.arnaudchess.model.Room
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    var plays: DatabaseReference? = null
    var boardFragment: BoardFragment? = null

    val auth = FirebaseAuth.getInstance()
    val database = FirebaseDatabase.getInstance()


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

        searchOpponentButton.setOnClickListener {
            auth.uid?.let {
                val openGameQuery: Query = database.getReference("games")
                openGameQuery.addListenerForSingleValueEvent(object : ValueEventListener{
                    override fun onCancelled(error: DatabaseError) {}

                    override fun onDataChange(snapshot: DataSnapshot) {
                        for (roomSnapshot in snapshot.children) {
                            if (!roomSnapshot.child("player2").exists()) {
                                joinGame(roomSnapshot.child("player2"))
                                return
                            }
                        }
                        createGame()
                    }
                })
            }
        }

        signoutButton.setOnClickListener{
            auth.signOut()
            startLoginActivity()
        }
    }

    private fun joinGame(player2: DataSnapshot) {
        auth.uid?.let{
            player2.ref.setValue(
                Room.Player(
                    uid = it,
                    name = auth.currentUser?.displayName ?: "Desconhecido"
                )
            )
            player2.ref.parent?.let {
                listenMoves(it)
                boardFragment?.startBlackBottom()
            }
        }
    }

    private fun listenMoves(game: DatabaseReference) {
        plays = game.ref.child("plays")
        plays?.addChildEventListener(object: ChildEventListener{
            override fun onCancelled(error: DatabaseError) {

            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {

            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {

            }

            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val start = mirrorMap[snapshot.child("start").value.toString()]
                val end = mirrorMap[snapshot.child("end").value.toString()]
                val color = snapshot.child("color").value.toString().toBoolean()
                if (start != null && end != null) {
                    boardFragment?.moveEnemyPiece(color, start, end)
                }

//                snapshot.children.last().getValue(Room.Play::class.java)?.let { lastPlay ->
//                    val start = mirrorMap[lastPlay.start]
//                    val end = mirrorMap[lastPlay.end]
//                    if (start != null && end != null) {
//                        boardFragment?.moveEnemyPiece(start, end)
//                    }
//                }
            }
            override fun onChildRemoved(snapshot: DataSnapshot) {

            }

        })

    }

    private fun createGame() {
        auth.uid?.let{
            listenMoves(database
                .getReference("games")
                .child(System.currentTimeMillis().toString()).apply {
                    setValue(
                    Room(
                        player1 = Room.Player(
                            uid = it,
                            name = auth.currentUser?.displayName ?: "Desconhecido"
                        )
                    )
            )
            })
        }
        boardFragment?.startWhiteBottom()
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
            myNameTextView.text = auth.currentUser?.displayName
        } else {
            finish()
        }
    }

    companion object {
        const val LOGIN_REQUEST_CODE = 999
    }
}