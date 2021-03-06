package co.me.di

import co.me.domain.city.use_cases.*
import org.koin.dsl.module

val domainModule = module {

    single<IGetAllCities> {
        GetAllCities(cityRepository = get())
    }

    single<IGetCityWeather> {
        GetCityWeather(cityRepository = get())
    }

    single<ISearchCityByName> {
        SearchCityByName(cityRepository = get(), getCityWeather = get())
    }

    single<ISearchCitiesByName> {
        SearchCitiesByName(cityRepository = get())
    }

    single<IDeleteCity> {
        DeleteCity(cityRepository = get())
    }


}