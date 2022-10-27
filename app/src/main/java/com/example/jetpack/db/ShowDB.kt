package com.example.jetpack.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.jetpack.data.model.TvShowResponseItem

@Database(entities = [TvShowResponseItem::class], version = 1)
abstract class ShowDB: RoomDatabase() {

    abstract fun getShowDao() : ShowDao
}