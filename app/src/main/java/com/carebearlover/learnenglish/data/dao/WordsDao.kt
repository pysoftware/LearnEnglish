package com.carebearlover.learnenglish.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.carebearlover.learnenglish.data.entities.db_tables.CategoriesEntity
import com.carebearlover.learnenglish.data.entities.db_tables.NumbersEntity
import com.carebearlover.learnenglish.data.entities.db_tables.WordsEntity

@Dao
interface WordsDao {

    @Query("SELECT * FROM words WHERE category = :category")
    fun getAllWordsByCategory(category: String): LiveData<List<WordsEntity>>

}