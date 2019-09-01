package com.carebearlover.learnenglish.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.carebearlover.learnenglish.data.entities.MyExampleEntity

@Dao
interface ExampleDao {

    @Delete
    fun deleteExample(myExample: MyExampleEntity)

    @Update
    fun updateExample(myExample: MyExampleEntity)

    @Query("SELECT * FROM example WHERE `word` = :word")
    fun getExamplesByWord(word: String): LiveData<List<MyExampleEntity>>

    @Insert
    fun insertExamples(myExamples: List<MyExampleEntity>)

    @Insert
    fun insertExample(myExample: MyExampleEntity)

}