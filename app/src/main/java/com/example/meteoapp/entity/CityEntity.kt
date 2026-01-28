package com.example.meteoapp.entity

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "city_table")
data class CityEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var name: String,
    var icon: String?,
    var temperature: Double?,
    var weather: String?
)
