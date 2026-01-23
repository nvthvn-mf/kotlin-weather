package com.example.meteoapp.dao
import androidx.room.*
import com.example.meteoapp.entity.CityEntity

@Dao
interface CityDao{
    @Insert
     fun insert(city: CityEntity)

    @Query("SELECT * FROM city_table ORDER BY name ASC")
     fun getAllCities(): List<CityEntity>

    @Query("SELECT * FROM city_table WHERE id=:cityId")
    suspend fun find(cityId: Int): CityEntity?

    @Delete
    suspend fun delete (city: CityEntity)

    @Update
    suspend fun update(city: CityEntity)


}