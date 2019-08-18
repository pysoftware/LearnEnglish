package com.magere.learnenglish.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.magere.learnenglish.data.dao.WordsDao
import com.magere.learnenglish.data.entities.WordsEntity

@Database(entities = [WordsEntity::class], version = 1, exportSchema = false)
abstract class Database : RoomDatabase() {

    abstract fun wordsDao(): WordsDao

    companion object {
        private const val DB_NAME = "words_db"
        private var instance: com.magere.learnenglish.data.db.Database? = null

        fun getInstance(context: Context): com.magere.learnenglish.data.db.Database? {
            if (instance == null) {
                synchronized(com.magere.learnenglish.data.db.Database::class) {
                    instance = Room.databaseBuilder(
                            context.applicationContext,
                            com.magere.learnenglish.data.db.Database::class.java, DB_NAME
                    ).fallbackToDestructiveMigration().build()
                }
            }
            return instance
        }

        fun destroyInstance() {
            instance = null
        }
    }

}