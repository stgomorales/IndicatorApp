package cl.smq.indicatorapp.data.remote

import javax.inject.Inject

class IndicatorRemoteDataSource @Inject constructor(
    private val indicatorService: IndicatorService): RemoteDataSource() {

    suspend fun getIndicators() = getResult { indicatorService.getIndicators() }
    suspend fun getIndicatorDetail(code: String) = getResult { indicatorService.getIndicatorDetail(code) }
}