package com.demircandemir.relaysample.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.demircandemir.relaysample.R
import com.demircandemir.relaysample.domain.model.MealInfo
import com.demircandemir.relaysample.navigation.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectableFoodCard(
    meal: MealInfo,
    onAddedClicked: (Int) -> Unit,
    navController: NavHostController
) {

    var selected by remember { mutableStateOf(false) }


    LaunchedEffect(key1 = selected) {
        if (selected) {
            navController.navigate(Screens.Home.passMealId(meal.id))
        }
    }

    Card(
        onClick = {
            navController.navigate(Screens.Detail.passMealInfo(meal))
        },
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .size(width = 140.dp, height = 280.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.meat_food3),
                contentDescription = stringResource(R.string.food_image),
                modifier = Modifier
                    .fillMaxWidth()
                    .size(140.dp, 150.dp),
            )

            Box() {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 0.dp)
                ) {
                    Text(
                        text = meal.name,
                        modifier = Modifier
                            .padding(top = 8.dp),
                        fontWeight = FontWeight.Bold,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.headlineMedium
                    )

                    Spacer(modifier = Modifier.padding(15.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = meal.calorie,
                            fontWeight = FontWeight.Thin,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.bodyMedium
                        )

                        IconButton(
                            onClick = {
                                //onAddedClicked(meal.id)
                                      selected = !selected
                            },
                            modifier = Modifier
                                .selectable(
                                    selected = selected,
                                    onClick = {}
                                )
                        ) {

                            if (selected) {
                                Icon(
                                imageVector = Icons.Filled.CheckCircle,
                                contentDescription = stringResource(R.string.food_card_favorite_button)
                                )
                            } else {
                                Icon(
                                    imageVector = Icons.Filled.AddCircle,
                                    contentDescription = stringResource(R.string.food_card_favorite_button)
                                )
                            }
                        }

                    }
                }
            }

        }
    }
}