package co.me.xumakweathedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.me.domain.city.use_cases.IGetAllCities
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

    private val _stateLiveData = MutableLiveData<MainActivityState>()

    private var _state =
        MainActivityState(cities = emptyList(), currentDayNumber = 0, currentDateText = dateText)
        set(value) {
            field = value
            _stateLiveData.postValue(field)
        }

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

    val state: LiveData<MainActivityState> = _stateLiveData

    init {
        viewModelScope.launch(Dispatchers.IO) {
            initialCitiesRequest(Unit)
        }
        viewModelScope.launch(Dispatchers.IO) {
            listenAllCities()
        }
        viewModelScope.launch(Dispatchers.IO) {
            _state = _state.copy(currentDayNumber = getCurrentDay())
//            _currentDayNumber.postValue(getCurrentDay())
        }
    }

    private suspend fun listenAllCities() =
        getAllCities(Unit).collect { _state = _state.copy(cities = it) }

}