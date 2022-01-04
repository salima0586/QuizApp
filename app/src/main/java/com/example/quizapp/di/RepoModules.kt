package com.example.quizapp.di

import com.example.quizapp.ui.repository.Repository
import org.koin.core.module.Module
import org.koin.dsl.module

val repoModules: Module = module{
    single { Repository(get()) }
}
