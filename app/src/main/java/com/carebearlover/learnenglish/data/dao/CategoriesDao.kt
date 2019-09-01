package com.carebearlover.learnenglish.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.carebearlover.learnenglish.data.entities.db_tables.CategoriesEntity

@Dao
interface CategoriesDao {

    @Query("SELECT * FROM categories")
    fun getAllCategories(): LiveData<List<CategoriesEntity>>

}