package com.robosh.basestartapplication.login.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.robosh.basestartapplication.R
import com.robosh.basestartapplication.application.SHARED_PREFS_FILE
import com.robosh.basestartapplication.application.USER_LOGGED_IN_TOKEN
import com.robosh.basestartapplication.databinding.FragmentLoginBinding
import com.robosh.basestartapplication.login.presenter.LoginViewModel
import com.robosh.basestartapplication.model.login.LoginEvent
import com.robosh.basestartapplication.model.login.LoginState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private val loginViewModel: LoginViewModel by viewModels()

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
        initClickListener()
        loginViewModel.state.onEach {
            render(it)
        }.launchIn(lifecycleScope)
    }

    private fun render(loginState: LoginState) {
        when (loginState) {
            is LoginState.LoginSuccess -> navigateToAccountScreen()
            is LoginState.LoginError -> Unit //todo show error message
            is LoginState.LoginIdle -> Unit
        }
    }

    private fun initClickListener() {
        binding.loginButton.setOnClickListener {
            loginViewModel.intentChannel.offer(
                LoginEvent.UserLoginClicked(
                    binding.emailInputEditText.toString(),
                    binding.passwordInputEditText.toString()
                )
            )
        }
    }

    private fun navigateToAccountScreen() {
        val sharedPreferences =
            requireActivity().getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE)
        sharedPreferences.edit().putBoolean(USER_LOGGED_IN_TOKEN, true).apply()
        findNavController().navigate(R.id.action_loginFragment_to_accountFragment)
    }
}