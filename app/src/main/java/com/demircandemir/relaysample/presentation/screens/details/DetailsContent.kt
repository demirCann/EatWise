package com.demircandemir.relaysample.presentation.screens.details

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.demircandemir.relaysample.R
import com.demircandemir.relaysample.domain.model.MealInfo
import kotlinx.coroutines.launch

@Composable
fun DetailsContent(
    meal: MealInfo,
    onFinishedClicked : () -> Unit
) {

    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Expanded)
    )

    LaunchedEffect(scaffoldState.bottomSheetState) {
        scaffoldState.bottomSheetState.expand()
    }

    BottomSheetScaffold(
        sheetShape = RoundedCornerShape(topStart = 18.dp, topEnd = 18.dp),
        scaffoldState = scaffoldState,
        sheetPeekHeight = 240.dp,
        sheetContent = {
            LineIndicatorExample(
                meal = meal,
                onFinishedClicked = {
                    onFinishedClicked()
                }
            )
        },
        content = { paddingValues ->
            BackgroundContent(
                meal = meal,
                paddingValues = paddingValues,
                onCloseClicked = {
                    onFinishedClicked()
                }
            )
        }
    )
}

@Composable
fun LineIndicatorExample(
    meal: MealInfo,
    onFinishedClicked : () -> Unit
) {
    Column(modifier  = Modifier.heightIn(min = 100.dp, max = 500.dp)){

        val pageCount = meal.recipe.size
        val pagerState = androidx.compose.foundation.pager.rememberPagerState(pageCount = { pageCount + 1 })

        HorizontalPager(
            modifier = Modifier.fillMaxSize(),
            state = pagerState)
        {
            PagerSampleItem(
                meal = meal,
                pageNumber = pageCount,
                pagerState = pagerState,
                onFinishedClicked = {
                    onFinishedClicked()
                }
            )
        }

    }
}

@Composable
fun BackgroundContent(
    meal: MealInfo,
    paddingValues: PaddingValues,
    onCloseClicked: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(MaterialTheme.colorScheme.surface)
    ) {

        AsyncImage(
            model = meal.image,
            contentDescription = "Meat Food",
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.TopStart),
            contentScale = ContentScale.Crop
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {

            IconButton(
                modifier = Modifier.padding(all = 8.dp),
                onClick = { onCloseClicked() }
            ) {
                Icon(
                    modifier = Modifier.size(16.dp),
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close Icon",
                    tint = Color.White
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LineIndicatorExamplePreview() {
    //LineIndicatorExample()
}

@Composable
fun PagerSampleItem(
    meal: MealInfo,
    pageNumber: Int,
    pagerState: androidx.compose.foundation.pager.PagerState,
    onFinishedClicked : () -> Unit
) {
    if (pagerState.currentPage == 0) {
        StartPage(
            meal = meal,
            pagerState = pagerState
        )

    } else {
        StepPage(
            meal = meal,
            pageNumber = pageNumber,
            pagerState = pagerState,
            onFinishedClicked = {
                onFinishedClicked()
            }
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StartPage(
    meal: MealInfo,
    pagerState: androidx.compose.foundation.pager.PagerState
) {

    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .padding(top = 16.dp),
    ) {
        Text(
            text = meal.name,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.headlineLarge,
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .height(60.dp)
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.tertiaryContainer),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "${meal.calorie} kcal",
                    style = MaterialTheme.typography.bodyMedium,
                )
                Text(
                    text = "Calories",
                    style = MaterialTheme.typography.titleMedium,
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "${meal.protein} g",
                    style = MaterialTheme.typography.bodyMedium,
                )
                Text(
                    text = "Protein",
                    style = MaterialTheme.typography.titleMedium,
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "${meal.carbohydrate} g",
                    style = MaterialTheme.typography.bodyMedium,
                )
                Text(
                    text = "Carbs",
                    style = MaterialTheme.typography.titleMedium,
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "${meal.fat} g",
                    style = MaterialTheme.typography.bodyMedium,
                )
                Text(
                    text = "Fat",
                    style = MaterialTheme.typography.titleMedium,
                )
            }
        }



        Row(
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Ingredients",
                style = MaterialTheme.typography.titleMedium,
            )
        }


        meal.ingredients.forEach {
            Row(
                modifier = Modifier
                    .padding(start = 18.dp, top = 16.dp, end = 16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.CenterHorizontally),
            contentAlignment = Alignment.BottomCenter,
        ) {
            Button(
                onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth(0.94f),
                shape = RoundedCornerShape(100.dp),
            ) {
                Text(text = "Start Cooking")
            }
        }
    }
}

@Composable
fun StepPage(
    meal: MealInfo,
    pageNumber: Int,
    pagerState: androidx.compose.foundation.pager.PagerState,
    onFinishedClicked : () -> Unit
) {

    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Step ${pagerState.currentPage}",
            style = MaterialTheme.typography.headlineLarge,
        )

        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(pageNumber) { iteration ->
                val color =
                    if (pagerState.currentPage - 1 == iteration) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onPrimary
                val tintColor =
                    if (pagerState.currentPage - 1 == iteration) Color.White else MaterialTheme.colorScheme.primary
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .clip(CircleShape)
                        .border(1.dp, MaterialTheme.colorScheme.primary, CircleShape)
                        .background(color)
                        .size(32.dp)
                ) {
                    Text(
                        text = (iteration + 1).toString(),
                        modifier = Modifier.align(Alignment.Center),
                        color = tintColor
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        HorizontalDivider()

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp),
            text =  meal.recipe[pagerState.currentPage - 1],
            style = MaterialTheme.typography.bodyMedium,
        )

        if (pagerState.currentPage == pagerState.pageCount - 1) {
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Button(
                    onClick = {
                        Log.d("Pager", "PagerStatePrevious: ${pagerState.pageCount}")
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage - 1)
                        }
                    },
                    modifier = Modifier
                        .width(170.dp),
                    colors = androidx.compose.material.ButtonDefaults.buttonColors(
                        backgroundColor = Color.Gray,
                        contentColor = Color.DarkGray
                    ),
                    shape = RoundedCornerShape(100.dp),
                ) {
                    Text(text = "Previous")
                }

                Button(
                    onClick = {
                        Log.d("Pager", "PagerStateNext: ${pagerState.currentPage}")
                        coroutineScope.launch {
                            onFinishedClicked()
                        }
                    },
                    modifier = Modifier
                        .width(170.dp),
                    colors = androidx.compose.material.ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colorScheme.secondary,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(100.dp),
                ) {
                    Text(text = "Finish Cooking")
                }
            }
        } else {
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                        Log.d("Pager", "PagerStatePrevious: ${pagerState.pageCount}")
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage - 1)
                        }
                    },
                    modifier = Modifier
                        .width(170.dp),
                    colors = androidx.compose.material.ButtonDefaults.buttonColors(
                        backgroundColor = Color.Gray,
                        contentColor = Color.DarkGray
                    ),
                    shape = RoundedCornerShape(100.dp),
                ) {
                    Text(text = "Previous")
                }

                Button(
                    onClick = {
                        Log.d("Pager", "PagerStateNext: ${pagerState.currentPage}")
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    },
                    modifier = Modifier
                        .width(170.dp),
                    colors = androidx.compose.material.ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colorScheme.secondary,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(100.dp),
                ) {
                    Text(text = "Next")
                }
            }
        }
    }
}

@Preview
@Composable
fun PagerSampleItemPreview() {
    //PagerSampleItem(pageNumber = 3, pagerState = androidx.compose.foundation.pager.rememberPagerState(pageCount = { 3 }))
}


