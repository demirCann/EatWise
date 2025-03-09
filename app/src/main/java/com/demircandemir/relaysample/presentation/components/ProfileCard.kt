package com.demircandemir.relaysample.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.demircandemir.relaysample.R
import com.demircandemir.relaysample.domain.model.FirebaseUserData
import com.demircandemir.relaysample.domain.model.UserInfo
import com.demircandemir.relaysample.util.formatCalculatedIntake

@Composable
fun ProfileCard(
    userInfo: UserInfo,
    firebaseUserData: FirebaseUserData,
    paddingValues: PaddingValues
) {
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
                model = firebaseUserData.profilePictureUrl,
                contentDescription = stringResource(R.string.user_image),
                modifier = Modifier
                    .size(96.dp)
                    .padding(16.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .padding(top = 14.dp, start = 16.dp),
                verticalArrangement = Arrangement.Center
            ) {
                userInfo.name?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.titleLarge,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Row {
                    Text(
                        text = userInfo.age.toString(),
                        style = MaterialTheme.typography.bodyLarge,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = stringResource(R.string.years_old),
                        style = MaterialTheme.typography.bodyLarge,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                userInfo.gender?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodyLarge,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        HorizontalDivider(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp))
        Spacer(modifier = Modifier.height(16.dp))
        ProfileInfoRow(label = stringResource(R.string.current_weight_label), value = "${userInfo.weight} kg")
        Spacer(modifier = Modifier.height(8.dp))
        ProfileInfoRow(label = stringResource(R.string.height_label), value = "${userInfo.height} cm")
        Spacer(modifier = Modifier.height(8.dp))
        userInfo.goal?.let { ProfileInfoRow(label = stringResource(R.string.goal_label), value = it) }
        Spacer(modifier = Modifier.height(8.dp))
        ProfileInfoRow(label = stringResource(R.string.weight_goal_label), value = "${userInfo.weight_goal} kg")
        Spacer(modifier = Modifier.height(8.dp))
        ProfileInfoRow(label = stringResource(R.string.exercise_day_in_a_week_label), value = userInfo.exercise_amount.toString())
        Spacer(modifier = Modifier.height(8.dp))
        ProfileInfoRow(label = stringResource(R.string.time_frame_label), value = "${userInfo.time_frame} days")
        Spacer(modifier = Modifier.height(8.dp))

        val calculatedIntake = formatCalculatedIntake(userInfo)
        ProfileInfoRow(label = stringResource(R.string.calculated_intake_label), value = calculatedIntake)
    }
}

@Composable
fun ProfileInfoRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = 8.dp,
                horizontal = 8.dp
            ),
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