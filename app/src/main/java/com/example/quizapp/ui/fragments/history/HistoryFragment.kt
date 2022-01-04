package com.example.quizapp.ui.fragments.history

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quizapp.core.ui.BaseFragment
import com.example.quizapp.databinding.FragmentHistoryBinding
import com.example.quizapp.local.model.RoomResult
import com.example.quizapp.ui.fragments.history.adaprer.QuestionAdapter
import com.example.quizapp.ui.fragments.quiz.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class HistoryFragment : BaseFragment<FragmentHistoryBinding>(FragmentHistoryBinding::inflate) {

    private val viewModel: MainViewModel by viewModel()
    private lateinit var questionAdapter: QuestionAdapter
    private var list: ArrayList<RoomResult> = arrayListOf()
    override fun setupLiveData() {
        viewModel.getSavedQuestions().observe(viewLifecycleOwner, {
            list = it as ArrayList<RoomResult>
            questionAdapter.addQuestions(list)
        })
    }

    override fun setupUI() {
        questionAdapter = QuestionAdapter(list)
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = questionAdapter
        }
    }
}