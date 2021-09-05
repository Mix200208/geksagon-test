package com.example.musicplayerapp.view

import android.app.Notification
import android.graphics.Color
import android.media.AudioManager
import android.media.AudioManager.STREAM_MUSIC
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.media.session.MediaSessionCompat
import android.widget.ImageButton
import androidx.core.net.toUri
import com.example.musicplayerapp.R
import com.example.musicplayerapp.R.color.light_blue


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val url = "https://mp3l.jamendo.com//?trackid=887202&format=mp31&from=app-devsite"

        val repeatButton: ImageButton = findViewById(R.id.repeatButton)
        val playButton: ImageButton = findViewById(R.id.playButton)


        val player: MediaPlayer = MediaPlayer().apply {

            setAudioStreamType(STREAM_MUSIC);
            setDataSource(url);
            prepare()

        }

        initRepeatButton(repeatButton, player)

        initPlayButton(playButton, player)
    }

    private fun initPlayButton(playButton: ImageButton, player: MediaPlayer) {
        playButton.setOnClickListener {
            when (!player.isPlaying) {
                true -> {
                    player.start()
                    playButton.setImageResource(R.drawable.ic_baseline_pause_60)
                }

                false -> {
                    player.pause()
                    playButton.setImageResource(R.drawable.ic_baseline_play_arrow_60)

                }


            }

        }
    }

    private fun initRepeatButton(repeatButton: ImageButton, player: MediaPlayer) {
        repeatButton.setOnClickListener {

            player.isLooping = !player.isLooping

            val color = when (player.isLooping) {
                true -> Color.GREEN
                false -> resources.getColor(light_blue)
            }

            repeatButton.setColorFilter(color)

        }
    }
}

