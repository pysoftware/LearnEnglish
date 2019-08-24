package com.magere.learnenglish.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.magere.learnenglish.data.dao.ExampleDao
import com.magere.learnenglish.data.dao.WordsDao
import com.magere.learnenglish.data.entities.ExampleEntity
import com.magere.learnenglish.data.entities.WordsEntity

@Database(entities = [WordsEntity::class, ExampleEntity::class], version = 2, exportSchema = false)
abstract class Database : RoomDatabase() {

    abstract fun wordsDao(): WordsDao
    abstract fun examplesDao(): ExampleDao

    companion object {
        private const val DB_NAME = "words_db"
        private var instance: com.magere.learnenglish.data.db.Database? = null

        fun getInstance(context: Context): com.magere.learnenglish.data.db.Database? {
            if (instance == null) {
                synchronized(com.magere.learnenglish.data.db.Database::class) {
                    instance = Room.databaseBuilder(
                            context.applicationContext,
                            com.magere.learnenglish.data.db.Database::class.java, DB_NAME
                    )
                            .addMigrations(migrationAtoB())
                            .build()
                }
            }
            return instance
        }

        private fun migrationAtoB() = object : Migration(1,2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL(
                        "create table `examples` (`id` integer primary key, `word` TEXT, `example` TEXT, `translateExample` TEXT)"
                )
            }
        }
    }

}