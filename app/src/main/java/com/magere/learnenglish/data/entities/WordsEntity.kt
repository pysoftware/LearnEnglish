package com.magere.learnenglish.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.magere.learnenglish.extensions.today

@Entity(tableName = "words")
data class WordsEntity(
        @PrimaryKey(autoGenerate = true)
        var id: Int? = 0,
        var word: String = "",
        var translate: String = "",
        val example1: String? = "",
        val translateExample1: String? = "",
        val example2: String? = "",
        val translateExample2: String? = "",
        var group: Int? = 1,
        var date: Long? = today()
)