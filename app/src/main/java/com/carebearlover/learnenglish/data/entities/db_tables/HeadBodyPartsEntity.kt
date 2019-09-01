package com.carebearlover.learnenglish.data.entities.db_tables

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.carebearlover.learnenglish.extensions.today

@Entity(tableName = "head_body_parts")
data class HeadBodyPartsEntity(
        @PrimaryKey(autoGenerate = true)
        val id: Int? = null,
        val word: String,
        val translate: String,
        val groupp: Int? = 1,
        val date: Long? = today()
)