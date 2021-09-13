package co.me.xumakweathedata.ui.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import co.me.domain.entities.City
import co.me.xumakweathedata.R
import kotlinx.coroutines.FlowPreview
import org.koin.androidx.compose.getViewModel


@FlowPreview
@Composable
fun SearchContent(navController: NavController) {

    val viewModel = getViewModel<SearchViewModel>()
    val goBack: Boolean by viewModel.goBackLiveData.observeAsState(initial = false)

    val searchState: SearchState by viewModel.state.observeAsState(
        initial = SearchState(
            searchText = "",
            citySearch = CitySearchInitial
        )
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(35.dp),
        contentAlignment = Alignment.Center
    )
    {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            TopBar(navController = navController)
            SearchTextField(searchState, viewModel)
            CitySearch(searchState, viewModel)
            if (goBack) {
                navController.popBackStack(route = "main", inclusive = false)
            }
        }
        if (searchState.citySearch == CitySearchInProgress) {
            CircularProgressIndicator(color = colorResource(id = R.color.inactive_week_day))
        }
    }
}

@Composable
fun TopBar(navController: NavController) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
        IconButton(onClick = { navController.popBackStack() }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_icon_close),
                contentDescription = stringResource(R.string.close_search_button_description),
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

@FlowPreview
@Composable
fun CitySearch(searchState: SearchState, viewModel: SearchViewModel) {
    when (searchState.citySearch) {
        CitySearchError -> TextMessage(message = stringResource(R.string.error_during_search_request))
        CitySearchInProgress -> { /*Nothing to show*/
        }
        CitySearchInitial -> { /*Nothing to show*/
        }
        is CitySearchResult -> CitySearchResults(cities = searchState.citySearch.cities, viewModel)
        CitySearchSuccessEmpty -> TextMessage(
            message = stringResource(
                id = R.string.search_empty_result,
                searchState.searchText
            )
        )
    }
}

@FlowPreview
@Composable
fun CitySearchResults(cities: List<City>, viewModel: SearchViewModel) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        items(cities) { city ->
            CityItem(city, viewModel)
        }
    }
}

@FlowPreview
@Composable
fun CityItem(city: City, viewModel: SearchViewModel) {
    val defaultPadding = 16.dp
    Column(
        Modifier
            .clickable { viewModel.citySelected(city) }) {
        Text(
            city.fullName,
            modifier = Modifier.padding(
                top = defaultPadding,
                start = defaultPadding,
                end = defaultPadding,
            )
        )
        Divider(Modifier.height(1.dp))
    }
}
