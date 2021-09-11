package co.me.infrastructure.city.repository.data_sources.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import co.me.infrastructure.city.dtos.CityDto
import co.me.infrastructure.city.repository.data_sources.local.RoomCityLocalDataSource
import co.me.infrastructure.city.repository.data_sources.local.converters.HourlyWeatherDtoConverter
import co.me.infrastructure.city.repository.data_sources.local.converters.WeatherDayDtoConverter

@Database(
    entities = [
        CityDto::class
    ],
    version = 1
)
@TypeConverters(HourlyWeatherDtoConverter::class, WeatherDayDtoConverter::class)
abstract class XumakWeatherDatabase : RoomDatabase() {

    abstract fun cityDataSource(): RoomCityLocalDataSource

    companion object {
        @Volatile
        private var INSTANCE: XumakWeatherDatabase? = null

        fun getInstance(context: Context): XumakWeatherDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        XumakWeatherDatabase::class.java,
                        "xumak_weather"
                    )
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}