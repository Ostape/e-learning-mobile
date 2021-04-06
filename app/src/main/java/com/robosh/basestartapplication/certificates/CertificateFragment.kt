package com.robosh.basestartapplication.certificates

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.robosh.basestartapplication.databinding.FragmentCertificateBinding
import com.robosh.basestartapplication.databinding.FragmentLoginBinding

class CertificateFragment : Fragment() {

    private lateinit var binding: FragmentCertificateBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCertificateBinding.inflate(inflater, container, false)
        return binding.root
    }
}