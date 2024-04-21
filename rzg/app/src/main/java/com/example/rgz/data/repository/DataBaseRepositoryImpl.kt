package com.example.rgz.data.repository

import android.util.Log
import com.example.rgz.data.db.Car
import com.example.rgz.data.db.CarDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DataBaseRepositoryImpl @Inject constructor(
    private val db: CarDao
) : DataBaseRepository {

    private var lastUpdatedId: Long? = null

    override suspend fun getAll(): Result<List<Car>> {
        return withContext(Dispatchers.IO) {
            try {
                val items = db.getAll()
                Result.success(items)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    override suspend fun getLastUpdated(): Result<Car> {
        return withContext(Dispatchers.IO) {
            try {
                val car = db.getById(lastUpdatedId!!)
                Result.success(car)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    override suspend fun insert(data: Car) {
        withContext(Dispatchers.IO) {
            lastUpdatedId = data.id
            db.insert(data)
        }
    }

    override suspend fun delete(data: Car) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(name: String) {
        withContext(Dispatchers.IO) {
            db.delete(name)
        }
    }


}