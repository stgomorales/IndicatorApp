package cl.smq.indicatorapp.data.remote

import cl.smq.indicatorapp.data.entities.Indicator
import cl.smq.indicatorapp.data.entities.IndicatorDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface IndicatorService {
    @GET("api")
    suspend fun getIndicators(): Response<Indicator>

    @GET("api/{code}")
    suspend fun getIndicatorDetail(@Path("code") code :String) : Response<IndicatorDetail>

}