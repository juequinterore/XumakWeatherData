package co.me.xumakweathedata.ui.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import co.me.domain.entities.City
import kotlinx.coroutines.FlowPreview
import org.koin.androidx.compose.getViewModel

@FlowPreview
@Composable
fun SearchContent() {

    val viewModel = getViewModel<SearchViewModel>()

    val searchState: SearchState by viewModel.state.observeAsState(
        initial = SearchState(
            searchText = "",
            citySearch = CitySearchInitial
        )
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        SearchTextField(searchState, viewModel)
        CitySearch(searchState)
    }
}

@Composable
fun TextMessage(message: String) {
    Text(text = message)
}

@FlowPreview
@Composable
fun SearchTextField(searchState: SearchState, viewModel: SearchViewModel) {
    TextField(
        singleLine = true,
        value = searchState.searchText,
        onValueChange = { viewModel.searchTextChanged(newText = it) })
}

@Composable
fun CitySearch(searchState: SearchState) {
    when (searchState.citySearch) {
        CitySearchError -> TextMessage(message = "Error during request. Try again later")
        CitySearchInProgress -> TextMessage(message = "Loading Data")
        CitySearchInitial -> { /*Nothing to show*/
        }
        is CitySearchResult -> CitySearchResults(cities = searchState.citySearch.cities)
        CitySearchSuccessEmpty -> TextMessage(message = "No cities by: ${searchState.searchText}")
    }
}

@Composable
fun CitySearchResults(cities: List<City>) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        items(cities) { city ->
            CityItem(city)
        }
    }
}

@Composable
fun CityItem(city: City) {
    Text(city.fullName)
}
