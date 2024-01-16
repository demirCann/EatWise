package com.demircandemir.relaysample.presentation.screens.details

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.demircandemir.relaysample.R
import com.demircandemir.relaysample.domain.model.MealInfo
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DetailsContent(
    meal: MealInfo,
    onFinishedClicked : () -> Unit
) {

    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Expanded)
    )


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
        content = {
            BackgroundContent(
                onCloseClicked = {}
            )
        }
    )

}




@OptIn(ExperimentalFoundationApi::class, ExperimentalPagerApi::class)
@Composable
fun LineIndicatorExample(
    meal: MealInfo,
    onFinishedClicked : () -> Unit
) {
    Column(modifier  = Modifier.heightIn(min = 100.dp, max = 500.dp)){


        val pageCount = 5
        val pagerState = androidx.compose.foundation.pager.rememberPagerState(pageCount = { pageCount + 1 })


        HorizontalPager(
            modifier = Modifier.fillMaxSize(),
            beyondBoundsPageCount = 4,
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
    onCloseClicked: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(1f)
                .align(Alignment.TopStart),
            painter = painterResource(id = R.drawable.meat_food3),
            contentDescription = "Meat Food",
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


@OptIn(ExperimentalFoundationApi::class, ExperimentalPagerApi::class)
@Composable
fun PagerSampleItem(
    meal: MealInfo,
    pageNumber: Int,
    pagerState: androidx.compose.foundation.pager.PagerState,
    onFinishedClicked : () -> Unit
) {


    //StepPage(pageNumber = pageNumber, pagerState = pagerState, onFinishedClicked = onFinishedClicked)


    if (pagerState.currentPage == 0) {
        StartPage(
            meal = meal,
            pagerState = pagerState
        )

    } else {
        StepPage(
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

        Text(
            text = "Launch/ 15 min",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Thin
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
                    text = meal.calorie,
                    style = MaterialTheme.typography.bodyMedium,
                )
                    Text(
                        text = "Energy",
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

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "2 serves",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Thin
            )
        }


        Row(
            modifier = Modifier
                .padding(start = 18.dp, top = 16.dp, end = 16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Chicken breasts",
                style = MaterialTheme.typography.bodyMedium,
            )

            Text(
                text = "250 g",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Thin
            )
        }


        Row(
            modifier = Modifier
                .padding(start = 18.dp, top = 16.dp, end = 16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Unslated butter",
                style = MaterialTheme.typography.bodyMedium,
            )

            Text(
                text = "1 tbsp",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Thin
            )
        }

        Row(
            modifier = Modifier
                .padding(start = 18.dp, top = 16.dp, end = 16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Sesame or vegatable oil",
                style = MaterialTheme.typography.bodyMedium,
            )

            Text(
                text = "2 tbsp",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Thin
            )
        }

        Row(
            modifier = Modifier
                .padding(start = 18.dp, top = 16.dp, end = 16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Fresh ginger",
                style = MaterialTheme.typography.bodyMedium,
            )

            Text(
                text = "2 tbsp",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Thin
            )
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StepPage(
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

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Bacon",
                style = MaterialTheme.typography.titleLarge,
            )

            Text(
                text = "50 g",
                style = MaterialTheme.typography.bodyMedium,
            )
        }

        Divider()

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Soy sauce",
                style = MaterialTheme.typography.titleLarge,
            )

            Text(
                text = "200 ml",
                style = MaterialTheme.typography.bodyMedium,
            )
        }
        Divider()

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp),
            text = "We tie the bacon with twine so that the skin is on the outside and one end and the other practically meet. Heat a little oil in a pressure cooker and mark the bacon all over until golden brown. We remove and discard the oil.",
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



@OptIn(ExperimentalFoundationApi::class, ExperimentalPagerApi::class)
@Preview
@Composable
fun PagerSampleItemPreview() {
    //PagerSampleItem(pageNumber = 3, pagerState = androidx.compose.foundation.pager.rememberPagerState(pageCount = { 3 }))
}


