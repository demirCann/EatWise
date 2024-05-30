package com.demircandemir.relaysample.presentation.screens.meals

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
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
            mealImageUri = "https://drive.google.com/uc?export=view&id=1ymE1mvU-s2HW3uUoXwULZJX1KM2nGeHo",
            mealRoute = Screens.BreakfastMeals.passRepast("Breakfast")
        ),
        CarouselItemData(
            mealName = "Lunch",
            mealImageUri = "https://drive.google.com/uc?export=view&id=1pucK0wZvnWFs7ts-55SYusfwTM0KB7j5",
            mealRoute = Screens.BreakfastMeals.passRepast("Lunch")
        ),
        CarouselItemData(
            mealName = "Dinner",
            mealImageUri = "https://drive.google.com/uc?export=view&id=1EwFV2tqbuhw9zyl2cNMrvJynRjOspaWg",
            mealRoute = Screens.BreakfastMeals.passRepast("Dinner")
        ),
        CarouselItemData(
            mealName = "Snack",
            mealImageUri = "https://drive.google.com/uc?export=view&id=1wyDwd6C_iI9ZzlTbjUnaSWiOFyO6uEId",
            mealRoute = Screens.BreakfastMeals.passRepast("Snack")
        ),
    )


    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(carouselItems, key = { it.mealName }) {
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
            .background(Color.DarkGray)
            .clickable {
                Log.d("CarouselItem", "onClickedItem: ")
                onClickedItem()
            },
        contentAlignment = Alignment.BottomStart

    ) {
        Image(
            painter = rememberAsyncImagePainter(model = mealData.mealImageUri),
            contentDescription = mealData.mealName,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 4.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = mealData.mealName,
                color = Color.White,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }
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
    val mealImageUri: String,
    val mealRoute: String
)






