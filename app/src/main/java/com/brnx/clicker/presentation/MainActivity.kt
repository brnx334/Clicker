package com.brnx.clicker.presentation

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.core.app.ActivityCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.brnx.clicker.R
import com.brnx.clicker.database.connect.AppDatabase
import com.brnx.clicker.database.repository.EnhancementRepositoryImpl
import com.brnx.clicker.database.repository.UserRepositoryImpl
import com.brnx.clicker.navigation.Screen
import com.brnx.clicker.presentation.clicker.UserViewModel
import com.brnx.clicker.presentation.main.MainScreen
import com.brnx.clicker.presentation.setting.SettingScreen
import com.brnx.clicker.presentation.shop.ShopScreen
import com.brnx.clicker.presentation.shop.ShopViewModel
import com.brnx.clicker.ui.theme.ClickerTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.android.gms.games.AuthenticationResult
import com.google.android.gms.games.PlayGames
import com.google.android.gms.games.PlayGamesSdk
import com.google.android.gms.tasks.Task


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ApplicationContextProvider.initialize(this)
        val database = AppDatabase.getInstance(applicationContext, isDarkThemeEnabled(this))
        val userViewModel = UserViewModel(UserRepositoryImpl(database.userDao()))
        val shopViewModel = ShopViewModel(EnhancementRepositoryImpl(database.enhancementDao()))
        val userState = mutableStateOf(userViewModel.getReadOnlyUser())

        PlayGamesSdk.initialize(this)

        val gamesSignInClient = PlayGames.getGamesSignInClient(this)

        gamesSignInClient.isAuthenticated.addOnCompleteListener { isAuthenticatedTask: Task<AuthenticationResult> ->
            val isAuthenticated = isAuthenticatedTask.isSuccessful &&
                    isAuthenticatedTask.result.isAuthenticated
            if (isAuthenticated) {

                submitScore(this, userState.value.userScore.toLong())

                Log.i("TAG", "onCreate: Authenticated")
            } else {
                Log.i("TAG", "onCreate:  NO Authenticated")

            }
        }


        setContent {

                    ClickerTheme(userState.value.isDarkModeActive) {
                        //set system ui theming
                        val color = MaterialTheme.colorScheme.primary
                        val systemUiController = rememberSystemUiController()

                        SideEffect {
                            systemUiController.setSystemBarsColor(color)
                        }
                        NavigationSystem(userViewModel, shopViewModel)
                    }
                }
        }
    }


@Composable
fun NavigationSystem(
    userViewModel: UserViewModel,
    shopViewModel: ShopViewModel,
){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.MainScreen.route
    ){
        composable(route = Screen.MainScreen.route) {
            MainScreen(
                userViewModel = userViewModel,
                navController = navController
            )
        }
        composable(route = Screen.ShopScreen.route) {
            ShopScreen(
                navController = navController,
                shopViewModel = shopViewModel,
                userViewModel = userViewModel)
        }
        composable(route = Screen.SettingScreen.route){
            SettingScreen(
                navController = navController,
                userViewModel = userViewModel)
        }
            
        
    }
}

object ApplicationContextProvider {
    private var appContext: Context? = null

    fun initialize(context: Context) {
        appContext = context
    }

    fun getContext(): Context {
        return appContext ?: throw IllegalStateException("Context is not initialized")
    }
}

fun isDarkThemeEnabled(context: Context): Boolean {
    val currentNightMode = context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
    return currentNightMode == Configuration.UI_MODE_NIGHT_YES
}

private fun showLeaderboard(activity: Activity) {
    PlayGames.getLeaderboardsClient(activity)
        .getLeaderboardIntent(activity.getString(R.string.leaderboard_id))
        .addOnSuccessListener { intent ->
            ActivityCompat.startActivityForResult(
                activity,
                intent,
                0, null
            )
        }
}

private fun submitScore(activity: Activity, score: Long){
    PlayGames.getLeaderboardsClient(activity)
        .submitScore(
            activity.getString(R.string.leaderboard_id),
            score
        )
}

private fun getLeaderboardActivity(activity: Activity, score: Long){
    submitScore(activity, score)
    showLeaderboard(activity)
}
