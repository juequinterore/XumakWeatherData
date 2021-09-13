package co.me.xumakweathedata

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import co.me.xumakweathedata.ui.search.SearchContent
import co.me.xumakweathedata.ui.theme.MainContent
import co.me.xumakweathedata.ui.theme.XumakWeatheDataTheme
import kotlinx.coroutines.FlowPreview
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by inject()

    @FlowPreview
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
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()

                    val mainActivityState: MainActivityState by viewModel.state.observeAsState(
                        MainActivityState(
                            cities = emptyList(),
                            currentDayNumber = 0,
                            currentDateText = ""
                        )
                    )

                    NavHost(navController = navController, startDestination = "main") {
                        composable("main") {
                            MainContent(
                                navController,
                                mainActivityState
                            )
                        }
                        composable("search") { SearchContent(navController) }
                    }
                }
            }
        }
    }
}
