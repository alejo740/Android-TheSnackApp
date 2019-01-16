package com.cobos.edwin.thesnackapp.api.services

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.cobos.edwin.thesnackapp.api.models.Snack
import io.reactivex.Single

@Dao
interface SnackDao {

    @Query("SELECT * FROM Snack")
    fun getAllSnacks(): Single<List<Snack>>

    @Insert
    fun insertSnacks(snacks: List<Snack>)

    @Insert
    fun insertSnack(snack: Snack)
}