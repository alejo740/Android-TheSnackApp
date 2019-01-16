package com.cobos.edwin.thesnackapp.api.services

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import android.util.Log
import dagger.Module
import dagger.Provides
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module
class AppDatabaseModule constructor(context: Context) {

    private lateinit var localDatabase: AppDatabase

    init {
        localDatabase = Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            AppDatabase.DB_NAME
        )
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    Executors.newSingleThreadExecutor().execute {
                        Log.i("ROOM", "POPULATE DATA")
                        localDatabase.snackDao().insertSnacks(AppDatabase.PRE_DATA)
                    }
                }
            })
            .build()
        localDatabase.query("select 1", null)
    }

    @Singleton
    @Provides
    fun provideRoomDatabase(): AppDatabase {
        return localDatabase
    }

}