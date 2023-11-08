package com.brnx.clicker.presentation.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.brnx.clicker.R
import com.brnx.clicker.presentation.clicker.UserViewModel
import com.brnx.clicker.presentation.common.ScreenTitle

@Composable
fun SettingScreen(navController:NavHostController, userViewModel: UserViewModel) {

    val user = userViewModel.getReadOnlyUser()

    val updateUserSoundSetting: (Boolean) -> Unit = { newValue ->
        userViewModel.updateUserSoundSetting(newValue)
    }
    val updateUserDarkModeSetting: (Boolean) -> Unit = { newValue ->
        userViewModel.updateUserDarkModeSetting(newValue)
    }
    Column (modifier = Modifier.background(MaterialTheme.colorScheme.primary)){
        Spacer(modifier = Modifier.height(16.dp))
        ScreenTitle(navController = navController,text = stringResource(R.string.setting))
        SwitchButtonSound(
            stringResource(R.string.sound),
            user.isSoundActive,
            updateUserSoundSetting
        )
        SwitchButtonDarkMode(stringResource(R.string.dark_mode), user.isDarkModeActive,updateUserDarkModeSetting)
//        ButtonTemplate("About") TODO
        Spacer(modifier = Modifier.fillMaxHeight())
    }
}


@Composable
fun SwitchButtonSound(
    text: String,
    isSoundActive: Boolean,
    onSoundToggle: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .height(88.dp)
            .fillMaxWidth()
            .padding(10.dp)
            .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(16.dp))
            .clickable { onSoundToggle(!isSoundActive) },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = text,
            color = MaterialTheme.colorScheme.onPrimary,
            fontSize = 30.sp,
            fontFamily = FontFamily(Font(R.font.montserrat_regular))
        )
        Switch(
            checked = isSoundActive,
            onCheckedChange = { isChecked ->
                onSoundToggle(isChecked)
            },
            modifier = Modifier.padding(end = 24.dp)
        )
    }
}





@Composable
fun SwitchButtonDarkMode(
    text:String,
    isSoundActive: Boolean,
    onDarkModeToggle: (Boolean)-> Unit
){
    var checked = remember { mutableStateOf(isSoundActive) }
    Row(
        modifier = Modifier
            .height(88.dp)
            .fillMaxWidth()
            .padding(10.dp)
            .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(16.dp)),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = text,
            color = MaterialTheme.colorScheme.onPrimary,
            fontSize = 30.sp,
            fontFamily = FontFamily(Font(R.font.montserrat_regular))
        )
        Switch(
            checked = checked.value,
            onCheckedChange = { isChecked ->
                onDarkModeToggle(isChecked)
                checked.value = !checked.value
            },
            modifier = Modifier.padding(end = 24.dp)
        )
    }
}

@Composable
fun ButtonTemplate(
    text:String
) {
    Row(
        modifier = Modifier
            .height(88.dp)
            .fillMaxWidth()
            .padding(10.dp)
            .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(16.dp)),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = text,
            color = MaterialTheme.colorScheme.onPrimary,
            fontSize = 30.sp,
            fontFamily = FontFamily(Font(R.font.montserrat_regular))
        )
    }
}