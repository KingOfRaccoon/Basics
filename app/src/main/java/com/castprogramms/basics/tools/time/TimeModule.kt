package com.castprogramms.karma.tools.time

import java.util.*

object TimeModule {
    fun now(): DataTime {
        val date = Calendar.getInstance()
        val minute = date.get(Calendar.MINUTE)
        var strMinute = ""
        if (minute < 10){
            strMinute = "0$minute"
        }
        else
            strMinute = minute.toString()
        return DataTime(date.get(Calendar.YEAR),
            date.get(Calendar.MONTH)+1,
                date.get(Calendar.DAY_OF_MONTH),
                date.get(Calendar.HOUR_OF_DAY).toString() + ":" + strMinute,
                (date.timeZone.rawOffset / 1000 / 3600).toString())
    }

    fun getServiceTime(dataTime: DataTime): String {
        var string = ""
        val date = now()
        val delDays = date.day - dataTime.day
        when(delDays){
            0 ->{
                string += "Сегодня"
            }
            1 ->{
                string += "Вчера"
            }
            else ->{
                string += dataTime.day.toString()
                string += " "
                string += getMouth(dataTime.mouth)
            }
        }
        string += ", "
        string += dataTime.time
        return string
    }

    fun getMouth(int: Int) = when(int){
        1 -> "Января"
        2 -> "Февраля"
        3 -> "Марта"
        4 -> "Апреля"
        5 -> "Мая"
        6 -> "Июня"
        7 -> "Июля"
        8 -> "Августа"
        9 -> "Сентября"
        10 -> "Октября"
        11 -> "Ноября"
        12 -> "Декабря"
        else -> ""
    }
}