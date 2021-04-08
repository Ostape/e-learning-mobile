package com.robosh.basestartapplication.detail.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.robosh.basestartapplication.application.INTENT_MOVIE_KEY
import com.robosh.basestartapplication.databinding.FragmentDetailBinding
import com.robosh.basestartapplication.detail.presenter.DetailViewModel
import com.robosh.basestartapplication.model.CourseEvent
import com.robosh.basestartapplication.model.CourseState
import com.robosh.basestartapplication.net.api.ElearningApi
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class DetailCourseFragment : Fragment() {

    private val detailViewModel: DetailViewModel by viewModels()
    private lateinit var binding: FragmentDetailBinding

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
        detailViewModel.state.onEach {
            render(it)
        }.launchIn(lifecycleScope)
        arguments?.getString(INTENT_MOVIE_KEY)?.let {
            detailViewModel.intentChannel.offer(CourseEvent.CourseNotified(it))
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
        hideLoader()
        with(binding) {
            detailMovieContent.visibility = VISIBLE
            detailCourseDescription.text = courseState.course.description
            Picasso.get().load(courseState.course.courseImage)
                .into(detailCourseImage)
            detailCourseTitle.text = courseState.course.name
        }
    }

    private fun displayError(courseState: CourseState.ErrorState) {
        hideLoader()
        binding.errorTextView.visibility = VISIBLE
        binding.errorTextView.text = courseState.data
    }
}
