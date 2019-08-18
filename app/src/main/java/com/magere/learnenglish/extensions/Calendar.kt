package com.magere.learnenglish.extensions

import java.util.*

fun today(days: Int = 0): Long {
    val calendar = Calendar.getInstance()
    calendar.set(Calendar.HOUR_OF_DAY, 0)
    calendar.set(Calendar.MINUTE, 0)
    calendar.set(Calendar.SECOND, 0)
    calendar.set(Calendar.MILLISECOND, 0)
    calendar.add(Calendar.DAY_OF_MONTH, days)
    return calendar.timeInMillis
}

fun format(date: Long): String {
    val monthName = arrayOf("Января", "Февраля", "Марта", "Апреля", "Мая", "Июня", "Июля", "Августа", "Сентября", "Октября", "Ноября", "Декабря")
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = date
    return " ${calendar.get(Calendar.DAY_OF_MONTH)} ${monthName[calendar.get(Calendar.MONTH)]}"
}