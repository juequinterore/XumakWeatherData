package co.me.xumakweathedata.infrastructure.city

import co.me.xumakweathedata.infrastructure.city.dtos.CityDto
import co.me.xumakweathedata.infrastructure.city.dtos.WeatherDayDto
import co.me.xumakweathedata.infrastructure.city.repository.data_sources.RetrofitCityRemoteDataSource
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type

//Dummy test class to check Retrofit is properly set
class RetrofitCityDataSourceTest {
    @Test
    fun `should return city search by name`() {

        val gsonBuilder = GsonBuilder()

        val type: Type =
            object : TypeToken<List<@kotlin.jvm.JvmSuppressWildcards CityDto>>() {}.type

        gsonBuilder.registerTypeAdapter(type, CityListDeserializer())
        val gson = gsonBuilder.create()
        val gsonConverter = GsonConverterFactory.create(gson)

        val retrofit = Retrofit.Builder().baseUrl("https://weather.exam.bottlerocketservices.com/")
            .addConverterFactory(gsonConverter)
            .build()

        val dataSource = RetrofitCityRemoteDataSource(retrofit = retrofit)

        val cities = dataSource.searchCityByName("Calera")

        println(cities)
    }

    @Test
    fun `should return city weather for day`() {

        val gsonBuilder = GsonBuilder()

        val type: Type =
            object : TypeToken<List<@kotlin.jvm.JvmSuppressWildcards CityDto>>() {}.type

        val type2: Type =
            object :
                TypeToken<Map<@kotlin.jvm.JvmSuppressWildcards Int, @kotlin.jvm.JvmSuppressWildcards WeatherDayDto>>() {}.type

        gsonBuilder.registerTypeAdapter(type, CityListDeserializer())
        gsonBuilder.registerTypeAdapter(type2, WeatherDayMapDeserializer())

        val gson = gsonBuilder.create()
        val gsonConverter = GsonConverterFactory.create(gson)

        val retrofit = Retrofit.Builder().baseUrl("https://weather.exam.bottlerocketservices.com/")
            .addConverterFactory(gsonConverter)
            .build()

        val dataSource = RetrofitCityRemoteDataSource(retrofit = retrofit)

        val map = dataSource.getCityWeather(4048888)

        println(map)

    }
}