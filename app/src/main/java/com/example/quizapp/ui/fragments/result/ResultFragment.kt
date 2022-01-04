package com.example.quizapp.ui.fragments.result

import android.util.Log
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.quizapp.R
import com.example.quizapp.core.ui.BaseFragment
import com.example.quizapp.databinding.FragmentResultBinding
import com.example.quizapp.local.model.RoomResult
import com.example.quizapp.ui.fragments.quiz.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class ResultFragment : BaseFragment<FragmentResultBinding>(FragmentResultBinding::inflate) {

    private val viewModel: MainViewModel by viewModel()

    private var amount: Int = 0
    private var correct: Int = 0
    private var difficulty: String = ""
    override fun setupLiveData() {}

    override fun setupUI() {
        if (arguments != null) {
            amount = requireArguments().getInt("amount")
            correct = requireArguments().getInt("correct")
            difficulty = requireArguments().getString("difficulty").toString()
            Log.d("tag", "1:$amount 2:$correct 3:$difficulty")
        }

        val result = correct * 100 / amount
        if (result >= 80) {
            binding.ivMark.visibility = View.VISIBLE
        } else {
            binding.ivMark.visibility = View.GONE
        }
        binding.tvDifficultyResult.text = difficulty
        binding.tvCorrectAnswerResult.text = correct.toString()
        binding.tvResultMixed.text = result.toString().plus("%")


        binding.btnFinish.setOnClickListener{
            val roomResult = RoomResult(null,correct,amount,difficulty)
            viewModel.saveQuestions(roomResult)
            findNavController().navigate(R.id.quizFragment)
        }
    }
    }