package com.example.jetpack.di

import android.content.Context
import androidx.room.Room
import com.example.jetpack.db.ShowDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun providesDatabase(@ApplicationContext context: Context) : ShowDB{
        return Room.databaseBuilder(context, ShowDB::class.java, "Show").build()
    }
}