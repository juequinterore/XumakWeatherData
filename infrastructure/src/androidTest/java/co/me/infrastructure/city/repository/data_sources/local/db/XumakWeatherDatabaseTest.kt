package co.me.infrastructure.city.repository.data_sources.local.db

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import co.me.infrastructure.city.dtos.CityDto
import co.me.infrastructure.city.dtos.HourlyWeatherDto
import co.me.infrastructure.city.dtos.WeatherDayDto
import co.me.infrastructure.city.repository.data_sources.local.RoomCityLocalDataSource
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class XumakWeatherDatabaseTest {

    private lateinit var cityDao: RoomCityLocalDataSource
    private lateinit var db: XumakWeatherDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext

        db = Room.inMemoryDatabaseBuilder(context, XumakWeatherDatabase::class.java)
            .allowMainThreadQueries()
            .build()

        cityDao = db.cityDataSource()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetNight() = runBlocking {
        //Arrange
        val cityDto = CityDto(
            geonameid = 1234,
            name = "Medellín",
            imageUrl = "https://medellin.co/image.png",
            weather = mapOf(
                6 to WeatherDayDto(
                    dayOfTheWeek = 6,
                    weatherType = "cloudy",
                    low = 12,
                    high = 32,
                    hourlyWeather = mapOf(
                        0 to HourlyWeatherDto(
                            rainChance = 0.3,
                            hour = 3,
                            humidity = 0.19,
                            weatherType = "cloudy",
                            windSpeed = 23.0,
                            temperature = 39
                        )
                    )
                )
            )
        )

        cityDao.insert(cityDto)

        //Act
        val allCities = cityDao.getAllCities().first()

        //Assert
        assertEquals(allCities, listOf(cityDto))
    }

}