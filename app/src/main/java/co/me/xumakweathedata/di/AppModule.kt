package co.me.xumakweathedata.di

import co.me.di.APIURLQualifier
import co.me.xumakweathedata.BuildConfig
import co.me.xumakweathedata.MainViewModel
import co.me.xumakweathedata.main.use_cases.IInitialCitiesRequest
import co.me.xumakweathedata.main.use_cases.InitialCitiesCitiesRequest
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single(APIURLQualifier) {
        BuildConfig.API_URL
    }

    viewModel { MainViewModel(getAllCities = get(), initialCitiesRequest = get()) }

    single<IInitialCitiesRequest> {
        InitialCitiesCitiesRequest(
            getAllCities = get(),
            citiesRepository = get(),
            searchCityByName = get()
        )
    }
}