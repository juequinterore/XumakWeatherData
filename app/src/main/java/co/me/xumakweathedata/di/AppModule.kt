package co.me.xumakweathedata.di

import co.me.di.APIURLQualifier
import co.me.xumakweathedata.BuildConfig
import co.me.xumakweathedata.MainViewModel
import co.me.xumakweathedata.main.use_cases.*
import co.me.xumakweathedata.ui.search.SearchViewModel
import kotlinx.coroutines.FlowPreview
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@FlowPreview
val appModule = module {

    single(APIURLQualifier) {
        BuildConfig.API_URL
    }

    viewModel {
        MainViewModel(
            deleteCity = get(),
            getAllCities = get(),
            getCurrentDayNumber = get(),
            initialCitiesRequest = get(),
        )
    }

    viewModel {
        SearchViewModel(searchCitiesByName = get(), searchAndSaveCityByName = get())
    }

    single<IInitialCitiesRequest> {
        InitialCitiesCitiesRequest(
            getAllCities = get(),
            citiesRepository = get(),
            searchCityByName = get()
        )
    }

    single<IGetCurrentDayNumber> {
        GetCurrentDayNumber()
    }

    single<ISearchAndSaveCityByName> {
        SearchAndSaveCityByName(searchCityByName = get(), citiesRepository = get())
    }
}