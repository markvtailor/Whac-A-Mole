package com.markvtls.whac_a_mole.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.markvtls.whac_a_mole.databinding.FragmentStartBinding
import com.markvtls.whac_a_mole.presentation.GameplayViewModel
import com.markvtls.whac_a_mole.presentation.GameplayViewModelFactory


class StartFragment : Fragment() {
    private val viewModel: GameplayViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "Activity is not created yet!"
        }
        ViewModelProvider(this, GameplayViewModelFactory(activity.application))[GameplayViewModel::class.java]
    }
    private var _binding: FragmentStartBinding? = null
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.topScore.observe(viewLifecycleOwner) {
            binding.topScoreValue.text = it.toString()
        }

        binding.play.setOnClickListener {
            val action = StartFragmentDirections.actionStartFragmentToGameplayFragment()
            findNavController().navigate(action)
        }

        binding.rules.setOnClickListener {
            showRules()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun showRules() {
        val dialogFragment = RulesFragment()
        dialogFragment.show(childFragmentManager, dialogFragment.tag)
    }
}