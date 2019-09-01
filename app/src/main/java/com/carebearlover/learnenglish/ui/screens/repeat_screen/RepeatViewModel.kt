package com.carebearlover.learnenglish.ui.screens.repeat_screen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.carebearlover.learnenglish.data.entities.MyExampleEntity
import com.carebearlover.learnenglish.data.entities.MyWordsEntity
import com.carebearlover.learnenglish.data.repositories.MyRepository

class RepeatViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = MyRepository(application)

    fun getExamplesByWord(word: String): LiveData<List<MyExampleEntity>> =
            repository.getExamplesByWord(word)

    fun getAllTodayRepeatWords(date: Long): LiveData<List<MyWordsEntity>> = repository.getAllTodayRepeatWords(date)

    fun getCountTodayRepeatWords(date: Long): LiveData<Int> = repository.getCountTodayRepeatWords(date)

    fun updateWordRepeatDate(toDate: Long, group: Int) =
            repository.updateWordRepeatDate(toDate = toDate, group = group)

    fun updateWordRepeatDate() =
            repository.updateWordRepeatDate()

    fun updateWordRepeatDate(word: String) =
            repository.updateWordRepeatDate(word)

    fun updateWordRepeatDate(toDate: Long, word: String, group: Int) =
            repository.updateWordRepeatDate(toDate = toDate, word = word, group = group)
}
