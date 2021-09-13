package co.me.xumakweathedata.ui.theme

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import co.me.domain.entities.City
import co.me.domain.value_objects.DayHour
import co.me.domain.value_objects.HourlyWeather
import co.me.domain.value_objects.WeatherDay
import co.me.domain.value_objects.WeekDay
import co.me.xumakweathedata.MainActivityState
import co.me.xumakweathedata.R
import co.me.xumakweathedata.extensions.toIcon
import com.bumptech.glide.request.RequestOptions
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage

@ExperimentalFoundationApi
@Composable
fun MainContent(navController: NavController, state: MainActivityState) {
    Box{
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            TopSection(navController, state)
            BottomSection(city = state.cities.firstOrNull(), currentDayNumber = state.currentDayNumber)
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun ColumnScope.BottomSection(city: City?, currentDayNumber: Int) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .weight(4f)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            colorResource(id = R.color.main_gradient_start),
                            colorResource(id = R.color.main_gradient_end)
                        )
                    )
                )
        ) {
            WeekRow(weight = 1f, weatherDaysMap = city?.weather ?: mapOf(), currentDayNumber)
            Divider(color = Color.White, thickness = (0.6).dp)
            Box(modifier =  Modifier.weight(3f), contentAlignment = Alignment.BottomCenter){
                HourlyWeatherListColumn(city?.getWeatherForDay(WeekDay(currentDayNumber))?.hourlyWeather ?: mapOf())
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.35f)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                colorResource(id = R.color.main_gradient_end)
                            ),
                        )
                    )
                )
            }
        }
    }
}

@Composable
fun ColumnScope.TopSection(
    navController: NavController,
    state: MainActivityState
) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .weight(3f)
        .background(colorResource(id = R.color.main_gradient_start))) {
        val city = state.cities.firstOrNull()
        GlideImage(
            imageModel = city?.imageUrl?.value ?: "https://all-the-weather-resources.s3.amazonaws.com/Images/Android_City_Images/xhdpi/img_dallas.png",
            contentScale = ContentScale.Crop,
            circularReveal = CircularReveal(duration = 250),
            requestOptions = RequestOptions().centerCrop(),
            modifier = Modifier.alpha(0.9f)
        )
        Box{
            Topbar(navController)
            TopSectionCityInfo(city = city, currentDateText = state.currentDateText, currentDayNumber = state.currentDayNumber)
        }

    }
}

@Composable
fun TopSectionCityInfo(city: City?, currentDateText: String, currentDayNumber: Int) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = city?.fullName ?: "",
            fontSize = 16.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = currentDateText, fontSize = 15.sp, color = Color.White)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "${city?.weather?.get(WeekDay(currentDayNumber))?.high ?: 0}°", fontSize = 40.sp, color = Color.White)
    }
}

@Composable
fun Topbar(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = { navController.navigate("search") }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_icon_search),
                contentDescription = "Search Button",
                tint = Color.White
            )
        }
        Row {
            IconButton(onClick = { Log.e("PUTA", "Click en Delete") }) {
                Icon(
                    imageVector = Icons.Outlined.Delete,
                    contentDescription = "Delete Button",
                    tint = Color.White
                )
            }
            IconButton(onClick = { Log.e("PUTA", "Click en Radar") }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_icon_radar),
                    contentDescription = "Radar Button",
                    tint = Color.White
                )
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun HourlyWeatherListColumn(hourlyWeatherMap: Map<DayHour, HourlyWeather>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(bottom = 35.dp)
    ) {
        HourlyWeatherList(hourlyWeatherMap)
    }
}

@Composable
fun ColumnScope.WeekRow(weight: Float, weatherDaysMap: Map<WeekDay, WeatherDay>, currentDayNumber: Int) {
    val weatherDaysList = weatherDaysMap.entries.sortedBy { it.key.value }.map { it.value }

    Row(
        modifier = Modifier
            .weight(weight = weight)
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        weatherDaysList.forEachIndexed {index, it ->
            DayWeather(weatherDay = it, isActive = index == currentDayNumber)
        }
    }
}

@Composable
fun DayWeather(weatherDay: WeatherDay, isActive: Boolean) {
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxHeight()) {
        val color = if(isActive) Color.White else colorResource(id = R.color.inactive_week_day)
        Text(weatherDay.dayOfTheWeek.toShortName(),
            color = color)
        Icon(
            painter = painterResource(weatherDay.weatherType.toIcon()),
            contentDescription = "Day",
            tint = color,
            modifier = Modifier.size(20.dp)
        )
        Text("${weatherDay.high}°",
            color = color)
    }
}

@ExperimentalFoundationApi
@Composable
fun HourlyWeatherList(hourlyWeatherMap: Map<DayHour, HourlyWeather>) {
    val hourlyWeatherList = hourlyWeatherMap.entries.sortedBy { it.key.value }.map { it.value }

    HourlyWeatherHeader()
    LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)){
        items(hourlyWeatherList){ hourlyWeather ->
            HourlyWeatherItem(hourlyWeather)
        }
    }

}

const val tableWeight = (1.0/6).toFloat()

@Composable
fun HourlyWeatherHeader() {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 16.dp, end = 16.dp, start = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically) {

        val modifier = Modifier.weight(tableWeight)
        val headerFontSize = 10.sp

        @Composable
        fun HourlyWeatherHeaderText(text: String){
            Text(text = text,
                modifier = modifier,
                textAlign = TextAlign.Center,
                fontSize = headerFontSize,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }

        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = "Weather Icon",
            tint = Color.Transparent,
            modifier = modifier
        )
        HourlyWeatherHeaderText(text = "Time")
        HourlyWeatherHeaderText(text = "Temp")
        HourlyWeatherHeaderText(text = "Chance\nof Rain")
        HourlyWeatherHeaderText(text = "Wind\n(mph)")
        HourlyWeatherHeaderText(text = "Humidity")
    }
}

@Composable
fun HourlyWeatherItem(hourlyWeather: HourlyWeather) {
    Row(modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth()
        .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly) {

        val fontSize = 15.sp
        val modifier = Modifier.weight(tableWeight)

        @Composable
        fun HourlyWeatherItemText(text: String){
            Text(text = text,
                modifier = modifier,
                textAlign = TextAlign.Center,
                fontSize = fontSize,
                color = Color.White)
        }

        Icon(
            painter = painterResource(id = hourlyWeather.weatherType.toIcon()),
            contentDescription = "Weather Icon",
            tint = Color.White,
            modifier = modifier
        )

        HourlyWeatherItemText(text = hourlyWeather.hour.to12HoursString())
        HourlyWeatherItemText(text = "${hourlyWeather.temperature}°")
        HourlyWeatherItemText(text = hourlyWeather.rainChance.toPercentageString())
        HourlyWeatherItemText(text = "${hourlyWeather.windSpeed}")
        HourlyWeatherItemText(text = hourlyWeather.humidity.toPercentageString())
    }
}
