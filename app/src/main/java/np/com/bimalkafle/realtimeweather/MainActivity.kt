package np.com.bimalkafle.realtimeweather

import np.com.bimalkafle.realtimeweather.Repository.GoalRepository
import np.com.bimalkafle.realtimeweather.database.GoalDatabase
import np.com.bimalkafle.realtimeweather.viewmodel.GoalViewModel

//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Surface
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.lifecycle.ViewModelProvider
//import np.com.bimalkafle.realtimeweather.ui.theme.RealtimeWeatherTheme
//
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        val weatherViewModel = ViewModelProvider(this)[WeatherViewModel::class.java]
//
//        setContent {
//            RealtimeWeatherTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                   WeatherPage(weatherViewModel)
//                }
//            }
//        }
//    }
//}
//
//


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

import np.com.bimalkafle.realtimeweather.ui.theme.RealtimeWeatherTheme  // ← Make sure this import exists

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dao = GoalDatabase.getDatabase(application).goalDao()
        val repository = GoalRepository(dao)
        val factory = GoalViewModelFactory(repository)

        setContent {
            RealtimeWeatherTheme {  // ← Fixed: Use your actual theme name
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel: GoalViewModel = viewModel(factory = factory)
                    GoalListScreen(viewModel)
                }
            }
        }
    }
}

class GoalViewModelFactory(private val repository: GoalRepository) :
    androidx.lifecycle.ViewModelProvider.Factory {
    override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GoalViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GoalViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}