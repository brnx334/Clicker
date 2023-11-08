package com.brnx.clicker.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.brnx.clicker.R
import com.brnx.clicker.navigation.Screen

@Composable
fun ScreenTitle(
    navController: NavController,
                    text: String, modifier: Modifier = Modifier) {
    Row(modifier
        .fillMaxWidth()
        .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ) {
        Box(modifier
            .clip(shape = MaterialTheme.shapes.medium)
            .height(88.dp)
            .background(color = MaterialTheme.colorScheme.surface)
            .padding(end = 16.dp)
            .weight(0.8f)
        ) {
            Text(
                text = text,
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 40.sp,
                modifier = Modifier
                    .align(Alignment.Center),
                fontFamily = FontFamily(Font(R.font.montserrat_regular))
            )
        }
        Spacer(modifier
            .width(16.dp))
        Image(
            painter = painterResource(id = if (isSystemInDarkTheme()) R.drawable.dark_close else R.drawable.light_close),
            contentDescription = null,
            modifier
                .size(88.dp)
                .clickable { navController.navigate(Screen.MainScreen.route) }
        )
    }
}
