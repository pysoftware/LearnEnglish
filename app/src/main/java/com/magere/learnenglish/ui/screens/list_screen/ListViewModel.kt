package com.magere.learnenglish.ui.screens.list_screen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel;
import com.magere.learnenglish.data.entities.WordsEntity
import com.magere.learnenglish.data.repositories.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ListViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = Repository(application)

    fun insertWord(word: WordsEntity) {
        repository.insertWord(word)
    }

    fun getCountStudiesWords(): LiveData<Int> = repository.getCountStudiesWords()

    fun getAllStudiesWords(): LiveData<List<WordsEntity>> = repository.getAllStudiesWords()

    fun getAllStudiedWords(): LiveData<List<WordsEntity>> = repository.getAllStudiedWords()

    fun getCountStudiedWords(): LiveData<Int> = repository.getCountStudiedWords()

    fun deleteWord(word: WordsEntity) = repository.deleteWord(word)

    fun updateWordRepeatDate(toDate: Long, word: String, group: Int) =
            repository.updateWordRepeatDate(toDate = toDate, word = word, group = group)
}
