package com.github.johnnymillergh.android.androidjetpackmvvmboilerplate.login.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.github.johnnymillergh.android.androidjetpackmvvmboilerplate.R
import com.github.johnnymillergh.android.androidjetpackmvvmboilerplate.common.setDebounceClickListener
import com.github.johnnymillergh.android.androidjetpackmvvmboilerplate.databinding.FragmentSecondBinding
import com.github.johnnymillergh.android.androidjetpackmvvmboilerplate.login.viewmodel.SecondFragmentVM
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_second.*
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
@FlowPreview
@AndroidEntryPoint
class SecondFragment : Fragment() {
    private val vm: SecondFragmentVM by viewModels()

    private var _binding: FragmentSecondBinding? = null

    /**
     * This property is only valid between onCreateView and onDestroyView.
     */
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListener()
        setObserver()
    }

    private fun setListener() {
        binding.buttonSecond.setDebounceClickListener(250L) {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    private fun setObserver() {
        lifecycleScope.launchWhenCreated {
            vm.userList.debounce(1000).collect {
                networkResponseTextView.text = it.toString()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
