package com.robosh.basestartapplication.player.view

import android.os.Bundle
import android.util.Log
import android.view.View.GONE
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.robosh.basestartapplication.R
import com.robosh.basestartapplication.application.INTENT_LESSON_KEY
import com.robosh.basestartapplication.model.Lesson
import com.robosh.basestartapplication.player.PlayerConfig.Companion.API_KEY
import kotlinx.android.synthetic.main.activity_youtube.*

class YouTubePlayerActivity : YouTubeBaseActivity() {

    private lateinit var onInitializedListener: YouTubePlayer.OnInitializedListener

    private var cachedLesson: Lesson? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cachedLesson = intent.extras?.get(INTENT_LESSON_KEY) as Lesson?
        Log.d("TAGGERRR", cachedLesson.toString())
        setContentView(R.layout.activity_youtube)
        onInitializedListener = object : YouTubePlayer.OnInitializedListener {
            override fun onInitializationSuccess(
                provider: YouTubePlayer.Provider?,
                youTubePlayer: YouTubePlayer?,
                p2: Boolean
            ) {
                youTubePlayer?.loadVideo("g8chWfmLb6M")
            }

            override fun onInitializationFailure(
                p0: YouTubePlayer.Provider?,
                p1: YouTubeInitializationResult?
            ) {

            }
        }
    }

    override fun onStart() {
        super.onStart()
        youtubePlayerId.initialize(API_KEY, onInitializedListener)
    }
}