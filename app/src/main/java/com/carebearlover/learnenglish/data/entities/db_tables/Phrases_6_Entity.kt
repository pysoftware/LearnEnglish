package com.carebearlover.learnenglish.data.entities.db_tables

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.carebearlover.learnenglish.extensions.today

@Entity(tableName = "phrases_6")
data class Phrases_6_Entity(
        @PrimaryKey(autoGenerate = true)
        val id: Int? = null,
        val word: String,
        val translate: String,
        val groupp: Int? = 1,
        val date: Long? = today()
)