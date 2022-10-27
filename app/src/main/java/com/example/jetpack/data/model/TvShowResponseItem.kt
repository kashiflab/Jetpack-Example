package com.example.jetpack.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TvShow")
data class TvShowResponseItem(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    @Embedded
    val image: Image,
    val name: String,

)