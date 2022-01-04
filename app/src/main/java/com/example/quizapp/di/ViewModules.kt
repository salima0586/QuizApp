package com.example.quizapp.di

import com.example.quizapp.ui.fragments.quiz.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModules: Module = module {
    viewModel { MainViewModel(get()) }
}