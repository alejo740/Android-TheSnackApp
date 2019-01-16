package com.cobos.edwin.thesnackapp.api.services

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.cobos.edwin.thesnackapp.api.models.Snack
import javax.inject.Singleton


@Singleton
@Database(entities = [Snack::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun snackDao(): SnackDao

    companion object {
        const val DB_NAME = "snacks-db"
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