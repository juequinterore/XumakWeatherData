package co.me.xumakweathedata.ui.search

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import co.me.domain.entities.City
import co.me.xumakweathedata.R
import kotlinx.coroutines.FlowPreview
import org.koin.androidx.compose.getViewModel


@FlowPreview
@Preview
@Composable
fun SearchContent(navController: NavController? = null) {

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
            .padding(35.dp)
    ) {
        TopBar(navController = navController)
        SearchTextField(searchState, viewModel)
        CitySearch(searchState)
    }
}

@Composable
fun TopBar(navController: NavController?) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
        IconButton(onClick = { navController?.popBackStack() }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_icon_close),
                contentDescription = "Close Search Button",
                tint = Color.Black
            )
        }
    }
}

@Composable
fun TextMessage(message: String) {
    Text(text = message)
}

@FlowPreview
@Composable
fun SearchTextField(searchState: SearchState, viewModel: SearchViewModel) {
    val defaultColor = colorResource(
        id = R.color.search_text_field_color
    )

    TextField(
        value = searchState.searchText,
        onValueChange = { viewModel.searchTextChanged(newText = it) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp),
        singleLine = true,
        textStyle = TextStyle(fontSize = 40.sp, fontWeight = FontWeight.ExtraLight),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            focusedIndicatorColor = defaultColor,
            textColor = defaultColor,
            cursorColor = defaultColor
        )
    )
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
    val defaultPadding = 16.dp
    Text(
        city.fullName,
        modifier = Modifier.padding(
            top = defaultPadding,
            start = defaultPadding,
            end = defaultPadding,
            bottom = 8.dp
        )
    )
    Divider(Modifier.height(1.dp))
}
