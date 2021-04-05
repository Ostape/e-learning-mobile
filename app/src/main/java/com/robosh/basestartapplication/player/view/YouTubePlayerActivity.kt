package com.robosh.basestartapplication.player.view

import android.os.Bundle
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.robosh.basestartapplication.R
import com.robosh.basestartapplication.player.PlayerConfig.Companion.API_KEY
import kotlinx.android.synthetic.main.activity_youtube.*

class YouTubePlayerActivity : YouTubeBaseActivity() {

    private lateinit var onInitializedListener: YouTubePlayer.OnInitializedListener


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_youtube)
        onInitializedListener = object : YouTubePlayer.OnInitializedListener {
            override fun onInitializationSuccess(
                provider: YouTubePlayer.Provider?,
                youTubePlayer: YouTubePlayer?,
                p2: Boolean
            ) {
                youTubePlayer?.loadVideo("_hGcXJjPsTw")
            }

            override fun onInitializationFailure(
                p0: YouTubePlayer.Provider?,
                p1: YouTubeInitializationResult?
            ) {

            }

        }
        youtubePlayBtn.setOnClickListener {
            youtubePlayerId.initialize(API_KEY, onInitializedListener)
        }
    }
}