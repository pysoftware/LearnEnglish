package com.carebearlover.learnenglish.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.carebearlover.learnenglish.data.entities.MyWordsEntity
import com.carebearlover.learnenglish.extensions.today

@Dao
interface MyWordsDao {

    @Query("UPDATE word SET `group` = 1, date = :date")
    fun resetAllProgress(date: Long? = today())

    @Query("DELETE FROM word")
    fun deleteAllWords()

    @Query("SELECT * FROM word")
    fun getAllWords(): LiveData<List<MyWordsEntity>>

    @Query("SELECT * FROM word where `group` = :group")
    fun getAllWordsByGroup(group: Int): LiveData<List<MyWordsEntity>>

    @Query("SELECT * FROM word WHERE date = :date")
    fun getAllTodayRepeatWords(date: Long): LiveData<List<MyWordsEntity>>

    // List fragment
    @Query("SELECT COUNT(*) FROM word WHERE `group` BETWEEN 1 AND 5")
    fun getCountStudiesWords(): LiveData<Int>

    @Query("SELECT COUNT(*) FROM word WHERE `group` = 6")
    fun getCountStudiedWords(): LiveData<Int>

    @Query("SELECT * FROM word WHERE `group` BETWEEN 1 AND 5")
    fun getAllStudiesWords(): LiveData<List<MyWordsEntity>>

    @Query("SELECT * FROM word WHERE `group` = 6")
    fun getAllStudiedWords(): LiveData<List<MyWordsEntity>>

    // DAILY REPEATING
    @Query("SELECT MIN(date) FROM word")
    fun getNearestDate(): LiveData<Long>

    @Query("SELECT COUNT(*) FROM word WHERE `date` = :date")
    fun getCountTodayRepeatWords(date: Long): LiveData<Int>

    @Query("SELECT COUNT(*) from word")
    fun getCountAllWords(): LiveData<Int>

    @Query("UPDATE word SET `group` = :group, date = :toDate")
    fun updateWordRepeatDate(toDate: Long, group: Int)

    @Query("UPDATE word SET `group` = :group, date = :toDate WHERE word = :word")
    fun updateWordRepeatDate(toDate: Long, word: String, group: Int)

    @Query("UPDATE word SET `group` = 1, date = :today WHERE date < :today")
    fun updateWordRepeatDate(today: Long = today())

    @Query("UPDATE word SET `group` = 1, date = :today WHERE word = :word")
    fun updateWordRepeatDate(today: Long = today(), word: String)

    @Query("SELECT `group` FROM word WHERE word = :word")
    fun getWordGroupByTitle(word: String): Int

    @Insert
    fun insertWord(word: MyWordsEntity)

    @Update
    fun updateWord(word: MyWordsEntity)

    @Delete
    fun deleteWord(word: MyWordsEntity)
}