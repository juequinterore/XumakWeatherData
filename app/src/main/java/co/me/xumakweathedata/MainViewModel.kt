package co.me.xumakweathedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.me.domain.city.use_cases.IGetAllCities
import co.me.domain.entities.City
import co.me.xumakweathedata.main.use_cases.IInitialCitiesRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel(
    private val initialCitiesRequest: IInitialCitiesRequest,
    private val getAllCities: IGetAllCities
) : ViewModel() {

    private val _allCities = MutableLiveData<List<City>>()

    val allCities: LiveData<List<City>> = _allCities

    init {
        viewModelScope.launch(Dispatchers.IO) {
            initialCitiesRequest(Unit)
        }
        viewModelScope.launch(Dispatchers.IO) {
            listenAllCities()
        }
    }

    private suspend fun listenAllCities() = getAllCities(Unit).collect { _allCities.postValue(it) }

}