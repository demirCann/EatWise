package com.demircandemir.relaysample.presentation.screens.meals

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.demircandemir.relaysample.R
import com.demircandemir.relaysample.navigation.Screens

@Composable
fun Carousel(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {


    val carouselItems = listOf(
        CarouselItemData(
            mealName = "Breakfast",
            mealImage = painterResource(id = R.drawable.breakfast_image),
            mealRoute = Screens.BreakfastMeals.route
        ),
        CarouselItemData(
            mealName = "Lunch",
            mealImage = painterResource(id = R.drawable.lunch_image),
            mealRoute = Screens.LunchMeals.route
        ),
        CarouselItemData(
            mealName = "Dinner",
            mealImage = painterResource(id = R.drawable.meat_food),
            mealRoute = Screens.DinnerMeals.route
        ),
        CarouselItemData(
            mealName = "Snack",
            mealImage = painterResource(id = R.drawable.snacks_image),
            mealRoute = Screens.SnacksMeals.route
        ),
    )


    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(carouselItems) {
            CarouselItem(
                mealData = it,
                onClickedItem = {
                    navController.navigate(it.mealRoute)
                }
            )
        }
    }


}



@Composable
fun CarouselItem(
    mealData: CarouselItemData,
    onClickedItem: () -> Unit
) {

    Box(
        modifier = Modifier
            .size(width = 120.dp, 160.dp)
            .clip(shape = RoundedCornerShape(18.dp))
            .paint(
                painter = mealData.mealImage,
                contentScale = ContentScale.Crop
            )
            .clickable {
                Log.d("CarouselItem", "onClickedItem: ")
                onClickedItem()
            },
        contentAlignment = Alignment.BottomStart

    ) {
        Text(
            text = mealData.mealName,
            modifier = Modifier.padding(start = 16.dp, bottom = 8.dp),
            color = Color.White,
            style = MaterialTheme.typography.titleLarge,
        )
    }
}

@Preview
@Composable
fun CarouselItemPreview() {

    /*CarouselItem(
        mealName = "Dinner",
        onClickedItem = {}
    )
     */
}



data class CarouselItemData(
    val mealName: String,
    val mealImage: Painter,
    val mealRoute: String
)






