package co.me.xumakweathedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.me.domain.city.use_cases.IGetAllCities
import co.me.domain.entities.City
import co.me.xumakweathedata.main.use_cases.GetCurrentDayCommand
import co.me.xumakweathedata.main.use_cases.IGetCurrentDayNumber
import co.me.xumakweathedata.main.use_cases.IInitialCitiesRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class MainViewModel(
    private val initialCitiesRequest: IInitialCitiesRequest,
    private val getCurrentDayNumber: IGetCurrentDayNumber,
    private val getAllCities: IGetAllCities,
) : ViewModel() {

    private val _allCities = MutableLiveData<List<City>>()
    private val _currentDate = MutableLiveData<String>()
    private val _currentDayNumber = MutableLiveData<Int>()
    private val dateText: String
        get() {
            val format = SimpleDateFormat()
            format.applyLocalizedPattern("EEE, MM/dd/yy HH:mm aa")
            return format.format(Calendar.getInstance().time)
        }

    private suspend fun getCurrentDay(): Int {
        val calendarInstance = Calendar.getInstance()
        val currentDayNumber = calendarInstance.get(Calendar.DAY_OF_WEEK)

        return getCurrentDayNumber(GetCurrentDayCommand(currentDayNumber))
    }

    val allCities: LiveData<List<City>> = _allCities
    val currentDate: LiveData<String> = _currentDate
    val currentDayNumber: LiveData<Int> = _currentDayNumber

    init {
        viewModelScope.launch(Dispatchers.IO) {
            initialCitiesRequest(Unit)
        }
        viewModelScope.launch(Dispatchers.IO) {
            listenAllCities()
        }
        viewModelScope.launch(Dispatchers.IO) {
            _currentDayNumber.postValue(getCurrentDay())
        }

        _currentDate.postValue(dateText)
    }

    private suspend fun listenAllCities() = getAllCities(Unit).collect { _allCities.postValue(it) }

}