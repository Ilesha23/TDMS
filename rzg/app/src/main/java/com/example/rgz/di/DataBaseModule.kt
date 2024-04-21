package com.example.rgz.di

import android.content.Context
import androidx.room.Room
import com.example.rgz.data.db.CarDao
import com.example.rgz.data.db.DataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context): DataBase {
        return Room.databaseBuilder(
            context,
            DataBase::class.java,
            "db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideCarDao(db: DataBase): CarDao {
        return db.carDao()
    }

}