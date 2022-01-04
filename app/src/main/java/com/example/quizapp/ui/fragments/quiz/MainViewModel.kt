package com.example.quizapp.ui.fragments.quiz

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizapp.core.network.Resource
import com.example.quizapp.local.model.RoomResult
import com.example.quizapp.model.QuizResponse
import com.example.quizapp.ui.repository.Repository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository) : ViewModel() {

    fun getAllQuestions(
        amount: Int,
        category: Int,
        difficulty: String,
        type: String
    ): LiveData<Resource<QuizResponse>> {
        return repository.getAllQuestions(amount, category, difficulty, type)
    }

    fun saveQuestions(roomResult: RoomResult) = viewModelScope.launch {
        repository.upsert(roomResult)
    }

    fun getSavedQuestions() = repository.getSavedQuestions()

    fun deleteQuestions(roomResult: RoomResult) = viewModelScope.launch {
        repository.deleteResults(roomResult)
    }
}