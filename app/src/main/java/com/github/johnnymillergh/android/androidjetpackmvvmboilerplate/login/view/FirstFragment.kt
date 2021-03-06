package com.github.johnnymillergh.android.androidjetpackmvvmboilerplate.login.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.github.johnnymillergh.android.androidjetpackmvvmboilerplate.R
import com.github.johnnymillergh.android.androidjetpackmvvmboilerplate.common.setDebounceClickListener
import com.github.johnnymillergh.android.androidjetpackmvvmboilerplate.databinding.FragmentFirstBinding
import com.github.johnnymillergh.android.androidjetpackmvvmboilerplate.login.viewmodel.FirstFragmentVM
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class FirstFragment : Fragment() {
    private val vm: FirstFragmentVM by viewModels()

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        binding.vm = vm
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setDebounceClickListener(250L) {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
