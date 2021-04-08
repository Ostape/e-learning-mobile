package com.robosh.basestartapplication.account.view

import android.app.AlertDialog
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
import com.robosh.basestartapplication.account.presenter.AccountViewModel
import com.robosh.basestartapplication.application.SHARED_PREFS_FILE
import com.robosh.basestartapplication.application.USER_LOGGED_IN_TOKEN
import com.robosh.basestartapplication.databinding.FragmentAccountBinding
import com.robosh.basestartapplication.model.User
import com.robosh.basestartapplication.model.account.AccountEvent
import com.robosh.basestartapplication.model.account.AccountState
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class AccountFragment : Fragment() {

    private val accountViewModel: AccountViewModel by viewModels()
    private lateinit var binding: FragmentAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPreferences =
            requireActivity().getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE)
        if (sharedPreferences.getBoolean(USER_LOGGED_IN_TOKEN, false).not()) {
            findNavController().navigate(R.id.action_accountFragment_to_loginFragment)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClickListeners()
        accountViewModel.state.onEach {
            render(it)
        }.launchIn(lifecycleScope)
        accountViewModel.intentChannel.offer(AccountEvent.Loading)
    }

    private fun render(accountState: AccountState) {
        when (accountState) {
            is AccountState.AccountData -> renderUserData(accountState.user)
        }
    }

    private fun renderUserData(user: User) {
        with(binding) {
            Picasso.get().load(user.avatarUrl).into(userAvatar)
            username.text = "${user.name} ${user.surname}"
            userEmail.text = user.email
        }
    }

    private fun initClickListeners() {
        binding.wishListButton.setOnClickListener {
            findNavController().navigate(R.id.action_accountFragment_to_wishListFragment)
        }
        binding.certificateId.setOnClickListener {
            findNavController().navigate(R.id.action_accountFragment_to_certificateFragment)
        }
        binding.logoutId.setOnClickListener {
            AlertDialog.Builder(context)
                .setTitle("Вийти з акаунту?")
                .setMessage("Ви впевненні що хочете вийти з аккаунту?")
                .setPositiveButton("Вийти") { _, _ ->
                    val sharedPreferences =
                        requireActivity().getSharedPreferences(
                            SHARED_PREFS_FILE,
                            Context.MODE_PRIVATE
                        )
                    sharedPreferences.edit().putBoolean(USER_LOGGED_IN_TOKEN, false).apply()
                    findNavController().navigate(R.id.action_accountFragment_to_loginFragment)
                }
                .setNegativeButton("Відмінити", null)
                .setIcon(R.drawable.ic_log_out)
                .show()
        }
    }
}