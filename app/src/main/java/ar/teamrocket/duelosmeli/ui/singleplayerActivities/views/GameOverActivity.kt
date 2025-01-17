package ar.teamrocket.duelosmeli.ui.singleplayerActivities.views

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import ar.teamrocket.duelosmeli.R
import ar.teamrocket.duelosmeli.data.database.Player
import ar.teamrocket.duelosmeli.data.database.PlayerDao
import ar.teamrocket.duelosmeli.databinding.ActivityGameOverBinding
import ar.teamrocket.duelosmeli.ui.singleplayerActivities.adapters.HighScoreAdapter
import org.koin.android.ext.android.inject


class GameOverActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGameOverBinding
    private val playerDao : PlayerDao by inject()
//TODO: Agregar ViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameOverBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val gameOverSound = MediaPlayer.create(this, R.raw.gameover)
        gameOverSound.start()
        binding.btnBackToHome.setOnClickListener { viewNewGame() }

        val topTenPlayers = playerDao.getTopTenOrderByScore()

        val idPlayer = intent.extras!!.getLong("IdPlayer")
        //obtener jugador
        //val num: Long = 1
        var player:List<Player> = emptyList()

        if (idPlayer > 0) {
            player = playerDao.getById(idPlayer)
        }
        //playerDao.updatePlayer(player[0])
        val pointsAchieved = intent.extras!!.getInt("Points")
        val pointsAchievedString = getString(R.string.achieved_points, pointsAchieved)
        val pointsHighscore = getString(R.string.record_points, player[0].score)

        binding.tvScoreAchieved.text = pointsAchievedString
        binding.tvHigherScore.text = pointsHighscore

        //Highscore RecyclerView
        binding.rvScoreTable.layoutManager = LinearLayoutManager(this)
        binding.rvScoreTable.adapter = HighScoreAdapter(topTenPlayers)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, NewGameActivity::class.java)
        startActivity(intent)
    }

    private fun viewNewGame() {
        val intent = Intent(this, NewGameActivity::class.java)
        startActivity(intent)
        finish()
    }
}