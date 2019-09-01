package com.carebearlover.learnenglish.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.carebearlover.learnenglish.data.dao.CategoriesDao
import com.carebearlover.learnenglish.data.dao.WordsDao
import com.carebearlover.learnenglish.data.entities.db_tables.*

@Database(entities = [
    CategoriesEntity::class,
    ColoursEntity::class,
    ExamplesEntity::class,
    HeadBodyPartsEntity::class,
    LittleBodyPartsEntity::class,
    MainBodyPartsEntity::class,
    NumbersEntity::class,
    Phrases_1_Entity::class,
    Phrases_2_Entity::class,
    Phrases_3_Entity::class,
    Phrases_4_Entity::class,
    Phrases_5_Entity::class,
    Phrases_6_Entity::class,
    WeatherEntity::class,
    WordsEntity::class
], version = 2, exportSchema = false)
abstract class Database : RoomDatabase() {
    abstract fun categoriesDao(): CategoriesDao
    abstract fun wordsDao(): WordsDao
}