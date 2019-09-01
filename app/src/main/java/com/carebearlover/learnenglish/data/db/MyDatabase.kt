package com.carebearlover.learnenglish.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.carebearlover.learnenglish.data.dao.ExampleDao
import com.carebearlover.learnenglish.data.dao.MyWordsDao
import com.carebearlover.learnenglish.data.entities.MyExampleEntity
import com.carebearlover.learnenglish.data.entities.MyWordsEntity

@Database(entities = [MyWordsEntity::class, MyExampleEntity::class], version = 4, exportSchema = false)
abstract class MyDatabase : RoomDatabase() {

    abstract fun wordsDao(): MyWordsDao
    abstract fun examplesDao(): ExampleDao

    companion object {
        private const val DB_NAME = "words_db"
        private var instance: MyDatabase? = null

        fun getInstance(context: Context): MyDatabase? {
            if (instance == null) {
                synchronized(MyDatabase::class) {
                    instance = Room.databaseBuilder(
                            context.applicationContext,
                            MyDatabase::class.java, DB_NAME
                    )
                            .fallbackToDestructiveMigration()
                            .addMigrations(migrationAtoB(), migrationAAtoBB())
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
        private fun migrationAAtoBB() = object : Migration(2,3) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL(
                        "create table `words` (`id` integer primary key autoincrement, `word` TEXT, `translate` TEXT, `groupp` integer, `date` integer)"
                )
            }
        }
    }

}