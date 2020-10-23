package cl.smq.indicatorapp.data.local

import androidx.room.TypeConverter
import cl.smq.indicatorapp.data.entities.Indicator
import cl.smq.indicatorapp.data.entities.Serie
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class Converter {
    var gson = Gson()

    @TypeConverter
    fun indicatorToString(indicator: Indicator): String {
        return gson.toJson(indicator)
    }

    @TypeConverter
    fun stringToIndicator(data: String): Indicator {
        val listType = object : TypeToken<Indicator>() {
        }.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun SerieListToString(serieItems: List<Serie>?): String? {
        return if (serieItems.isNullOrEmpty())
            null
        else
            gson.toJson(serieItems)
    }

    @TypeConverter
    fun stringToSerieList(data: String): List<Serie>? {
        val listType = object : TypeToken<List<Serie>>() {
        }.type
        return if (data.isNullOrEmpty())
            null
        else
            gson.fromJson(data, listType)
    }

    @TypeConverter
    fun toDate(dateLong:Long):Date {
        return Date(dateLong)
    }

    @TypeConverter
    fun fromDate(date: Date):Long{
        return date.time;
    }
}