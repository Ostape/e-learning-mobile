package com.robosh.basestartapplication.login.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.robosh.basestartapplication.R
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
            is LoginState.LoginError -> showErrorMessage()
            is LoginState.LoginIdle -> Unit
        }
    }

    private fun showErrorMessage() {
        binding.errorBanner.visibility = VISIBLE
        binding.errorBanner.postDelayed({
            binding.errorBanner.visibility = GONE
        }, 4000)
        loginViewModel.intentChannel.offer(LoginEvent.UpdateEvent)
    }

    private fun initClickListener() {
        binding.loginButton.setOnClickListener {
            loginViewModel.intentChannel.offer(
                LoginEvent.UserLoginClicked(
                    binding.usernameInputEditText.text.toString(),
                    binding.passwordInputEditText.text.toString()
                )
            )
        }
    }

    private fun navigateToAccountScreen() {
        findNavController().navigate(R.id.action_loginFragment_to_accountFragment)
    }
}