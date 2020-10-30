package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*

const val SECONDS = 1000L
const val MINUTES = 60 * SECONDS
const val HOURS = 60 * MINUTES
const val DAYS = 24 * HOURS

fun Date.humanizeDiff(): String {
    val currentDate: Int = Date().time.toInt()
    val time = this.time
    val difference: Int = currentDate - time.toInt()
    val seconds = difference / 1000
    var minutes = seconds / 60
    var hours = minutes / 60
    var days = hours / 24
    var lastVisit = ""
    when {
        seconds <= 1 -> {
            lastVisit = "только что"
        }
        seconds <= 45 -> {
            lastVisit =  "несколько секунд назад"
        }
        seconds <= 75 -> {
            lastVisit =  "минуту назад"
        }
        minutes <= 45 -> {
            if(minutes < 5){
                if (minutes == 1) minutes++
                lastVisit = "$minutes минуты назад"
            }else{
                lastVisit =  "$minutes минут назад"
            }
        }
        minutes <= 75 -> {
            lastVisit =  "час назад"
        }
        hours <= 22 -> {
            if (hours < 5){
                if (hours == 1) hours++
                lastVisit = "$hours часа назад"
            }else{
                lastVisit =  "$hours часов назад"
            }
        }
        hours <= 26 -> {
            lastVisit =  "день назад"
        }
        days <= 360 -> {
            if (days < 5){
                if (days == 1) days++
                lastVisit = "$days дня назад"
            }else{
                lastVisit =  "$days дней назад"
            }
        }
        days > 360 -> {
            lastVisit =  "более года назад"
        }
    }
    return lastVisit

}

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"):String{
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, timeUnits : TimeUnits = TimeUnits.SECONDS): Date{
    var time = this.time
    time += when (timeUnits){
        TimeUnits.SECONDS -> value * SECONDS
        TimeUnits.MINUTES -> value * MINUTES
        TimeUnits.HOURS -> value * HOURS
        TimeUnits.DAYS -> value * DAYS
    }
    this.time = time
    return this
}

enum class TimeUnits{
    SECONDS,
    MINUTES,
    HOURS,
    DAYS
}