package com.magere.learnenglish.data.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import com.magere.learnenglish.data.db.Database
import com.magere.learnenglish.data.entities.ExampleEntity
import com.magere.learnenglish.data.entities.WordsEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Repository(application: Application) {
    private val db = Database.getInstance(application)
    private val wordsDao = db!!.wordsDao()
    private val examplesDao = db!!.examplesDao()
    private var getAllWords: LiveData<List<WordsEntity>>? = wordsDao.getAllWords()
    private var getAllStudiesWords: LiveData<List<WordsEntity>> = wordsDao.getAllStudiesWords()
    private var getAllStudiedWords: LiveData<List<WordsEntity>> = wordsDao.getAllStudiedWords()
    private var getCountStudiesWords: LiveData<Int> = wordsDao.getCountStudiesWords()
    private var getCountStudiedWords: LiveData<Int> = wordsDao.getCountStudiedWords()
    private var getCountAllWords: LiveData<Int> = wordsDao.getCountAllWords()

    fun deleteWord(word: WordsEntity) = GlobalScope.launch(Dispatchers.IO) {
        wordsDao.deleteWord(word)
    }

    fun updateWord(word: WordsEntity) {
        GlobalScope.launch(Dispatchers.IO) {
            wordsDao.updateWord(word)
        }
    }

    // List fragment
    fun insertWord(word: WordsEntity) {
        GlobalScope.launch(Dispatchers.IO) {
            wordsDao.insertWord(word)
        }
    }

    fun getCountStudiesWords(): LiveData<Int> = getCountStudiesWords

    fun getAllStudiesWords(): LiveData<List<WordsEntity>> = getAllStudiesWords

    fun getAllStudiedWords(): LiveData<List<WordsEntity>> = getAllStudiedWords

    fun getCountStudiedWords(): LiveData<Int> = getCountStudiedWords

    // Repeat fragment

    fun getNearestDate(): LiveData<Long> = wordsDao.getNearestDate()

    fun getAllTodayRepeatWords(date: Long): LiveData<List<WordsEntity>> = wordsDao.getAllTodayRepeatWords(date)

    fun getCountTodayRepeatWords(date: Long): LiveData<Int> = wordsDao.getCountTodayRepeatWords(date)

    fun updateWordRepeatDate(toDate: Long, group: Int) {
        GlobalScope.launch(Dispatchers.IO) {
            wordsDao.updateWordRepeatDate(toDate = toDate, group = group)
        }
    }

    fun updateWordRepeatDate(toDate: Long, word: String, group: Int) {
        GlobalScope.launch(Dispatchers.IO) {
            wordsDao.updateWordRepeatDate(toDate = toDate, word = word, group = group)
        }
    }

    fun updateWordRepeatDate() {
        GlobalScope.launch(Dispatchers.IO) {
            wordsDao.updateWordRepeatDate()
        }
    }

    fun getAllWords(): LiveData<List<WordsEntity>>? = getAllWords

    fun getAllWordsByGroup(group: Int): LiveData<List<WordsEntity>>? = wordsDao.getAllWordsByGroup(group)

    fun getCountAllWords(): LiveData<Int> = getCountAllWords

    fun getWordGroupByTitle(word: String): Int = wordsDao.getWordGroupByTitle(word)

    // Examples

    fun deleteExample(example: ExampleEntity) = GlobalScope.launch(Dispatchers.IO) {
        examplesDao.deleteExample(example = example)
    }

    fun getExamplesByWord(word: String): LiveData<List<ExampleEntity>> =
            examplesDao.getExamplesByWord(word)

    fun insertExamples(examples: List<ExampleEntity>) = GlobalScope.launch(Dispatchers.IO) {
        examplesDao.insertExamples(examples)
    }

    fun insertExample(example: ExampleEntity) = GlobalScope.launch(Dispatchers.IO) {
        examplesDao.insertExample(example)
    }
}