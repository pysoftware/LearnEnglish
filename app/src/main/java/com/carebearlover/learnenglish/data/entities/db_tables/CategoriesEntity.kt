package com.carebearlover.learnenglish.data.entities.db_tables

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class CategoriesEntity(
        @PrimaryKey(autoGenerate = true)
        val id: Int? = null,
        val category: String
)