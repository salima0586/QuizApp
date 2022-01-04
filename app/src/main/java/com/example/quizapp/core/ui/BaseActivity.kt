package com.example.quizapp.core.ui

import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity <VB: ViewBinding>(): AppCompatActivity() {

    protected lateinit var binding:VB
    protected abstract fun inflateBinding(from: LayoutInflater):VB


    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        binding = inflateBinding(LayoutInflater.from(this))
        setContentView(binding.root)

        setupUI()
        setupLiveData()
    }

    abstract fun setupLiveData()

    abstract fun setupUI()
}
