package cl.smq.indicatorapp.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*


/**
 * {
 * "codigo": "tpm",
 * "nombre": "Tasa Política Monetaria (TPM)",
 * "unidad_medida": "Porcentaje",
 * "fecha": "2020-10-22T03:00:00.000Z",
 * "valor": 0.5
 * }
 * **/

@Entity(tableName = "indicator")
data class Indicator(
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),
    @SerializedName("codigo")
    val code: String,
    @SerializedName("nombre")
    val name: String,
    @SerializedName("unidad_medida")
    val unitMeasure: String,
    @SerializedName("fecha")
    val date: Date,
    @SerializedName("valor")
    val value: Double,
    @SerializedName("serie")
    val series: List<Serie>
)
