package co.me.infrastructure.city.repository.data_sources

import co.me.infrastructure.city.dtos.CityDto
import co.me.infrastructure.city.dtos.WeatherDayDto
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

class RetrofitCityRemoteDataSource(retrofit: Retrofit) : ICityRemoteDataSource {
    interface CityService {
        @GET("/cities")
        fun getCitiesByName(@Query("search") cityName: String): Call<List<CityDto>>

        @GET("/cities/{cityId}")
        fun getCityWeather(@Path("cityId") cityId: Int): Call<Map<Int, WeatherDayDto>>
    }

    private val cityService: CityService = retrofit.create(CityService::class.java)

    override fun searchCityByName(cityName: String): CityDto? {
        val call = cityService.getCitiesByName(cityName)
        val response = call.execute()
        val cities = response.body()

        return cities?.firstOrNull()
    }

    override fun getCityWeather(cityId: Int): Map<Int, WeatherDayDto> {
        val call = cityService.getCityWeather(cityId)
        val response = call.execute()
        val map = response.body()

        return map!!
    }
}