package co.me.xumakweathedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.me.domain.city.use_cases.DeleteCityCommand
import co.me.domain.city.use_cases.IDeleteCity
import co.me.domain.city.use_cases.IGetAllCities
import co.me.domain.entities.City
import co.me.xumakweathedata.main.use_cases.GetCurrentDayCommand
import co.me.xumakweathedata.main.use_cases.IGetCurrentDayNumber
import co.me.xumakweathedata.main.use_cases.IInitialCitiesRequest
import co.me.xumakweathedata.main.use_cases.InitialCitiesRequestCommand
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class MainViewModel(
    private val initialCitiesRequest: IInitialCitiesRequest,
    private val getCurrentDayNumber: IGetCurrentDayNumber,
    private val deleteCity: IDeleteCity,
    private val getAllCities: IGetAllCities,
) : ViewModel() {

    private val _stateLiveData = MutableLiveData<MainActivityState>()

    private var _state =
        MainActivityState(
            city = null,
            cities = emptyList(),
            currentDayNumber = 0,
            currentDateText = dateText,
            selectedCityIndex = -1,
        )
        set(value) {
            field = value
            _stateLiveData.postValue(field)
        }

    private val dateText: String
        get() {
            val format = SimpleDateFormat("EEE, MM/dd/yy HH:mm aa", Locale.US)
            return format.format(Calendar.getInstance().time)
        }

    private suspend fun getCurrentDay(): Int {
        val calendarInstance = Calendar.getInstance()
        val currentDayNumber = calendarInstance.get(Calendar.DAY_OF_WEEK)

        return getCurrentDayNumber(GetCurrentDayCommand(currentDayNumber))
    }

    val state: LiveData<MainActivityState> = _stateLiveData

    init {
        viewModelScope.launch(Dispatchers.IO) {
            initialCitiesRequest(InitialCitiesRequestCommand("Calera"))
        }
        viewModelScope.launch(Dispatchers.IO) {
            listenAllCities()
        }
        viewModelScope.launch(Dispatchers.IO) {
            _state = _state.copy(currentDayNumber = getCurrentDay())
        }
    }

    fun selectedCityIndexChanged(it: Int) {
        _state = getStateWithNewSelectedCity(
            latestSelectedCityIndex = it,
            latestCities = _state.cities
        ).copy(
            selectedCityIndex = it
        )
    }

    fun deleteSelectedCity() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.city?.let {
                deleteCity(DeleteCityCommand(it))
            }
        }
    }

    private suspend fun listenAllCities() =
        getAllCities(Unit).collect {
            _state = getStateWithNewSelectedCity(
                latestSelectedCityIndex = _state.selectedCityIndex,
                latestCities = it
            ).copy(cities = it)
        }

    private fun getStateWithNewSelectedCity(
        latestSelectedCityIndex: Int,
        latestCities: List<City>
    ): MainActivityState {
        val city =
            if (isValidCityIndex(
                    latestSelectedCityIndex,
                    latestCitiesSize = latestCities.size
                )
            ) latestCities[latestSelectedCityIndex] else null
        return _state.copy(city = city)
    }

    private fun isValidCityIndex(latestSelectedCityIndex: Int, latestCitiesSize: Int): Boolean =
        latestSelectedCityIndex > -1 && latestSelectedCityIndex < latestCitiesSize

}