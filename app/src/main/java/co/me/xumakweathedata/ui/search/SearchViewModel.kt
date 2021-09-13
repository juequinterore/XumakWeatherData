package co.me.xumakweathedata.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.me.domain.city.use_cases.ISearchCitiesByName
import co.me.domain.city.use_cases.SearchCitiesByNameCommand
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch

@FlowPreview
class SearchViewModel(private val searchCitiesByName: ISearchCitiesByName) : ViewModel() {

    private val _searchChannel = Channel<String>()
    private val _stateLiveData = MutableLiveData<SearchState>()

    private var _state = SearchState(searchText = "", citySearch = CitySearchInitial)
        set(value) {
            field = value
            _stateLiveData.postValue(field)
        }

    val state: LiveData<SearchState> = _stateLiveData

    init {
        viewModelScope.launch(Dispatchers.IO) {
            var searchJob: Job? = null

            _searchChannel.consumeAsFlow().debounce(200).collect {
                searchJob?.cancel()
                if (it.isBlank()) {
                    _state = _state.copy(citySearch = CitySearchInitial)
                    return@collect
                }
                searchJob = launchSearchJob(it)
            }

        }
    }

    private fun launchSearchJob(name: String): Job = viewModelScope.launch(Dispatchers.IO) {
        _state = _state.copy(citySearch = CitySearchInProgress)
        _state = try {
            val cities = searchCitiesByName(SearchCitiesByNameCommand(name))
            val citySearch =
                if (cities.isEmpty()) CitySearchSuccessEmpty else CitySearchResult(cities)
            _state.copy(citySearch = citySearch)
        } catch (ex: Exception) {
            _state.copy(citySearch = CitySearchError)
        }
    }

    fun searchTextChanged(newText: String) {
        _state = _state.copy(searchText = newText)
        viewModelScope.launch(Dispatchers.IO) {
            _searchChannel.send(newText)
        }
    }

}