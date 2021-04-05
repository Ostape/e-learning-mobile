package com.robosh.basestartapplication.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.robosh.basestartapplication.databinding.FragmentLoginBinding
import com.robosh.basestartapplication.player.view.YouTubePlayerActivity

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
        binding.registerButton.setOnClickListener {
//            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
            startActivity(Intent(context, YouTubePlayerActivity::class.java))
        }
    }

}