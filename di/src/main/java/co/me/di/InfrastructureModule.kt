package co.me.di

import co.me.domain.city.ICityRepository
import co.me.infrastructure.city.CityListDeserializer
import co.me.infrastructure.city.WeatherDayMapDeserializer
import co.me.infrastructure.city.dtos.CityDto
import co.me.infrastructure.city.dtos.WeatherDayDto
import co.me.infrastructure.city.repository.CityRepository
import co.me.infrastructure.city.repository.data_sources.ICityRemoteDataSource
import co.me.infrastructure.city.repository.data_sources.RetrofitCityRemoteDataSource
import co.me.infrastructure.city.repository.data_sources.local.ICityLocalDataSource
import co.me.infrastructure.city.repository.data_sources.local.db.XumakWeatherDatabase
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type

val infrastructureModule = module {

    single {
        CityListDeserializer()
    }

    single {
        WeatherDayMapDeserializer()
    }

    single {
        val gsonBuilder = GsonBuilder()

        val listCityType: Type =
            object : TypeToken<List<@kotlin.jvm.JvmSuppressWildcards CityDto>>() {}.type

        val mapIntWeatherDayDtoType: Type =
            object :
                TypeToken<Map<@kotlin.jvm.JvmSuppressWildcards Int, @kotlin.jvm.JvmSuppressWildcards WeatherDayDto>>() {}.type

        gsonBuilder.registerTypeAdapter(listCityType, get<CityListDeserializer>())
        gsonBuilder.registerTypeAdapter(mapIntWeatherDayDtoType, get<WeatherDayMapDeserializer>())

        val gson = gsonBuilder.create()

        GsonConverterFactory.create(gson)
    }

    single {
        Retrofit.Builder().baseUrl(get<String>(APIURLQualifier))
            .addConverterFactory(get<GsonConverterFactory>())
            .build()
    }

    single<ICityRemoteDataSource> {
        RetrofitCityRemoteDataSource(retrofit = get())
    }

    single {
        XumakWeatherDatabase.getInstance(androidContext())
    }

    single<ICityLocalDataSource> {
        get<XumakWeatherDatabase>().cityDataSource()
    }

    single<ICityRepository> {
        CityRepository(
            remoteCityDataSource = get(),
            localCityDataSource = get()
        )

    }
}