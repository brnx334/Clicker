package com.brnx.clicker.presentation.main

import android.app.Activity
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.navigation.NavController
import com.brnx.clicker.R
import com.brnx.clicker.navigation.Screen
import com.brnx.clicker.presentation.ApplicationContextProvider
import com.brnx.clicker.presentation.clicker.ClickCounter
import com.brnx.clicker.presentation.clicker.UserViewModel
import com.google.android.gms.games.PlayGames


@Composable
fun MainScreen(
    userViewModel: UserViewModel,
    navController: NavController
){

    Scaffold (
        bottomBar = { ClickerBottomNavigation(navController, userViewModel) }
    ){innerPadding->
        HomeScreen(userViewModel,Modifier.padding(innerPadding))
    }
}

@Composable
fun ClickerBottomNavigation(
    navController: NavController,
    userViewModel: UserViewModel
){
    val context = ApplicationContextProvider.getContext()

    NavigationBar (
        containerColor = Color.Transparent,
        contentColor = Color.Cyan){
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.List,
                    contentDescription = null,
                )
            },
            label = {
                Text(text = stringResource(R.string.leaderboard))
            },
            selected = true,
            onClick = {
                getLeaderboardActivity(context as Activity,userViewModel.user.value.userScore.toLong())
                      },
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = MaterialTheme.colorScheme.primary)
        )
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = null,
                )
            },
            label = {
                Text(text = stringResource(R.string.shop))
            },
            selected = true,
            onClick = {
                navController.navigate(Screen.ShopScreen.route)},
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = MaterialTheme.colorScheme.primary)
        )
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = null,
                )
            },
            label = {
                Text(text = stringResource(com.brnx.clicker.R.string.setting))
            },
            selected = true,
            onClick = {
                navController.navigate(Screen.SettingScreen.route)},
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = MaterialTheme.colorScheme.primary)
        )
    }
}


@Composable
fun HomeScreen(userViewModel: UserViewModel, modifier: Modifier = Modifier) {
    val user = userViewModel.getReadOnlyUser()
    ClickCounter(user){userViewModel.increaseUserScore()}
}


private fun showLeaderboard(leaderboardId:String, activity: Activity) {
    PlayGames.getLeaderboardsClient(activity)
        .getLeaderboardIntent(leaderboardId)
        .addOnSuccessListener { intent ->
            startActivityForResult(
                activity,
                intent,
                0,null
            )
        }
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
