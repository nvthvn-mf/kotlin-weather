package com.example.meteoapp.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.meteoapp.entity.CityEntity

@Database (entities = [CityEntity::class], version = 1)
abstract class MyAppDataBase: RoomDatabase(){
    abstract fun cityDao(): CityDao
}
