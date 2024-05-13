package com.demircandemir.relaysample.util

import java.util.Calendar
import java.util.GregorianCalendar

fun getDefaultDateInMillis(): Long {
    var cal = Calendar.getInstance()
    val year = cal[Calendar.YEAR]
    val month = cal[Calendar.MONTH]
    val date = cal[Calendar.DATE]
    cal.clear()
    cal = GregorianCalendar(year, month, date)
    return cal.timeInMillis
}