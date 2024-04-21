package com.example.rgz.data.repository

import com.example.rgz.data.db.Car

interface DataBaseRepository {
    suspend fun getAll(): Result<List<Car>>
    suspend fun getLastUpdated(): Result<Car>
    suspend fun insert(data: Car)
    suspend fun delete(data: Car)
    suspend fun delete(name: String)
}