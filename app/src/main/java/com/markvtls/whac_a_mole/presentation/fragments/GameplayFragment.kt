package com.markvtls.whac_a_mole.presentation.fragments

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.markvtls.whac_a_mole.databinding.FragmentGameplayBinding
import com.markvtls.whac_a_mole.presentation.GameplayViewModel
import com.markvtls.whac_a_mole.presentation.GameplayViewModelFactory

class GameplayFragment : Fragment() {



    private val viewModel: GameplayViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "Activity is not created yet!"
        }
        ViewModelProvider(this, GameplayViewModelFactory(activity.application))[GameplayViewModel::class.java]
    }
    private var _binding: FragmentGameplayBinding? = null
    private val binding get() = _binding!!
    private var score = MutableLiveData(0)
    private var currentScore = 0
    private var activeHoles = mutableSetOf(1,2,3,4,5,6,7,8,9)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val action = GameplayFragmentDirections.actionGameplayFragmentToStartFragment()
                findNavController().navigate(action)
                onDestroy()
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameplayBinding.inflate(inflater, container, false)
        randomHoles()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        implementGameplay()
        countDownTimer.start()
        moleTimer.start()
        score.observe(viewLifecycleOwner) {
            binding.counter.text = it.toString()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        moleTimer.cancel()
        countDownTimer.cancel()
    }
    private val countDownTimer = object :CountDownTimer(30000,1000) {
        override fun onTick(millisUntilFinished: Long) {
            val secondsUntilFinished = millisUntilFinished / 1000
            if (secondsUntilFinished > 9) {
                binding.timer.text = "00:$secondsUntilFinished"
            } else {
                binding.timer.text = "00:0$secondsUntilFinished"
            }

            }

        override fun onFinish() {
                viewModel.saveNewTopScore(currentScore)
                val action = GameplayFragmentDirections.actionGameplayFragmentToResultFragment(currentScore)
                findNavController().navigate(action)
            }

    }

    private val moleTimer = object :CountDownTimer(31000, 500) {
        override fun onTick(millisUntilFinished: Long) {
            val random = activeHoles.random()
            hideMoleInHole()
            randomMoles(random)
        }

        override fun onFinish() {
            println()
        }

    }
    private fun hideMoleInHole() {
        binding.apply {
            mole1.visibility = View.INVISIBLE
            mole2.visibility = View.INVISIBLE
            mole3.visibility = View.INVISIBLE
            mole4.visibility = View.INVISIBLE
            mole5.visibility = View.INVISIBLE
            mole6.visibility = View.INVISIBLE
            mole7.visibility = View.INVISIBLE
            mole8.visibility = View.INVISIBLE
            mole9.visibility = View.INVISIBLE
        }
    }

    private fun randomMoles(number: Int) {
        when(number) {
            1 -> binding.mole1.visibility = View.VISIBLE
            2 -> binding.mole2.visibility = View.VISIBLE
            3 -> binding.mole3.visibility = View.VISIBLE
            4 -> binding.mole4.visibility = View.VISIBLE
            5 -> binding.mole5.visibility = View.VISIBLE
            6 -> binding.mole6.visibility = View.VISIBLE
            7 -> binding.mole7.visibility = View.VISIBLE
            8 -> binding.mole8.visibility = View.VISIBLE
            9 -> binding.mole9.visibility = View.VISIBLE
        }
    }
    private fun randomHoles() {
        when((6..9).random()) {
            6 -> {
                binding.hole2.visibility = View.INVISIBLE
                binding.hole4.visibility = View.INVISIBLE
                binding.hole8.visibility = View.INVISIBLE
                activeHoles = mutableSetOf(1,3,5,6,7,9)
            }
            7 -> {
                binding.hole3.visibility = View.INVISIBLE
                binding.hole7.visibility = View.INVISIBLE
                activeHoles = mutableSetOf(1,2,4,5,6,8,9)
            }
            8 -> {
                binding.hole5.visibility = View.INVISIBLE
                activeHoles = mutableSetOf(1,2,3,4,6,7,8,9)
            }
        }
    }

    private fun implementGameplay() {
        binding.apply {
            mole1.setOnClickListener {
                if (it.isVisible) {
                    it.visibility = View.INVISIBLE
                    currentScore++
                    score.postValue(currentScore)
                }
            }

            mole2.setOnClickListener {
                if (it.isVisible) {
                    it.visibility = View.INVISIBLE
                    currentScore++
                    score.postValue(currentScore)
                }
            }

            mole3.setOnClickListener {
                if (it.isVisible) {
                    it.visibility = View.INVISIBLE
                    currentScore++
                    score.postValue(currentScore)
                }
            }

            mole4.setOnClickListener {
                if (it.isVisible) {
                    it.visibility = View.INVISIBLE
                    currentScore++
                    score.postValue(currentScore)
                }
            }

            mole5.setOnClickListener {
                if (it.isVisible) {
                    it.visibility = View.INVISIBLE
                    currentScore++
                    score.postValue(currentScore)
                }
            }

            mole6.setOnClickListener {
                if (it.isVisible) {
                    it.visibility = View.INVISIBLE
                    currentScore++
                    score.postValue(currentScore)
                }
            }

            mole7.setOnClickListener {
                if (it.isVisible) {
                    it.visibility = View.INVISIBLE
                    currentScore++
                    score.postValue(currentScore)
                }
            }

            mole8.setOnClickListener {
                if (it.isVisible) {
                    it.visibility = View.INVISIBLE
                    currentScore++
                    score.postValue(currentScore)
                }
            }

            mole9.setOnClickListener {
                if (it.isVisible) {
                    it.visibility = View.INVISIBLE
                    currentScore++
                    score.postValue(currentScore)
                }
            }
        }
    }
}