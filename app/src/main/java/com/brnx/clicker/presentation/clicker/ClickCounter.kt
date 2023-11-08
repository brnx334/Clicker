
package com.brnx.clicker.presentation.clicker

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.brnx.clicker.R
import com.brnx.clicker.domain.model.User
import com.brnx.clicker.presentation.ApplicationContextProvider
import com.brnx.clicker.presentation.SoundManager

@Composable
fun ClickCounter (
    user: User?,
    onClick: () -> Int
) {
    val score = remember {
        mutableIntStateOf(user?.userScore!!)
    }
    val soundManager = SoundManager()


    val context = ApplicationContextProvider.getContext()

    Box(modifier = Modifier
        .background(color = MaterialTheme.colorScheme.primary)
        .fillMaxSize()
        .noRippleClickable {
            score.intValue = onClick()
            if (user?.isSoundActive == true) soundManager.playSound(
                R.raw.button_click_sound,
                context
            )
        },
        contentAlignment = Alignment.Center
    ) {
        Column (horizontalAlignment = Alignment.CenterHorizontally){
            Text(
                "${score.intValue}",
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 40.sp,
                fontFamily = FontFamily(Font(R.font.montserrat_semibold))
            )

            Text(
                stringResource(R.string.p_c, user?.userScorePerClick!!),
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily(Font(R.font.montserrat_light)),

            )
        }
    }
}

fun Modifier.noRippleClickable(onClick: () -> Unit): Modifier = composed {
    clickable(indication = null,
        interactionSource = remember { MutableInteractionSource() }) {
        onClick()
    }
}

