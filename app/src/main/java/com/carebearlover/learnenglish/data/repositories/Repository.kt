package com.carebearlover.learnenglish.data.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import com.carebearlover.learnenglish.data.db.Database
import com.carebearlover.learnenglish.data.entities.db_tables.CategoriesEntity
import com.carebearlover.learnenglish.data.entities.db_tables.NumbersEntity
import com.carebearlover.learnenglish.data.entities.db_tables.WordsEntity
import com.huma.room_for_asset.RoomAsset
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class Repository(application: Application) {
    private val database = RoomAsset.databaseBuilder(
            application.applicationContext,
            Database::class.java,
            "words.db"
    ).build()
    private val categoriesDao = database.categoriesDao()
    private val wordsDao = database.wordsDao()

    fun getAllCategories(): LiveData<List<CategoriesEntity>> {
        return categoriesDao.getAllCategories()
    }
    fun getAllWordsByCategory(category: String): LiveData<List<WordsEntity>> {
        return wordsDao.getAllWordsByCategory(category)
    }

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
}