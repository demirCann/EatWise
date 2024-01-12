package com.demircandemir.relaysample.presentation.screens.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.himanshoe.charty.gauge.GaugeChart
import com.himanshoe.charty.gauge.config.GaugeChartConfig

@Composable
fun GaugeGraph(
    value: Int,
    modifier: Modifier
) {
    GaugeChart(
        percentValue = value,
        modifier = modifier
            .size(128.dp),
        gaugeChartConfig = GaugeChartConfig(
            placeHolderColor = MaterialTheme.colorScheme.outlineVariant,
            primaryColor = MaterialTheme.colorScheme.primary,
            strokeWidth = 20.dp.value,
            showNeedle = false,
            showIndicator = false,
            indicatorColor = MaterialTheme.colorScheme.error,
            indicatorWidth = 2.dp.value
        )
    )
}


@Preview
@Composable
fun GaugeGraphPreview() {
    //GaugeGraph(50)
}