package com.carebearlover.learnenglish.data.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import com.carebearlover.learnenglish.data.db.MyDatabase
import com.carebearlover.learnenglish.data.entities.MyExampleEntity
import com.carebearlover.learnenglish.data.entities.MyWordsEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MyRepository(application: Application) {
    private val db = MyDatabase.getInstance(application)
    private val wordsDao = db!!.wordsDao()
    private val examplesDao = db!!.examplesDao()
    private var getAllWords: LiveData<List<MyWordsEntity>>? = wordsDao.getAllWords()
    private var getAllStudiesWords: LiveData<List<MyWordsEntity>> = wordsDao.getAllStudiesWords()
    private var getAllStudiedWords: LiveData<List<MyWordsEntity>> = wordsDao.getAllStudiedWords()
    private var getCountStudiesWords: LiveData<Int> = wordsDao.getCountStudiesWords()
    private var getCountStudiedWords: LiveData<Int> = wordsDao.getCountStudiedWords()
    private var getCountAllWords: LiveData<Int> = wordsDao.getCountAllWords()

    fun resetAllProgress() {
        GlobalScope.launch(Dispatchers.IO) {
            wordsDao.resetAllProgress()
        }
    }
    fun deleteAllWords() {
        GlobalScope.launch(Dispatchers.IO) {
            wordsDao.deleteAllWords()
        }
    }

    fun deleteWord(word: MyWordsEntity) = GlobalScope.launch(Dispatchers.IO) {
        wordsDao.deleteWord(word)
    }

    fun updateWord(word: MyWordsEntity) {
        GlobalScope.launch(Dispatchers.IO) {
            wordsDao.updateWord(word)
        }
    }

    // List fragment
    fun insertWord(word: MyWordsEntity) {
        GlobalScope.launch(Dispatchers.IO) {
            wordsDao.insertWord(word)
        }
    }

    fun getCountStudiesWords(): LiveData<Int> = getCountStudiesWords

    fun getAllStudiesWords(): LiveData<List<MyWordsEntity>> = getAllStudiesWords

    fun getAllStudiedWords(): LiveData<List<MyWordsEntity>> = getAllStudiedWords

    fun getCountStudiedWords(): LiveData<Int> = getCountStudiedWords

    // Repeat fragment

    fun getNearestDate(): LiveData<Long> = wordsDao.getNearestDate()

    fun getAllTodayRepeatWords(date: Long): LiveData<List<MyWordsEntity>> = wordsDao.getAllTodayRepeatWords(date)

    fun getCountTodayRepeatWords(date: Long): LiveData<Int> = wordsDao.getCountTodayRepeatWords(date)

    fun updateWordRepeatDate(toDate: Long, group: Int) {
        GlobalScope.launch(Dispatchers.IO) {
            wordsDao.updateWordRepeatDate(toDate = toDate, group = group)
        }
    }
    fun updateWordRepeatDate(word: String) {
        GlobalScope.launch(Dispatchers.IO) {
            wordsDao.updateWordRepeatDate(word = word)
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

    fun getAllWords(): LiveData<List<MyWordsEntity>>? = getAllWords

    fun getAllWordsByGroup(group: Int): LiveData<List<MyWordsEntity>>? = wordsDao.getAllWordsByGroup(group)

    fun getCountAllWords(): LiveData<Int> = getCountAllWords

    fun getWordGroupByTitle(word: String): Int = wordsDao.getWordGroupByTitle(word)

    // Examples

    fun deleteExample(myExample: MyExampleEntity) = GlobalScope.launch(Dispatchers.IO) {
        examplesDao.deleteExample(myExample = myExample)
    }

    fun getExamplesByWord(word: String): LiveData<List<MyExampleEntity>> =
            examplesDao.getExamplesByWord(word)

    fun insertExamples(myExamples: List<MyExampleEntity>) = GlobalScope.launch(Dispatchers.IO) {
        examplesDao.insertExamples(myExamples)
    }

    fun insertExample(myExample: MyExampleEntity) = GlobalScope.launch(Dispatchers.IO) {
        examplesDao.insertExample(myExample)
    }
}