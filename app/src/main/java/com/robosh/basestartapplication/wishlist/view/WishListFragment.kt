package com.robosh.basestartapplication.wishlist.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.robosh.basestartapplication.courses.view.CoursesAdapter
import com.robosh.basestartapplication.courses.view.detail.DetailCoursesClickCallback
import com.robosh.basestartapplication.courses.view.detail.DetailCoursesClickListenerFactoryImpl
import com.robosh.basestartapplication.courses.view.subscribe.SubscribeCourseClickCallback
import com.robosh.basestartapplication.courses.view.subscribe.SubscribeCourseClickListenerFactoryImpl
import com.robosh.basestartapplication.databinding.FragmentWishlistBinding
import com.robosh.basestartapplication.model.Course
import com.robosh.basestartapplication.net.data.registeredUser

class WishListFragment : Fragment(), DetailCoursesClickCallback, SubscribeCourseClickCallback {

    private lateinit var binding: FragmentWishlistBinding
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
        binding = FragmentWishlistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        coursesAdapter.setData(registeredUser.wishListCourses ?: emptyList())
    }

    private fun initRecyclerView() {
        with(binding.wishListRecyclerView) {
            setHasFixedSize(true)
            adapter = coursesAdapter
            layoutManager =
                androidx.recyclerview.widget.LinearLayoutManager(this@WishListFragment.requireContext())
        }
    }

    override fun onDetailCourseClicked(movie: Course) {

    }

    override fun onSubscribeCourseClicked(course: Course) {

    }
}