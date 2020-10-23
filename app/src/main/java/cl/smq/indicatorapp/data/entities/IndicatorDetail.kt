package cl.smq.indicatorapp.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

/**
 *  "version": "1.6.0",
 *  "autor": "mindicador.cl",
 *  "codigo": "ivp",
 *  "nombre": "Indice de valor promedio (IVP)",
 *  "unidad_medida": "Pesos",
 *  "serie": [
 *  {
 *  "fecha": "2020-10-22T03:00:00.000Z",
 *  "valor": 30089.54
 *  },
 */
@Entity(tableName = "IndicatorDetail")
data class IndicatorDetail(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    @SerializedName("codigo")
    val code: String,
    @SerializedName("nombre")
    val mane: String,
    @SerializedName("unidad_medida")
    val unitMeasure: String,
    @SerializedName("serie")
    val series: List<Serie>,
    @SerializedName("fecha")
    var date: Date,
    @SerializedName("valor")
    val value: Double
)