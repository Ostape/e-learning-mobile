package com.robosh.basestartapplication.account

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.robosh.basestartapplication.R
import com.robosh.basestartapplication.databinding.FragmentAccountBinding


class AccountFragment : Fragment() {

    private lateinit var binding: FragmentAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        findNavController().navigate(R.id.action_accountFragment_to_loginFragment)
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
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton("Вийти", { dialog, which ->
                    // Continue with delete operation
                })
                .setNegativeButton("Відмінити", null)
                .setIcon(R.drawable.ic_log_out)
                .show()
        }
    }
}