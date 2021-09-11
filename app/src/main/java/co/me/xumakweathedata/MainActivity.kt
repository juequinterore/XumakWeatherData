package co.me.xumakweathedata

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.me.domain.value_objects.DayHour
import co.me.domain.value_objects.HourlyWeather
import co.me.domain.value_objects.Probability
import co.me.domain.value_objects.WeatherType
import co.me.xumakweathedata.ui.theme.XumakWeatheDataTheme
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by inject()

    @ExperimentalUnitApi
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

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

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
                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .background(Color.DarkGray)
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        listOf(0, 1, 2, 3, 4, 5, 6).forEach { _ ->
                            DayWeather()
                        }
                    }
                    Column(
                        modifier = Modifier
                            .weight(3f)
                            .background(Color.Yellow)
                            .fillMaxWidth()
                            .fillMaxHeight()
                    ) {
                        HourlyWeatherList()
                    }
                }
            }
        }
    }
}

@Composable
fun DayWeather() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        Text("Mon")
        Icon(
            imageVector = Icons.Filled.MailOutline,
            contentDescription = "Day",
            tint = Color.White
        )
        Text("72°")
    }
}

@Composable
fun HourlyWeatherList() {

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

    val hourlyWeatherList = hourlyWeatherMap.entries.sortedBy { it.key.value }.map { it.value }

    LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)){
        items(hourlyWeatherList){ hourlyWeather ->
            HourlyWeatherItem(hourlyWeather)
        }
    }

}

@Composable
fun HourlyWeatherItem(hourlyWeather: HourlyWeather) {
    Row(modifier = Modifier.fillMaxHeight().fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = "Weather Icon",
            tint = Color.White
        )

        Text(text = hourlyWeather.hour.to12HoursString())
        Text(text = "${hourlyWeather.temperature}°")
        Text(text = hourlyWeather.rainChance.toPercentageString())
        Text(text = "${hourlyWeather.windSpeed}")
        Text(text = hourlyWeather.humidity.toPercentageString())
    }
}
