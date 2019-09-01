package com.carebearlover.learnenglish.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.carebearlover.learnenglish.extensions.today

@Entity(tableName = "word")
data class MyWordsEntity(
        @PrimaryKey(autoGenerate = true)
        var id: Int? = 0,
        var word: String = "",
        var translate: String = "",
        var group: Int? = 1,
        var date: Long? = today()
)