package co.me.xumakweathedata

import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
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
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.me.domain.entities.City
import co.me.domain.value_objects.*
import co.me.xumakweathedata.extensions.toIcon
import co.me.xumakweathedata.ui.theme.XumakWeatheDataTheme
import com.bumptech.glide.request.RequestOptions
import com.skydoves.landscapist.glide.GlideImage
import org.koin.android.ext.android.inject
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.skydoves.landscapist.CircularReveal

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by inject()

    @ExperimentalUnitApi
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        setContent {
            XumakWeatheDataTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    val cities: List<City> by viewModel.allCities.observeAsState(emptyList())
                    val currentDayNumber: Int by viewModel.currentDayNumber.observeAsState(0)
                    val currentDateText: String by viewModel.currentDate.observeAsState("")
                    MainContent(cities, currentDayNumber, currentDateText)
                }
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun MainContent(cities: List<City>, currentDayNumber: Int, currentDateText: String) {
    Box{
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            TopSection(cities.firstOrNull(), currentDateText, currentDayNumber)
            BottomSection(cities.firstOrNull(), currentDayNumber)
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
fun ColumnScope.TopSection(city: City?, currentDateText: String, currentDayNumber: Int) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .weight(3f)
        .background(colorResource(id = R.color.main_gradient_start))) {
        GlideImage(
            imageModel = city?.imageUrl?.value ?: "https://all-the-weather-resources.s3.amazonaws.com/Images/Android_City_Images/xhdpi/img_dallas.png",
            contentScale = ContentScale.Crop,
            circularReveal = CircularReveal(duration = 250),
            requestOptions = RequestOptions().centerCrop(),
            modifier = Modifier.alpha(0.9f)
        )
        Box{
            Topbar()
            TopSectionCityInfo(city, currentDateText, currentDayNumber)
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
fun Topbar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = { println("CLICK") }) {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "Search Button",
                tint = Color.White
            )
        }
        Row {
            IconButton(onClick = { println("Click Delete") }) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "Delete Button",
                    tint = Color.White
                )
            }
            IconButton(onClick = { println("Click Radar") }) {
                Icon(
                    imageVector = Icons.Filled.Call,
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
