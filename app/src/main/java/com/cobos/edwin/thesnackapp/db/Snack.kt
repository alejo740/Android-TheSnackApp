package com.cobos.edwin.thesnackapp.db

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey


@Entity
data class Snack (

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    var name: String? = null,

    @ColumnInfo(name = "is_veggie")
    var isVeggie: Boolean = true

)