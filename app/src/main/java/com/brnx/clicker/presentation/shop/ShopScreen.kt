package com.brnx.clicker.presentation.shop

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.brnx.clicker.domain.model.Enhancement
import com.brnx.clicker.presentation.clicker.UserViewModel
import com.brnx.clicker.presentation.common.ScreenTitle

@Composable
fun ShopScreen(
    navController: NavHostController,
    shopViewModel: ShopViewModel,
    userViewModel: UserViewModel,
    modifier: Modifier = Modifier
    ) {

    fun onPurchase(enhancement: Enhancement): Any {
        Log.i("AG", "BEFORE: ${userViewModel.user}")

        if (userViewModel.isEnoughBalance(enhancement.enhancementPrice)){

            userViewModel.deductPoints(enhancement.enhancementPrice)
            userViewModel.increaseAdditionalPoint(enhancement.additionalPoint)

            shopViewModel.incrementOwned(enhancement)
            shopViewModel.increasePrice(enhancement)
            shopViewModel.updateEnhancement(enhancement)
            Log.i("AG", "AFTER: $userViewModel.user")
            return enhancement
        }
        return false
    }

    val enhancements = shopViewModel.enhancements.collectAsState(initial = emptyList())
    Column (modifier = Modifier.background(MaterialTheme.colorScheme.primary)){
        Spacer(modifier = Modifier.padding(16.dp) )
        ScreenTitle(navController,text = "Shop")
        Spacer(modifier = Modifier.padding(16.dp) )
        ProductList(enhancements.value){enhancement -> onPurchase(enhancement)}
        Spacer(modifier = Modifier.fillMaxHeight() )
    }
}


