package cl.smq.indicatorapp.data.entities

import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * {
 * "fecha": "2020-10-22T03:00:00.000Z",
 * "valor": 0.5
 * }
 **/

data class Serie (
    @SerializedName("fecha")
    val date: Date,
    @SerializedName("valor")
    val value: Double
)


