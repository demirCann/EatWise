package com.demircandemir.relaysample.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.demircandemir.relaysample.R
import com.demircandemir.relaysample.domain.model.UserInfo
import com.demircandemir.relaysample.presentation.screens.login.UserData
import com.google.firebase.auth.FirebaseAuth
import java.text.DecimalFormat

@Composable
fun ProfileCard(
    userInfo: UserInfo,
    paddingValues: PaddingValues
) {

//    LaunchedEffect(true) {
//
//        val userData = FirebaseAuth.getInstance().currentUser?.id
//
//        val userData = UserInfo(
//            id = 1,
//            name = "Demircan Demir",
//            image = "https://www.google.com",
//            goal = "Gain Weight",
//            weight = 63,
//            height = 175,
//
//    }

    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues)
            .padding(start = 16.dp, end = 16.dp, top = 16.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            AsyncImage(
                model = FirebaseAuth.getInstance().currentUser?.photoUrl,
                contentDescription = stringResource(R.string.user_image),
                modifier = Modifier
                    .size(96.dp)
                    .padding(16.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )


//            Image(
//                modifier = Modifier
//                    .size(72.dp),
//                imageVector = Icons.Filled.AccountCircle,
//                contentDescription = stringResource(R.string.person_image)
//            )


            Column(
                modifier = Modifier
                    .padding(top = 14.dp, start = 16.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = userInfo.name,
                    style = MaterialTheme.typography.titleLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Row {

                    Text(
                        text = userInfo.age.toString(),
                        style = MaterialTheme.typography.bodyLarge,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "years old",
                        style = MaterialTheme.typography.bodyLarge,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Text(
                    text = userInfo.gender,
                    style = MaterialTheme.typography.bodyLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )


            }
            

        }

        Spacer(modifier = Modifier.height(16.dp))

        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        ProfileInfoRow(label = "Current Weight", value = "${userInfo.weight} kg")
        Spacer(modifier = Modifier.height(8.dp))
        ProfileInfoRow(label = "Height", value = "${userInfo.height} cm")
        Spacer(modifier = Modifier.height(8.dp))
        ProfileInfoRow(label = "Goal", value = userInfo.goal)
        Spacer(modifier = Modifier.height(8.dp))
        ProfileInfoRow(label = "Weight Goal", value = "${userInfo.weight_goal} kg")
        Spacer(modifier = Modifier.height(8.dp))
        ProfileInfoRow(label = "Exercise Day in a Week", value = userInfo.exercise_amount.toString())
        Spacer(modifier = Modifier.height(8.dp))
        ProfileInfoRow(label = "Time Frame", value = "${userInfo.time_frame} days")
        Spacer(modifier = Modifier.height(8.dp))
        val decimalFormat = DecimalFormat("#.00")
        val formattedIntake = decimalFormat.format(userInfo.calculated_intake)
        ProfileInfoRow(label = "Calculated Intake", value = "$formattedIntake kcal/day")



    }


}



@Composable
fun ProfileInfoRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 8.dp),  // Her bir satırın üst ve altına boşluk eklemek için padding kullanıyoruz
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}


@Preview
@Composable
fun ProfileCardPreview() {
    //ProfileCard()
}


