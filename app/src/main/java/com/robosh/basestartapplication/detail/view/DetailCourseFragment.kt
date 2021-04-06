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
import com.robosh.basestartapplication.model.MovieEvent
import com.robosh.basestartapplication.model.MovieState
import com.robosh.basestartapplication.net.api.MovieDbApi
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
        arguments?.getInt(INTENT_MOVIE_KEY)?.let {
            detailViewModel.intentChannel.offer(MovieEvent.MovieNotified(it))
        }
    }

    private fun render(movieState: MovieState) {
        when (movieState) {
            is MovieState.SingleDataState -> displayMovie(movieState)
            MovieState.Idle -> Unit
            MovieState.LoadingState -> displayLoader()
            is MovieState.ErrorState -> displayError(movieState)
            is MovieState.DataListState -> Unit
            is MovieState.MovieClickedState -> Unit
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

    private fun displayMovie(movieState: MovieState.SingleDataState) {
        hideLoader()
        with(binding) {
            detailMovieContent.visibility = VISIBLE
            detailMovieDescription.text = movieState.movie.description
            Picasso.get().load(MovieDbApi.IMAGE_BASE_URL + movieState.movie.posterUrl)
                .into(detailMovieImage)
            detailMovieTitle.text = movieState.movie.title
        }
    }

    private fun displayError(movieState: MovieState.ErrorState) {
        hideLoader()
        binding.errorTextView.visibility = VISIBLE
        binding.errorTextView.text = movieState.data
    }
}
