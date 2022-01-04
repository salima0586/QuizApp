package com.example.quizapp.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.quizapp.local.model.RoomResult

@Database(entities = [RoomResult::class],version = 1)
abstract class ResultDataBase: RoomDatabase() {

    abstract fun getResultDao(): ResultDao
}