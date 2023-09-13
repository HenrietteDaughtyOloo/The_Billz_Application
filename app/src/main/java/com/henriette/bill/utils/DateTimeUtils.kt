package com.henriette.bill.utils

import java.time.DayOfWeek
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjuster
import java.time.temporal.TemporalAdjusters
import java.time.temporal.TemporalAdjusters.firstDayOfMonth
import java.time.temporal.TemporalAdjusters.lastDayOfMonth
import java.time.temporal.TemporalAdjusters.previousOrSame

class DateTimeUtils {
    companion object{
        private fun formatddMMyyyy(localDateTime: LocalDateTime):String{
            val format = DateTimeFormatter.ofPattern("dd/MM/yyyy")
            return localDateTime.format(format)
        }

        fun getFirstDayOfMonth(): String{
            val now = LocalDateTime.now()
            val firstDay = now.with(firstDayOfMonth())
            return formatddMMyyyy(firstDay)
        }
        fun getLastDayOfMonth(): String{
            val now = LocalDateTime.now()
            val lastDay = now.with(lastDayOfMonth())
            return formatddMMyyyy(lastDay)
        }
        fun getCurrentMonth():String{
            val now = LocalDateTime.now()
            return now.month.value.toString()
        }
        fun getCurrentYear():String{
            val now = LocalDateTime.now()
            return now.year.toString()
        }
        fun getFirstDateOfWeek():String{
            val now = LocalDateTime.now()
            val firstDay = now.with(previousOrSame(DayOfWeek.MONDAY))
            return formatddMMyyyy(firstDay)
        }
        fun getLastDateOfWeek():String{
            val now = LocalDateTime.now()
            val lastDay = now.with(previousOrSame(DayOfWeek.SUNDAY))
            return formatddMMyyyy(lastDay)
        }
        fun getDateOfWeekDay(day:String):String{
            val now = LocalDateTime.now()
            val weekDay = now.with(TemporalAdjusters.nextOrSame(DayOfWeek.of(day.toInt())))
            return formatddMMyyyy(weekDay)
        }
    }
}