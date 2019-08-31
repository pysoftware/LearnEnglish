package com.carebearlover.learnenglish.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.carebearlover.learnenglish.data.entities.ExampleEntity

@Dao
interface ExampleDao {

    @Delete
    fun deleteExample(example: ExampleEntity)

    @Update
    fun updateExample(example: ExampleEntity)

    @Query("SELECT * FROM examples WHERE `word` = :word")
    fun getExamplesByWord(word: String): LiveData<List<ExampleEntity>>

    @Insert
    fun insertExamples(examples: List<ExampleEntity>)

    @Insert
    fun insertExample(example: ExampleEntity)

}