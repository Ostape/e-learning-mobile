package com.robosh.basestartapplication.lessondetail.view

import android.os.Bundle
import android.util.Log
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.robosh.basestartapplication.R
import com.robosh.basestartapplication.application.INTENT_LESSON_KEY
import com.robosh.basestartapplication.databinding.ActivityLessonDetailBinding
import com.robosh.basestartapplication.lessondetail.LessonDetailVideoPlayerConfig.Companion.API_KEY
import com.robosh.basestartapplication.model.Lesson
import com.squareup.picasso.Picasso
import java.text.DateFormat

class LessonDetailActivity : YouTubeBaseActivity() {

    private lateinit var onInitializedListener: YouTubePlayer.OnInitializedListener
    private lateinit var binding: ActivityLessonDetailBinding

    private var cachedLesson: Lesson? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLessonDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        cachedLesson = intent.extras?.get(INTENT_LESSON_KEY) as Lesson?
        initViews(cachedLesson)

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
        binding.youtubePlayerId.initialize(API_KEY, onInitializedListener)
    }

    private fun initViews(lesson: Lesson?) {
        lesson?.let {
            with(binding) {
                detailLessonTitle.text = lesson.name
                Picasso.get().load(lesson.imageLesson).into(detailLessonImage)
                detailLessonDescription.text = lesson.text
                lessonDuration.text = "Тривалість уроку: ${lesson.durationMinutes} хвилин"
                lessonDeadline.text = "Дедлайн: ${DateFormat.getDateInstance().format(lesson.deadline)}"
            }
        }
    }
}