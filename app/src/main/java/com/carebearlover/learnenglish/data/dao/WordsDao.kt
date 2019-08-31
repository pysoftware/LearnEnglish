package com.carebearlover.learnenglish.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.carebearlover.learnenglish.data.entities.WordsEntity
import com.carebearlover.learnenglish.extensions.today

@Dao
interface WordsDao {

    @Query("UPDATE words SET `group` = 1, date = :date")
    fun resetAllProgress(date: Long? = today())

    @Query("DELETE FROM words")
    fun deleteAllWords()

    @Query("SELECT * FROM words")
    fun getAllWords(): LiveData<List<WordsEntity>>

    @Query("SELECT * FROM words where `group` = :group")
    fun getAllWordsByGroup(group: Int): LiveData<List<WordsEntity>>

    @Query("SELECT * FROM words WHERE date = :date")
    fun getAllTodayRepeatWords(date: Long): LiveData<List<WordsEntity>>

    // List fragment
    @Query("SELECT COUNT(*) FROM words WHERE `group` BETWEEN 1 AND 5")
    fun getCountStudiesWords(): LiveData<Int>

    @Query("SELECT COUNT(*) FROM words WHERE `group` = 6")
    fun getCountStudiedWords(): LiveData<Int>

    @Query("SELECT * FROM words WHERE `group` BETWEEN 1 AND 5")
    fun getAllStudiesWords(): LiveData<List<WordsEntity>>

    @Query("SELECT * FROM words WHERE `group` = 6")
    fun getAllStudiedWords(): LiveData<List<WordsEntity>>

    // DAILY REPEATING
    @Query("SELECT MIN(date) FROM words")
    fun getNearestDate(): LiveData<Long>

    @Query("SELECT COUNT(*) FROM words WHERE `date` = :date")
    fun getCountTodayRepeatWords(date: Long): LiveData<Int>

    @Query("SELECT COUNT(*) from words")
    fun getCountAllWords(): LiveData<Int>

    @Query("UPDATE words SET `group` = :group, date = :toDate")
    fun updateWordRepeatDate(toDate: Long, group: Int)

    @Query("UPDATE words SET `group` = :group, date = :toDate WHERE word = :word")
    fun updateWordRepeatDate(toDate: Long, word: String, group: Int)

    @Query("UPDATE words SET `group` = 1, date = :today WHERE date < :today")
    fun updateWordRepeatDate(today: Long = today())

    @Query("UPDATE words SET `group` = 1, date = :today WHERE word = :word")
    fun updateWordRepeatDate(today: Long = today(), word: String)

    @Query("SELECT `group` FROM words WHERE word = :word")
    fun getWordGroupByTitle(word: String): Int

    @Insert
    fun insertWord(word: WordsEntity)

    @Update
    fun updateWord(word: WordsEntity)

    @Delete
    fun deleteWord(word: WordsEntity)
}