package com.demircandemir.relaysample.presentation.screens.home

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.demircandemir.relaysample.R
import com.demircandemir.relaysample.data.remote.EatWiseApi
import com.demircandemir.relaysample.domain.model.MealInfo
import com.demircandemir.relaysample.navigation.Screens
import javax.inject.Inject


@Composable
fun MealAddRows(
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val meals by viewModel.meals.collectAsState()



    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, bottom = 48.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        meals.forEach { meal ->
            MealAddRowItem(
                mealAddRow = meal
            ) {
                navController.navigate(Screens.FoodSelection.passRepast(meal.name))
            }
        }
    }
}







@Composable
fun MealAddRowItem(
    mealAddRow: MealAddRow,
    onMealClicked: () -> Unit
) {



    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.tertiaryContainer)
            .clickable {
                onMealClicked()
            },
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = mealAddRow.imageRes),
                contentDescription = "stringResource(R.string.add_meal_row)",
                modifier = Modifier
                    .padding(8.dp)
                    .weight(1f)
                    .clip(CircleShape)
            )

            Column(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .weight(4f)
            ) {
                Text(
                    text = mealAddRow.name,
                    style = MaterialTheme.typography.bodyLarge,
                )

//                Text(
//                    text = "Recommended: ${mealAddRow.recommendedCalorie}",
//                    style = MaterialTheme.typography.bodySmall,
//                    fontWeight = FontWeight.ExtraLight
//                )

                if(mealAddRow.meals.isNotEmpty()){
                    Spacer(modifier = Modifier.height(8.dp))
                    mealAddRow.meals.forEach { mealName ->
                        Log.d("MealAddRowItem", "MealAddRowItem: ${mealName.name}")

                        Text(
                            text = mealName.name,
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Light
                        )
                    }
                }




            }

            Icon(
                modifier = Modifier
                    .size(36.dp)
                    .weight(1f),
                imageVector = Icons.Filled.AddCircle,
                contentDescription = "Add Button"
            )
        }
    }
}



data class MealAddRow(
    val name: String,
    val recommendedCalorie: String,
    @DrawableRes val imageRes: Int,
    val route: String,
    val meals: List<MealInfo> = emptyList()
)



