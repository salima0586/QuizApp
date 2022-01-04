package com.example.quizapp.ui.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.quizapp.core.network.Resource
import com.example.quizapp.local.ResultDataBase
import com.example.quizapp.local.model.RoomResult
import com.example.quizapp.model.QuizResponse
import com.example.quizapp.remote.QuizApi
import com.example.quizapp.remote.RetrofitClient
import kotlinx.coroutines.Dispatchers

class Repository(val db: ResultDataBase) {

    private var quizApi: QuizApi = RetrofitClient.create()

    fun getAllQuestions(amount:Int,category:Int,difficulty:String,type:String): LiveData<Resource<QuizResponse>> = liveData(
        Dispatchers.IO) {

        emit(Resource.loading(null))

        val response = quizApi.getQuestions(amount, category, difficulty,type)

        emit(
            if (response.isSuccessful)
                Resource.success(response.body(), response.code())
            else
                Resource.error(response.message(), response.body(), response.code())
        )
    }
    suspend fun upsert(roomResult: RoomResult) = db.getResultDao().upsert(roomResult)

    fun getSavedQuestions() = db.getResultDao().getAllResults()

    suspend fun deleteResults(roomResult: RoomResult) = db.getResultDao().deleteResult(roomResult)


}