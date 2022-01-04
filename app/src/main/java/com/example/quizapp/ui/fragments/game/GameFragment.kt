package com.example.quizapp.ui.fragments.game

import android.os.Bundle
import android.view.View
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quizapp.R
import com.example.quizapp.core.network.Status
import com.example.quizapp.core.ui.BaseFragment
import com.example.quizapp.databinding.FragmentGameBinding
import com.example.quizapp.model.QuizResponse
import com.example.quizapp.ui.fragments.game.adapter.QuizAdapter
import com.example.quizapp.ui.fragments.quiz.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class GameFragment : BaseFragment<FragmentGameBinding>(FragmentGameBinding:: inflate) {

    private val viewModel: MainViewModel by viewModel()

    private var category: Int = 0
    private var amount: Int = 0
    private var difficulty: String = ""
    private var equalPosition: Int = 0
    private var correctQuestions: Int = 0
    private val quizAdapter: QuizAdapter by lazy { QuizAdapter(this::clickListener) }

    override fun setupLiveData() {
        category = arguments?.getInt("int")!!
        difficulty = arguments?.getString("str").toString()
        amount = arguments?.getInt("amo")!!

        viewModel.getAllQuestions(amount, category, difficulty, "multiple")
            .observe(viewLifecycleOwner, {
                when (it.status) {
                    Status.SUCCESS -> {
                        if (it.data?.results?.size != null) {
                            equalPosition = it.data.results.size
                        }
                        binding.progressBar.visibility = View.GONE
                        binding.tvHeaderCategory.text =
                            it.data?.results?.get(0)?.category.toString()
                        quizAdapter.addQuestions(it.data?.results as ArrayList<QuizResponse.Result>)
                    }
                    Status.ERROR -> {
                        binding.progressBar.visibility = View.GONE
                        makeText(
                            requireContext(),
                            it.message.toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    Status.LOADING -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                }

            })

        binding.recyclerView.isNestedScrollingEnabled = false;
        binding.recyclerView.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = quizAdapter
        }
        binding.recyclerView.setOnTouchListener { v, event -> true }

    }

    private fun clickListener(item: QuizResponse.Result, position: Int, isCorrect: Boolean) {

        binding.recyclerView.smoothScrollToPosition(position + 1)
        if (isCorrect) {
            correctQuestions++
            Toast.makeText(requireContext(), "correct", Toast.LENGTH_SHORT).show()
        }
        if (equalPosition == position + 1) {
            Toast.makeText(requireContext(), "finish", Toast.LENGTH_SHORT).show()
            val bundle = Bundle()
            bundle.putInt("correct", correctQuestions)
            bundle.putInt("amount", amount)
            bundle.putString("difficulty", difficulty)
            findNavController().navigate(R.id.resultFragment, bundle)
        }
    }

    override fun setupUI() {
        binding.ivBack.setOnClickListener{
            findNavController().navigate(R.id.quizFragment)
        }
    }
}