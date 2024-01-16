package com.demircandemir.relaysample.presentation.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.demircandemir.relaysample.R
import com.demircandemir.relaysample.domain.model.MealInfo
import com.demircandemir.relaysample.navigation.Screens


@Composable
fun MealAddRows(
    navController: NavHostController,
) {


    val meals = listOf(
        MealAddRow(
            name = "Breakfast",
            recommendedCalorie = "820 - 1093 kcal",
            image = painterResource(id = R.drawable.breakfast_icon),
            route = Screens.BreakfastMeals.route
        ),
        MealAddRow(
            name = "Lunch",
            recommendedCalorie = "820 - 1093 kcal",
            image = painterResource(R.drawable.lunch_icon),
            route = Screens.LunchMeals.route
        ),
        MealAddRow(
            name = "Dinner",
            recommendedCalorie = "820 - 1093 kcal",
            image = painterResource(R.drawable.dinner_icon),
            route = Screens.DinnerMeals.route
        ),
        MealAddRow(
            name = "Snack",
            recommendedCalorie = "820 - 1093 kcal",
            image = painterResource(R.drawable.snacks_icon),
            route = Screens.SnacksMeals.route
        ),
    )


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
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
            .height(164.dp)
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
                painter = mealAddRow.image,
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

                Text(
                    text = "Recommended: ${mealAddRow.recommendedCalorie}",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.ExtraLight
                )
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
    val image: Painter,
    val selectedMeal: String? = null,
    val route : String
)




@Preview()
@Composable
fun MealAddRowsPreview() {
    /*MealAddRows(
        navController = rememberNavController()
    )

     */
}



