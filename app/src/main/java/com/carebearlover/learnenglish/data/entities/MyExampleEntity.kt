package com.carebearlover.learnenglish.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "example")
class MyExampleEntity (
        @PrimaryKey(autoGenerate = true)
        var id: Int? = null,
        var word: String? = "",
        var example: String? = "",
        var translateExample: String? = ""
)
