package com.demircandemir.relaysample.presentation.screens.survey.question

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.demircandemir.relaysample.presentation.screens.survey.QuestionWrapper

@Composable
fun FillQuestion(
    @StringRes titleResourceId: Int,
    text: String,
    unit: UnitClass,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {

    QuestionWrapper(
        titleResourceId = titleResourceId,
        modifier = modifier,
    ) {

        TextFieldRow(
            text = text,
            unit = unit,
            onTextChange = onTextChange,
            modifier = modifier.padding(start = 16.dp,top = 250.dp)
            )

    }

}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldRow(
    text: String,
    unit: UnitClass,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.Bottom,
    ) {

        TextField(
            value = text,
            onValueChange = { onTextChange(it) },
            singleLine = true,
            shape = TextFieldDefaults.outlinedShape,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.surface,
                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            ),
        )
        
        Spacer(modifier = modifier.width(8.dp))

        Text(
            text = unit.name,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary,

        )
    }


}

enum class UnitClass {
    CM, KG, YEARS, DAY
}


@Preview
@Composable
fun TextFieldRowPreview() {
    TextFieldRow(
        text = "Text",
        unit = UnitClass.CM,
        onTextChange = { }
    )


}







