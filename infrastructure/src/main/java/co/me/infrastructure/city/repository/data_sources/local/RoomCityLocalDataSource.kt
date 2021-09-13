package co.me.infrastructure.city.repository.data_sources.local

import androidx.room.*
import co.me.infrastructure.city.dtos.CityDto
import kotlinx.coroutines.flow.Flow

@Dao
abstract class RoomCityLocalDataSource : ICityLocalDataSource {

    @Query("SELECT * FROM cities ORDER BY insertTimestamp DESC")
    abstract override fun getAllCities(): Flow<List<CityDto>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract override fun insert(citiDto: CityDto)

    @Delete
    abstract fun delete(city: CityDto)
}