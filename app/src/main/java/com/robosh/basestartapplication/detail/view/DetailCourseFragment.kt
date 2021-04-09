package com.robosh.basestartapplication.detail.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.robosh.basestartapplication.R
import com.robosh.basestartapplication.application.INTENT_LESSON_KEY
import com.robosh.basestartapplication.application.INTENT_MOVIE_KEY
import com.robosh.basestartapplication.databinding.FragmentDetailBinding
import com.robosh.basestartapplication.detail.presenter.DetailViewModel
import com.robosh.basestartapplication.detail.view.comments.CommentsAdapter
import com.robosh.basestartapplication.detail.view.lesson.ClickLessonCallback
import com.robosh.basestartapplication.detail.view.lesson.ClickLessonListenerCallbackImpl
import com.robosh.basestartapplication.lessondetail.view.LessonDetailActivity
import com.robosh.basestartapplication.model.Course
import com.robosh.basestartapplication.model.CourseEvent
import com.robosh.basestartapplication.model.CourseState
import com.robosh.basestartapplication.model.Lesson
import com.robosh.basestartapplication.net.data.registeredUser
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class DetailCourseFragment : Fragment(), ClickLessonCallback {

    private val detailViewModel: DetailViewModel by viewModels()
    private lateinit var binding: FragmentDetailBinding
    private lateinit var lessonsAdapter: LessonsAdapter
    private lateinit var commentsAdapter: CommentsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lessonsAdapter = LessonsAdapter(ClickLessonListenerCallbackImpl(this))
        commentsAdapter = CommentsAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    @ExperimentalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        detailViewModel.state.onEach {
            render(it)
        }.launchIn(lifecycleScope)
        arguments?.getString(INTENT_MOVIE_KEY)?.let {
            detailViewModel.intentChannel.offer(CourseEvent.CourseNotified(it))
        }
    }

    private fun initRecyclerView() {
        with(binding.lessonsRecyclerView) {
            setHasFixedSize(true)
            isNestedScrollingEnabled = false
            adapter = lessonsAdapter
            layoutManager =
                androidx.recyclerview.widget.LinearLayoutManager(this@DetailCourseFragment.requireContext())
        }
        with(binding.commentsRecyclerView) {
            setHasFixedSize(true)
            isNestedScrollingEnabled = false
            adapter = commentsAdapter
            layoutManager =
                androidx.recyclerview.widget.LinearLayoutManager(this@DetailCourseFragment.requireContext())
        }
    }

    private fun render(courseState: CourseState) {
        when (courseState) {
            is CourseState.SingleDataState -> displayMovie(courseState)
            CourseState.Idle -> Unit
            CourseState.LoadingState -> displayLoader()
            is CourseState.ErrorState -> displayError(courseState)
            is CourseState.DataListState -> Unit
            is CourseState.CourseSubscribeClickedState -> Unit
        }
    }

    private fun displayLoader() {
        with(binding) {
            progressBar.visibility = VISIBLE
            detailMovieContent.visibility = GONE
            errorTextView.visibility = GONE
        }
    }

    private fun hideLoader() {
        binding.progressBar.visibility = GONE
    }

    private fun displayMovie(courseState: CourseState.SingleDataState) {
        setIconState(courseState.course)
        binding.likeIcon.setOnClickListener {
            if (courseState.course.isCourseLiked) {
                courseState.course.isCourseLiked = false
                registeredUser.wishListCourses?.remove(courseState.course)
                binding.likeIcon.setImageResource(R.drawable.ic_empty_like)
            } else {
                courseState.course.isCourseLiked = true
                registeredUser.wishListCourses?.add(courseState.course)
                binding.likeIcon.setImageResource(R.drawable.ic_full_like)
            }
        }
        hideLoader()
        with(binding) {
            detailMovieContent.visibility = VISIBLE
            detailCourseDescription.text = courseState.course.description
            Picasso.get().load(courseState.course.courseImage)
                .into(detailCourseImage)
            detailCourseTitle.text = courseState.course.name
            rating.rating = courseState.course.rating?.toFloat() ?: 0f
            lessonsAdapter.setData(courseState.course.lessons)
            commentsAdapter.setData(courseState.course.comments ?: emptyList())
        }
    }

    private fun displayError(courseState: CourseState.ErrorState) {
        hideLoader()
        binding.errorTextView.visibility = VISIBLE
        binding.errorTextView.text = courseState.data
    }

    override fun onLessonClicked(lesson: Lesson) {
        startActivity(Intent(requireContext(), LessonDetailActivity::class.java).apply {
            putExtra(INTENT_LESSON_KEY, lesson)
        })
    }

    private fun setIconState(course: Course) {
        if (course.isCourseLiked) {
            binding.likeIcon.setImageResource(R.drawable.ic_full_like)
        } else {
            binding.likeIcon.setImageResource(R.drawable.ic_empty_like)
        }
    }
}
