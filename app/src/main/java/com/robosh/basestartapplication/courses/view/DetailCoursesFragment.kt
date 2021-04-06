package com.robosh.basestartapplication.courses.view

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.robosh.basestartapplication.R
import com.robosh.basestartapplication.application.INTENT_MOVIE_KEY
import com.robosh.basestartapplication.courses.presenter.CoursesViewModel
import com.robosh.basestartapplication.courses.view.detail.DetailCoursesClickCallback
import com.robosh.basestartapplication.courses.view.detail.DetailCoursesClickListenerFactoryImpl
import com.robosh.basestartapplication.courses.view.subscribe.SubscribeCourseClickCallback
import com.robosh.basestartapplication.courses.view.subscribe.SubscribeCourseClickListenerFactoryImpl
import com.robosh.basestartapplication.databinding.FragmentCoursesBinding
import com.robosh.basestartapplication.model.CourseEvent
import com.robosh.basestartapplication.model.CourseState
import com.robosh.basestartapplication.model.Movie
import com.robosh.basestartapplication.receiver.AlarmNotificationReceiver
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.*

@AndroidEntryPoint
class DetailCoursesFragment : Fragment(),
    DetailCoursesClickCallback, SubscribeCourseClickCallback {

    private val coursesViewModel: CoursesViewModel by viewModels()
    private lateinit var binding: FragmentCoursesBinding
    private lateinit var coursesAdapter: CoursesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        coursesAdapter = CoursesAdapter(
            DetailCoursesClickListenerFactoryImpl(this),
            SubscribeCourseClickListenerFactoryImpl(this)
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCoursesBinding.inflate(inflater, container, false)
        return binding.root
    }

    @ExperimentalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        coursesViewModel.state.onEach {
            render(it)
        }.launchIn(lifecycleScope)
        coursesViewModel.intentChannel.offer(CourseEvent.CoursesFetch)
    }

    private fun initRecyclerView() {
        with(binding.listOfMoviesRecyclerView) {
            setHasFixedSize(true)
            adapter = coursesAdapter
            layoutManager = LinearLayoutManager(this@DetailCoursesFragment.requireContext())
        }
    }

    override fun onSubscribeCourseClicked(movie: Movie) {
        coursesViewModel.intentChannel.offer(CourseEvent.CourseSubscribeClicked(movie))
        Log.d("TAGGERR", "SUBSCRIBE")
    }

    override fun onDetailCourseClicked(movie: Movie) {
        Log.d("TAGGERR", "DETAIL")
        findNavController().navigate(
            R.id.action_coursesFragment_to_detailsCourseFragment,
            Bundle().apply { putInt(INTENT_MOVIE_KEY, movie.id) }
        )
//        coursesViewModel.intentChannel.offer(CourseEvent.CourseDetailClicked(movie))
    }

    private fun render(courseState: CourseState) {
        when (courseState) {
            is CourseState.DataListState -> showMoviesData(courseState)
            CourseState.Idle -> Unit
            CourseState.LoadingState -> showLoader()
            is CourseState.ErrorState -> showError()
            is CourseState.SingleDataState -> Unit
            is CourseState.CourseSubscribeClickedState -> movieClicked(courseState.movie)
            is CourseState.CourseDetailClickedState -> courseDetailClicked(courseState.movie)
        }
    }

    private fun courseDetailClicked(movie: Movie) {
        coursesViewModel.intentChannel.offer(CourseEvent.CoursesIdle)
        findNavController().navigate(
            R.id.action_coursesFragment_to_detailsCourseFragment,
            Bundle().apply { putInt(INTENT_MOVIE_KEY, movie.id) }
        )
    }

    private fun showMoviesData(courseState: CourseState.DataListState) {
        hideLoader()
        binding.listOfMoviesRecyclerView.visibility = VISIBLE
        coursesAdapter.setData(courseState.data)
    }

    private fun showLoader() {
        with(binding) {
            progressBar.visibility = VISIBLE
            errorMessageMovies.visibility = GONE
            listOfMoviesRecyclerView.visibility = GONE
        }
    }

    private fun hideLoader() {
        binding.progressBar.visibility = GONE
    }

    private fun showError() {
        hideLoader()
        binding.errorMessageMovies.visibility = VISIBLE
    }

    private fun movieClicked(movie: Movie) {
        showClickedMovieToast(movie).show()
        val instance = Calendar.getInstance()
//        instance.set(Calendar.HOUR, 9) // FYI: uncomment if needs to show alarm at 9 a.m.
        instance.set(Calendar.SECOND, instance.get(Calendar.SECOND) + 20)
        startAlarm(instance, movie)
    }

    private fun showClickedMovieToast(movie: Movie) =
        Toast.makeText(context, "Clicked ${movie.title}", Toast.LENGTH_LONG)

    private fun startAlarm(
        calendar: Calendar,
        movie: Movie
    ) {
        val alarmManager = requireActivity().getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val alarmNotificationIntent =
            Intent(requireContext(), AlarmNotificationReceiver::class.java).apply {
                putExtra(INTENT_MOVIE_KEY, movie.id)
            }
        val pendingIntent =
            PendingIntent.getBroadcast(
                requireContext(),
                0,
                alarmNotificationIntent,
                PendingIntent.FLAG_ONE_SHOT
            )

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmManager.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis,
                pendingIntent
            )
        } else {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
        }
    }
}
