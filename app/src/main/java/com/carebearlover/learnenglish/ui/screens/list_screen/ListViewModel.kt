package com.carebearlover.learnenglish.ui.screens.list_screen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.carebearlover.learnenglish.data.entities.ExampleEntity
import com.carebearlover.learnenglish.data.entities.WordsEntity
import com.carebearlover.learnenglish.data.repositories.Repository

class ListViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = Repository(application)

    fun insertWord(word: WordsEntity) {
        repository.insertWord(word)
    }

    fun resetAllProgress() = repository.resetAllProgress()

    fun deleteAllWords() = repository.deleteAllWords()

    fun getCountStudiesWords(): LiveData<Int> = repository.getCountStudiesWords()

    fun getAllStudiesWords(): LiveData<List<WordsEntity>> = repository.getAllStudiesWords()

    fun getAllStudiedWords(): LiveData<List<WordsEntity>> = repository.getAllStudiedWords()

    fun getCountStudiedWords(): LiveData<Int> = repository.getCountStudiedWords()

    fun deleteWord(word: WordsEntity) = repository.deleteWord(word)

    fun updateWordRepeatDate(toDate: Long, word: String, group: Int) =
            repository.updateWordRepeatDate(toDate = toDate, word = word, group = group)

    fun insertExamples(examples: List<ExampleEntity>) = repository.insertExamples(examples)


    fun insertExample(example: ExampleEntity) = repository.insertExample(example)
}
