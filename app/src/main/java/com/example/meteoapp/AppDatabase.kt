package com.example.meteoapp

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.meteoapp.dao.CityDao
import com.example.meteoapp.entity.CityEntity

@Database(entities = [CityEntity::class], version = 1)
//@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getDao(): CityDao
}
