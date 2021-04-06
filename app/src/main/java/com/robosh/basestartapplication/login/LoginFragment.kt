package com.robosh.basestartapplication.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.robosh.basestartapplication.R
import com.robosh.basestartapplication.application.SHARED_PREFS_FILE
import com.robosh.basestartapplication.application.USER_LOGGED_IN_TOKEN
import com.robosh.basestartapplication.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loginButton.setOnClickListener {
            val sharedPreferences =
                requireActivity().getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE)
            sharedPreferences.edit().putBoolean(USER_LOGGED_IN_TOKEN, true).apply()
            findNavController().navigate(R.id.action_loginFragment_to_accountFragment)
        }
    }
}