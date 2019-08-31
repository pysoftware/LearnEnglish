package com.carebearlover.learnenglish.ui.screens.repeat_screen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.carebearlover.learnenglish.data.entities.ExampleEntity
import com.carebearlover.learnenglish.data.entities.WordsEntity
import com.carebearlover.learnenglish.data.repositories.Repository

class RepeatViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = Repository(application)

    fun getExamplesByWord(word: String): LiveData<List<ExampleEntity>> =
            repository.getExamplesByWord(word)

    fun getAllTodayRepeatWords(date: Long): LiveData<List<WordsEntity>>
            = repository.getAllTodayRepeatWords(date)

    fun getCountTodayRepeatWords(date: Long): LiveData<Int>
            = repository.getCountTodayRepeatWords(date)

    fun getNearestDate(): LiveData<Long> = repository.getNearestDate()

    fun updateWordRepeatDate(toDate: Long, group: Int) =
            repository.updateWordRepeatDate(toDate = toDate, group = group)

    fun updateWordRepeatDate() =
            repository.updateWordRepeatDate()

    fun updateWordRepeatDate(word: String) =
            repository.updateWordRepeatDate(word)

    fun updateWordRepeatDate(toDate: Long, word: String, group: Int) =
            repository.updateWordRepeatDate(toDate = toDate, word = word, group = group)
}
