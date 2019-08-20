package com.magere.learnenglish.ui.screens.repeat_screen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel;
import com.magere.learnenglish.data.entities.WordsEntity
import com.magere.learnenglish.data.repositories.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RepeatViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = Repository(application)

    fun getAllTodayRepeatWords(date: Long): LiveData<List<WordsEntity>>
            = repository.getAllTodayRepeatWords(date)

    fun getCountTodayRepeatWords(date: Long): LiveData<Int>
            = repository.getCountTodayRepeatWords(date)

    fun getNearestDate(): LiveData<Long> = repository.getNearestDate()

    fun updateWordRepeatDate(toDate: Long, group: Int) =
            repository.updateWordRepeatDate(toDate = toDate, group = group)

    fun updateWordRepeatDate() =
            repository.updateWordRepeatDate()

    fun updateWordRepeatDate(toDate: Long, word: String, group: Int) =
            repository.updateWordRepeatDate(toDate = toDate, word = word, group = group)
}
