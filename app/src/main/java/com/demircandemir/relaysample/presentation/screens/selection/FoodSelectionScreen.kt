package com.demircandemir.relaysample.presentation.screens.selection

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.demircandemir.relaysample.domain.model.MealsRequest
import com.demircandemir.relaysample.navigation.Screens
import com.demircandemir.relaysample.presentation.common.ListContent
import com.demircandemir.relaysample.presentation.screens.meals.MealsTopBar
import com.google.firebase.auth.FirebaseAuth

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FoodSelectionScreen(
    selectionScreenViewModel: SelectionScreenViewModel = hiltViewModel(),
    navController: NavHostController
) {

    val repast = selectionScreenViewModel.repast
    selectionScreenViewModel.getMealsForSelection(repast!!)

    val meals = selectionScreenViewModel.meals.collectAsLazyPagingItems()

    val userId = FirebaseAuth.getInstance().currentUser?.uid

    Scaffold(
        topBar = {
            MealsTopBar(
                topBarName = repast,
                onSearchClicked = {
                    navController.navigate(Screens.Search.route)
                },
                onBackClicked = {
                    navController.popBackStack()
                }
            )
        },
        floatingActionButton = {
            Button(
                onClick = {

                    selectionScreenViewModel.putDietPlan(
                        userId = 1,
                        repast = repast,
                        meals = MealsRequest(
                            meals = selectionScreenViewModel.selectedMeals.value
                        )
                    )
                    navController.popBackStack()
//
//
//
//                          selectionScreenViewModel.postDietPlan(
//                              userId = 2,
//                              repast = repast,
//                              meals = "{${selectionScreenViewModel.selectedMeals.joinToString(separator = ",")}}"
//                          )
                },
                modifier = Modifier
                    .fillMaxWidth(0.94f)
                    .height(50.dp),
                shape = RoundedCornerShape(100.dp),
            ) {
                Text(text = "Done")
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        content = {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(it)
            ) {

                ListContent(
                    meals = meals,
                    isSelectionScreen = true,
                    modifier = Modifier.padding(bottom = 60.dp),
                    navController = navController
                )


            }


        }
    )

}

@Preview
@Composable
fun FoodSelectionScreenPreview() {
    FoodSelectionScreen(navController = rememberNavController())
}