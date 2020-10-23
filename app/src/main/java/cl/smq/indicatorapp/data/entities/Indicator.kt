package cl.smq.indicatorapp.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(tableName = "Indicator")
data class Indicator(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @SerializedName("fecha")
    val date: Date,
    val uf: IndicatorDetail,
    val ivp: IndicatorDetail,
    val dolar: IndicatorDetail,
    @SerializedName("dolar_intercambio")
    val dolarExchange: IndicatorDetail,
    val euro: IndicatorDetail,
    val ipc: IndicatorDetail,
    val utm: IndicatorDetail,
    val imacec: IndicatorDetail,
    val tpm: IndicatorDetail,
    @SerializedName("libra_cobre")
    val poundCopper: IndicatorDetail,
    @SerializedName("tasa_desempleo")
    val unemploymentRate: IndicatorDetail,
    val bitcoin: IndicatorDetail
){
    fun getIndicatorArray(): List<IndicatorDetail>{
        val indicators: ArrayList<IndicatorDetail> = ArrayList()
        uf.date = date
        indicators.add(uf)
        ivp.date = date
        indicators.add(ivp)
        dolar.date = date
        indicators.add(dolar)
        dolarExchange.date = date
        indicators.add(dolarExchange)
        euro.date = date
        indicators.add(euro)
        ipc.date = date
        indicators.add(ipc)
        utm.date = date
        indicators.add(utm)
        imacec.date = date
        indicators.add(imacec)
        tpm.date = date
        indicators.add(tpm)
        poundCopper.date = date
        indicators.add(poundCopper)
        unemploymentRate.date = date
        indicators.add(unemploymentRate)
        bitcoin.date = date
        indicators.add(bitcoin)
        return  indicators
    }
}

/**
 * {
 * "version": "1.6.0",
 * "autor": "mindicador.cl",
 * "fecha": "2020-10-22T05:00:00.000Z",
 * "uf": {
 *  "codigo": "uf",
 *  "nombre": "Unidad de fomento (UF)",
 *  "unidad_medida": "Pesos",
 *  "fecha": "2020-10-22T03:00:00.000Z",
 *  "valor": 28788.59
 * },
 * "ivp": {
 *  "codigo": "ivp",
 *  "nombre": "Indice de valor promedio (IVP)",
 *  "unidad_medida": "Pesos",
 *  "fecha": "2020-10-22T03:00:00.000Z",
 *  "valor": 30089.54
 * },
 * "dolar": {
 *  "codigo": "dolar",
 *  "nombre": "Dólar observado",
 *  "unidad_medida": "Pesos",
 *  "fecha": "2020-10-22T03:00:00.000Z",
 *  "valor": 784.07
 * },
 *"dolar_intercambio": {
 *  "codigo": "dolar_intercambio",
 *  "nombre": "Dólar acuerdo",
 *  "unidad_medida": "Pesos",
 *  "fecha": "2014-11-13T03:00:00.000Z",
 *  "valor": 758.87
 * },
 * "euro": {
 *  "codigo": "euro",
 *  "nombre": "Euro",
 *  "unidad_medida": "Pesos",
 *  "fecha": "2020-10-22T03:00:00.000Z",
 *  "valor": 930.76
 * },
 * "ipc": {
 *  "codigo": "ipc",
 *  "nombre": "Indice de Precios al Consumidor (IPC)",
 *  "unidad_medida": "Porcentaje",
 *  "fecha": "2020-09-01T04:00:00.000Z",
 *  "valor": 0.6
 * },
 *"utm": {
 *  "codigo": "utm",
 *  "nombre": "Unidad Tributaria Mensual (UTM)",
 *  "unidad_medida": "Pesos",
 *  "fecha": "2020-10-01T03:00:00.000Z",
 *  "valor": 50372
 * },
 *"imacec": {
 *   "codigo": "imacec",
 *   "nombre": "Imacec",
 *   "unidad_medida": "Porcentaje",
 *   "fecha": "2020-08-01T04:00:00.000Z",
 *   "valor": -11.3
 * },
 * "tpm": {
 *   "codigo": "tpm",
 *   "nombre": "Tasa Política Monetaria (TPM)",
 *   "unidad_medida": "Porcentaje",
 *   "fecha": "2020-10-22T03:00:00.000Z",
 *   "valor": 0.5
 * },
 * "libra_cobre": {
 *  "codigo": "libra_cobre",
 *  "nombre": "Libra de Cobre",
 *  "unidad_medida": "Dólar",
 *  "fecha": "2020-10-22T03:00:00.000Z",
 *  "valor": 3.12
 * },
 * "tasa_desempleo": {
 *  "codigo": "tasa_desempleo",
 *  "nombre": "Tasa de desempleo",
 *  "unidad_medida": "Porcentaje",
 *  "fecha": "2020-08-01T04:00:00.000Z",
 *  "valor": 12.93
 * },
 * "bitcoin": {
 *  "codigo": "bitcoin",
 *  "nombre": "Bitcoin",
 *  "unidad_medida": "Dólar",
 *  "fecha": "2020-10-19T03:00:00.000Z",
 *  "valor": 11758.16
 * }
 * }
 */
