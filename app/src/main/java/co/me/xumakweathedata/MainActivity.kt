package co.me.xumakweathedata

import android.os.Bundle
import android.util.Log
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.me.domain.value_objects.*
import co.me.xumakweathedata.extensions.toIcon
import co.me.xumakweathedata.ui.theme.XumakWeatheDataTheme
import org.koin.android.ext.android.inject

val hourlyWeatherMap = mapOf(
    DayHour(0) to HourlyWeather(
        rainChance = Probability(0.2),
        hour = DayHour(0),
        humidity = Probability(0.15),
        weatherType = WeatherType.CLOUDY,
        windSpeed = 32.0,
        temperature = 26
    ),
    DayHour(1) to HourlyWeather(
        rainChance = Probability(0.2),
        hour = DayHour(1),
        humidity = Probability(0.15),
        weatherType = WeatherType.CLOUDY,
        windSpeed = 32.0,
        temperature = 26
    ),
    DayHour(2) to HourlyWeather(
        rainChance = Probability(0.2),
        hour = DayHour(2),
        humidity = Probability(0.15),
        weatherType = WeatherType.SNOWSLEET,
        windSpeed = 32.0,
        temperature = 26
    ),
    DayHour(3) to HourlyWeather(
        rainChance = Probability(0.2),
        hour = DayHour(3),
        humidity = Probability(0.15),
        weatherType = WeatherType.CLOUDY,
        windSpeed = 32.0,
        temperature = 26
    ),
    DayHour(4) to HourlyWeather(
        rainChance = Probability(0.2),
        hour = DayHour(4),
        humidity = Probability(0.15),
        weatherType = WeatherType.CLOUDY,
        windSpeed = 32.0,
        temperature = 26
    ),
    DayHour(5) to HourlyWeather(
        rainChance = Probability(0.2),
        hour = DayHour(5),
        humidity = Probability(0.15),
        weatherType = WeatherType.SNOWSLEET,
        windSpeed = 32.0,
        temperature = 26
    ),
    DayHour(6) to HourlyWeather(
        rainChance = Probability(0.2),
        hour = DayHour(6),
        humidity = Probability(0.15),
        weatherType = WeatherType.CLOUDY,
        windSpeed = 32.0,
        temperature = 26
    ),
    DayHour(7) to HourlyWeather(
        rainChance = Probability(0.2),
        hour = DayHour(7),
        humidity = Probability(0.15),
        weatherType = WeatherType.CLOUDY,
        windSpeed = 32.0,
        temperature = 26
    ),
    DayHour(8) to HourlyWeather(
        rainChance = Probability(0.2),
        hour = DayHour(8),
        humidity = Probability(0.15),
        weatherType = WeatherType.SNOWSLEET,
        windSpeed = 32.0,
        temperature = 26
    ),
    DayHour(9) to HourlyWeather(
        rainChance = Probability(0.2),
        hour = DayHour(9),
        humidity = Probability(0.15),
        weatherType = WeatherType.CLOUDY,
        windSpeed = 32.0,
        temperature = 26
    ),
    DayHour(10) to HourlyWeather(
        rainChance = Probability(0.2),
        hour = DayHour(10),
        humidity = Probability(0.15),
        weatherType = WeatherType.CLOUDY,
        windSpeed = 32.0,
        temperature = 26
    ),
    DayHour(11) to HourlyWeather(
        rainChance = Probability(0.2),
        hour = DayHour(11),
        humidity = Probability(0.15),
        weatherType = WeatherType.SNOWSLEET,
        windSpeed = 32.0,
        temperature = 26
    ),
    DayHour(12) to HourlyWeather(
        rainChance = Probability(0.2),
        hour = DayHour(12),
        humidity = Probability(0.15),
        weatherType = WeatherType.SNOWSLEET,
        windSpeed = 32.0,
        temperature = 26
    ),
    DayHour(13) to HourlyWeather(
        rainChance = Probability(0.2),
        hour = DayHour(13),
        humidity = Probability(0.15),
        weatherType = WeatherType.CLOUDY,
        windSpeed = 32.0,
        temperature = 26
    ),
    DayHour(14) to HourlyWeather(
        rainChance = Probability(0.2),
        hour = DayHour(14),
        humidity = Probability(0.15),
        weatherType = WeatherType.SNOWSLEET,
        windSpeed = 32.0,
        temperature = 26
    ),
    DayHour(15) to HourlyWeather(
        rainChance = Probability(0.2),
        hour = DayHour(15),
        humidity = Probability(0.15),
        weatherType = WeatherType.CLOUDY,
        windSpeed = 32.0,
        temperature = 26
    ),
    DayHour(16) to HourlyWeather(
        rainChance = Probability(0.2),
        hour = DayHour(16),
        humidity = Probability(0.15),
        weatherType = WeatherType.CLOUDY,
        windSpeed = 32.0,
        temperature = 26
    ),
    DayHour(17) to HourlyWeather(
        rainChance = Probability(0.2),
        hour = DayHour(17),
        humidity = Probability(0.15),
        weatherType = WeatherType.SNOWSLEET,
        windSpeed = 32.0,
        temperature = 26
    ),
    DayHour(18) to HourlyWeather(
        rainChance = Probability(0.2),
        hour = DayHour(18),
        humidity = Probability(0.15),
        weatherType = WeatherType.CLOUDY,
        windSpeed = 32.0,
        temperature = 26
    ),
    DayHour(19) to HourlyWeather(
        rainChance = Probability(0.2),
        hour = DayHour(19),
        humidity = Probability(0.15),
        weatherType = WeatherType.CLOUDY,
        windSpeed = 32.0,
        temperature = 26
    ),
    DayHour(20) to HourlyWeather(
        rainChance = Probability(0.2),
        hour = DayHour(20),
        humidity = Probability(0.15),
        weatherType = WeatherType.SNOWSLEET,
        windSpeed = 32.0,
        temperature = 26
    ),
    DayHour(21) to HourlyWeather(
        rainChance = Probability(0.2),
        hour = DayHour(21),
        humidity = Probability(0.15),
        weatherType = WeatherType.CLOUDY,
        windSpeed = 32.0,
        temperature = 26
    ),
    DayHour(22) to HourlyWeather(
        rainChance = Probability(0.2),
        hour = DayHour(22),
        humidity = Probability(0.15),
        weatherType = WeatherType.CLOUDY,
        windSpeed = 32.0,
        temperature = 26
    ),
    DayHour(23) to HourlyWeather(
        rainChance = Probability(0.2),
        hour = DayHour(23),
        humidity = Probability(0.15),
        weatherType = WeatherType.SNOWSLEET,
        windSpeed = 32.0,
        temperature = 26
    )
)

val weatherDays = mapOf(
    WeekDay(0) to WeatherDay(
        dayOfTheWeek = WeekDay(0),
        low = 25,
        high = 75,
        weatherType = WeatherType.SUNNY,
        hourlyWeather = hourlyWeatherMap
    ),
    WeekDay(1) to WeatherDay(
        dayOfTheWeek = WeekDay(1),
        low = 11,
        high = 23,
        weatherType = WeatherType.CLOUDY,
        hourlyWeather = mapOf(
            DayHour(0) to HourlyWeather(
                rainChance = Probability(0.4),
                hour = DayHour(0),
                humidity = Probability(0.15),
                weatherType = WeatherType.CLOUDY,
                windSpeed = 32.0,
                temperature = 26
            ),
            DayHour(1) to HourlyWeather(
                rainChance = Probability(0.2),
                hour = DayHour(1),
                humidity = Probability(0.15),
                weatherType = WeatherType.CLOUDY,
                windSpeed = 32.0,
                temperature = 26
            ),
            DayHour(2) to HourlyWeather(
                rainChance = Probability(0.2),
                hour = DayHour(2),
                humidity = Probability(0.15),
                weatherType = WeatherType.SUNNY,
                windSpeed = 32.0,
                temperature = 26
            )
        )
    ),
    WeekDay(2) to WeatherDay(
        dayOfTheWeek = WeekDay(2),
        low = 25,
        high = 75,
        weatherType = WeatherType.SNOWSLEET,
        hourlyWeather = hourlyWeatherMap
    ),
    WeekDay(3) to WeatherDay(
        dayOfTheWeek = WeekDay(3),
        low = 25,
        high = 75,
        weatherType = WeatherType.HEAVYRAIN,
        hourlyWeather = hourlyWeatherMap
    ),
    WeekDay(4) to WeatherDay(
        dayOfTheWeek = WeekDay(4),
        low = 25,
        high = 75,
        weatherType = WeatherType.LIGHTRAIN,
        hourlyWeather = hourlyWeatherMap
    ),
    WeekDay(5) to WeatherDay(
        dayOfTheWeek = WeekDay(5),
        low = 25,
        high = 75,
        weatherType = WeatherType.PARTLYCLOUDY,
        hourlyWeather = hourlyWeatherMap
    ),
    WeekDay(6) to WeatherDay(
        dayOfTheWeek = WeekDay(6),
        low = 25,
        high = 75,
        weatherType = WeatherType.SUNNY,
        hourlyWeather = hourlyWeatherMap
    )
)

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by inject()

    @ExperimentalUnitApi
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        subscribeToLiveData()

        setContent {
            XumakWeatheDataTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
//                    Greeting("Android")
                    MainContent()
                }
            }
        }
    }

    private fun subscribeToLiveData() {
        viewModel.allCities.observe(this) {
            Log.e("PUTA", "Las ciudades son: $it")
        }
    }
}

@ExperimentalFoundationApi
@Preview
@Composable
fun MainContent() {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(Color.Black)
    ) {
//        GlideImage(
//            imageModel = "https://all-the-weather-resources.s3.amazonaws.com/Images/Android_City_Images/xhdpi/img_dallas.png",
//            contentScale = ContentScale.Crop
//        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(2f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
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

                Column(
                    modifier = Modifier
                        .fillMaxHeight(), verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Dallas, TX", fontSize = 15.sp, color = Color.White)
                    Text(text = "Mon 4/21/18  11:58 PM", fontSize = 15.sp, color = Color.White)
                    Text(text = "75°", fontSize = 40.sp, color = Color.White)
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(3f)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                ) {
                    WeekRow(weight = 1f, weatherDaysMap = weatherDays)
                    Divider(color = Color.White, thickness = 1.dp)
                    HourlyWeatherListColumn(3f)
                }
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun ColumnScope.HourlyWeatherListColumn(weight: Float) {
    Column(
        modifier = Modifier
            .weight(weight)
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        HourlyWeatherList(hourlyWeatherMap)
    }
}

@Composable
fun ColumnScope.WeekRow(weight: Float, weatherDaysMap: Map<WeekDay, WeatherDay>) {
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
        weatherDaysList.forEach {
            DayWeather(it)
        }
    }
}

@Composable
fun DayWeather(weatherDay: WeatherDay) {
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxHeight()) {
        Text(weatherDay.dayOfTheWeek.toShortName(),
            color = Color.White)
        Icon(
            painter = painterResource(weatherDay.weatherType.toIcon()),
            contentDescription = "Day",
            tint = Color.White,
            modifier = Modifier.size(20.dp)
        )
        Text("${weatherDay.high}°",
            color = Color.White)
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
