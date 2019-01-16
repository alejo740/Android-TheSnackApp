package com.cobos.edwin.thesnackapp.db

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import java.util.concurrent.Executors

private const val DB_NAME = "snacks-db"

@Database(entities = arrayOf(Snack::class), version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun snackDao(): SnackDao

    companion object {
        @Volatile
        private lateinit var INSTANCE: AppDatabase

        fun getInstance(): AppDatabase = INSTANCE

        fun init(context: Context) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                DB_NAME
            )
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        Executors.newSingleThreadExecutor().execute {
                            Runnable {
                                getInstance().snackDao().insertSnacks(PRE_DATA)
                            }.run()
                        }
                    }
                })
                .build()
            INSTANCE.query("select 1", null)
        }

        var PRE_DATA = listOf(
            Snack(name = "French fries", isVeggie = true),
            Snack(name = "Veggieburger", isVeggie = true),
            Snack(name = "Carrots", isVeggie = true),
            Snack(name = "Apple", isVeggie = true),
            Snack(name = "Banana", isVeggie = true),
            Snack(name = "Milkshake", isVeggie = true),
            Snack(name = "Cheeseburger", isVeggie = false),
            Snack(name = "Hamburger", isVeggie = false),
            Snack(name = "Hot dog", isVeggie = false)
        )

    }


}