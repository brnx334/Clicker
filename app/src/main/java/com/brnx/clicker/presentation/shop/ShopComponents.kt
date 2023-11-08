package com.brnx.clicker.presentation.shop

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.brnx.clicker.R
import com.brnx.clicker.domain.model.Enhancement
import com.brnx.clicker.presentation.ApplicationContextProvider
import es.dmoral.toasty.Toasty


@Composable
fun ProductList(
    enhancements: List<Enhancement>,
    onPurchase: (Enhancement) -> Any
){

    LazyColumn(modifier = Modifier
        .fillMaxWidth(1F)
        .background(Color.Transparent),
    ){
        items(enhancements){  enhancement ->
            ProductCardTemplate(enhancement){item -> onPurchase(item)}
        }
    }
}

@SuppressLint("CheckResult")
@Composable
fun ProductCardTemplate(
    enhancement: Enhancement,
    onPurchase: (Enhancement) -> Any
) {
    var isPurchaseSuccessful = true
    val additionalPoint = enhancement.additionalPoint

    var enhancementPrice by remember {
        mutableIntStateOf(enhancement.enhancementPrice)
    }
    var enhancementOwned by remember {
        mutableIntStateOf(enhancement.enhancementOwned)
    }
    val context = ApplicationContextProvider.getContext()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(16.dp))
            .clickable {
                val result = onPurchase(enhancement)

                if ( result is Boolean){
                    isPurchaseSuccessful = result
                    Toasty.warning(context,"Not enough points",Toasty.LENGTH_SHORT).show()

                } else if (result is Enhancement){
                    enhancementPrice = result.enhancementPrice
                    enhancementOwned = result.enhancementOwned
                }
            },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = "+${additionalPoint} p/c",
            color = MaterialTheme.colorScheme.onPrimary,
            fontSize = 30.sp,
            fontFamily = FontFamily(Font(R.font.montserrat_semibold))
        )
        Column (
            horizontalAlignment = Alignment.End
        ){
            Text(
                modifier = Modifier.padding(top = 8.dp, end = 16.dp),
                text = "$enhancementPrice",
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 25.sp,
                fontFamily = FontFamily(Font(R.font.montserrat_semibold)),
            )

            Text(
                modifier = Modifier.padding(bottom = 8.dp, end = 16.dp),
                text = "Owned: $enhancementOwned",
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 15.sp,
                fontFamily = FontFamily(Font(R.font.montserrat_regular)),
            )
        }
    }

}
