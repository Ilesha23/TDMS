package com.example.rgz.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity
data class Car(
    @PrimaryKey val id: Long = UUID.randomUUID().leastSignificantBits,
    val name: String,
    val description: String,
    val price: Double
)
