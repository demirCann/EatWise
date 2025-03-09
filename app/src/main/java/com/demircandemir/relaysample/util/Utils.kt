package com.demircandemir.relaysample.util

import java.text.DecimalFormat

fun formatCalculatedIntake(calculatedIntake: Any?): String {
    return try {
        val intakeValue = calculatedIntake?.toString()?.toDoubleOrNull()
        if (intakeValue != null) {
            val decimalFormat = DecimalFormat("#.00")
            "${decimalFormat.format(intakeValue)} kcal/day"
        } else {
            "N/A"
        }
    } catch (e: Exception) {
        "N/A"
    }
}