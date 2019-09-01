package com.carebearlover.learnenglish.ui.screens.words_list_screen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.carebearlover.learnenglish.data.entities.MyExampleEntity
import com.carebearlover.learnenglish.data.entities.MyWordsEntity
import com.carebearlover.learnenglish.data.entities.db_tables.WordsEntity
import com.carebearlover.learnenglish.data.repositories.Repository
import com.carebearlover.learnenglish.data.repositories.MyRepository

class WordsViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = MyRepository(application)
    private val repo = Repository(application)

    fun insertWord(word: MyWordsEntity) {
        repository.insertWord(word)
    }

    fun resetAllProgress() = repository.resetAllProgress()

    fun deleteAllWords() = repository.deleteAllWords()

    fun getCountStudiesWords(): LiveData<Int> = repository.getCountStudiesWords()

    fun getAllStudiesWords(): LiveData<List<MyWordsEntity>> = repository.getAllStudiesWords()

    fun getAllWordsByCategory(category: String): LiveData<List<WordsEntity>> = repo.getAllWordsByCategory(category)

    fun getAllStudiedWords(): LiveData<List<MyWordsEntity>> = repository.getAllStudiedWords()

    fun getCountStudiedWords(): LiveData<Int> = repository.getCountStudiedWords()

    fun deleteWord(word: MyWordsEntity) = repository.deleteWord(word)

    fun updateWordRepeatDate(toDate: Long, word: String, group: Int) =
            repository.updateWordRepeatDate(toDate = toDate, word = word, group = group)

    fun insertExamples(myExamples: List<MyExampleEntity>) = repository.insertExamples(myExamples)


    fun insertExample(myExample: MyExampleEntity) = repository.insertExample(myExample)
}
