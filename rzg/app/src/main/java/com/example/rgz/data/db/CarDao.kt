package com.example.rgz.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CarDao {

    @Query("SELECT * FROM car")
    fun getAll(): List<Car>

    @Query("SELECT * FROM car WHERE id=:id")
    fun getById(id: Long): Car

    @Insert
    fun insert(car: Car)

    @Delete
    fun delete(car: Car)

    @Query("DELETE FROM car WHERE name=:name")
    fun delete(name: String)

}