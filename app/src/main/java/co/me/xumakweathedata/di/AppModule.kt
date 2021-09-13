package co.me.xumakweathedata.di

import co.me.di.APIURLQualifier
import co.me.xumakweathedata.BuildConfig
import co.me.xumakweathedata.MainViewModel
import co.me.xumakweathedata.main.use_cases.GetCurrentDayNumber
import co.me.xumakweathedata.main.use_cases.IGetCurrentDayNumber
import co.me.xumakweathedata.main.use_cases.IInitialCitiesRequest
import co.me.xumakweathedata.main.use_cases.InitialCitiesCitiesRequest
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
            getAllCities = get(),
            initialCitiesRequest = get(),
            getCurrentDayNumber = get()
        )
    }

    viewModel {
        SearchViewModel(searchCitiesByName = get())
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
}