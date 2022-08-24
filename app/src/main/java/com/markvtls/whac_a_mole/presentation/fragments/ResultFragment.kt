package com.markvtls.whac_a_mole.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.markvtls.whac_a_mole.databinding.FragmentResultBinding
import com.markvtls.whac_a_mole.presentation.GameplayViewModel
import com.markvtls.whac_a_mole.presentation.GameplayViewModelFactory


class ResultFragment : Fragment() {
    private val args: ResultFragmentArgs by navArgs()
    private val viewModel: GameplayViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "Activity is not created yet!"
        }
        ViewModelProvider(this, GameplayViewModelFactory(activity.application))[GameplayViewModel::class.java]
    }
    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val action = ResultFragmentDirections.actionResultFragmentToStartFragment()
                findNavController().navigate(action)
            }
        })
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.topScore.observe(viewLifecycleOwner) {
            binding.topScoreValue.text = it.toString()
        }
        binding.lastScoreValue.text = args.result.toString()

        binding.playAgain.setOnClickListener {
            val action = ResultFragmentDirections.actionResultFragmentToGameplayFragment()
            findNavController().navigate(action)
        }

        binding.toMenu.setOnClickListener {
            val action = ResultFragmentDirections.actionResultFragmentToStartFragment()
            findNavController().navigate(action)
        }
    }


}