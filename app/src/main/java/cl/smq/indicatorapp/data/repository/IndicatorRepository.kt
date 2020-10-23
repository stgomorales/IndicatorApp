package cl.smq.indicatorapp.data.repository

import cl.smq.indicatorapp.data.entities.Indicator
import cl.smq.indicatorapp.data.entities.IndicatorDetail
import cl.smq.indicatorapp.data.local.IndicatorDetailDao
import cl.smq.indicatorapp.data.remote.IndicatorRemoteDataSource
import cl.smq.indicatorapp.utils.performGetOperation
import javax.inject.Inject

class IndicatorRepository @Inject constructor(
    private val remoteDataSource: IndicatorRemoteDataSource,
    private val localDataSource: IndicatorDetailDao){

    fun getIndicatorDetail(code: String) = performGetOperation(
        databaseQuery = {localDataSource.getIndicator(code)},
        networkCall = suspend { remoteDataSource.getIndicatorDetail(code) },
        saveCallResult = {localDataSource.insert(it)}
    )

    fun getAllIndicators() = performGetOperation(
        databaseQuery = {localDataSource.getAllIndicators()},
        networkCall = suspend { remoteDataSource.getAllIndicators() },
        saveCallResult = {localDataSource.insertAll(getIndicatorArray(it))}
    )

    fun getIndicatorArray(indicator: Indicator): List<IndicatorDetail>{
        val indicators: ArrayList<IndicatorDetail> = ArrayList()
        indicators.add(indicator.uf)
        indicators.add(indicator.ivp)
        indicators.add(indicator.dolar)
        indicators.add(indicator.dolarExchange)
        indicators.add(indicator.euro)
        indicators.add(indicator.ipc)
        indicators.add(indicator.utm)
        indicators.add(indicator.imacec)
        indicators.add(indicator.tpm)
        indicators.add(indicator.poundCopper)
        indicators.add(indicator.unemploymentRate)
        indicators.add(indicator.bitcoin)
        return  indicators
    }
}