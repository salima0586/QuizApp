package com.example.quizapp.ui.repository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.quizapp.ui.fragments.quiz.MainViewModel

class QuizViewModelProviderFactory (

    private val repository: Repository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repository)as T
    }
    }
