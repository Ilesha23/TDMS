package com.example.rgz.di

import com.example.rgz.data.db.CarDao
import com.example.rgz.data.db.DataBase
import com.example.rgz.data.repository.DataBaseRepository
import com.example.rgz.data.repository.DataBaseRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideDataBaseRepository(carDao: CarDao): DataBaseRepository {
        return DataBaseRepositoryImpl(carDao)
    }

}