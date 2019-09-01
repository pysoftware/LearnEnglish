package com.carebearlover.learnenglish.data.entities.db_tables

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.carebearlover.learnenglish.extensions.today

@Entity(tableName = "examples")
data class ExamplesEntity(
        @PrimaryKey(autoGenerate = true)
        val id: Int? = null,
        val word: String,
        val example: String,
        val translateExample: String
)