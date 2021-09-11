package co.me.infrastructure.city.repository.data_sources.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import co.me.infrastructure.city.dtos.CityDto
import kotlinx.coroutines.flow.Flow

@Dao
abstract class RoomCityLocalDataSource : ICityLocalDataSource {

    @Query("SELECT * FROM cities")
    abstract override fun getAllCities(): Flow<List<CityDto>>

    @Insert
    abstract override fun insert(citiDto: CityDto)
}