package com.example.jetpack.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.jetpack.data.model.TvShowResponseItem

@Dao
interface ShowDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addShows(show: TvShowResponseItem)

    @Query("SELECT * FROM TvShow")
    suspend fun getShows() : List<TvShowResponseItem>
}