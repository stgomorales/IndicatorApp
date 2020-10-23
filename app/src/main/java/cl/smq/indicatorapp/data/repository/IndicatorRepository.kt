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
        indicator.uf.date = indicator.date
        indicators.add(indicator.uf)
        indicator.ivp.date = indicator.date
        indicators.add(indicator.ivp)
        indicator.dolar.date = indicator.date
        indicators.add(indicator.dolar)
        indicator.dolarExchange.date = indicator.date
        indicators.add(indicator.dolarExchange)
        indicator.euro.date = indicator.date
        indicators.add(indicator.euro)
        indicator.ipc.date = indicator.date
        indicators.add(indicator.ipc)
        indicator.utm.date = indicator.date
        indicators.add(indicator.utm)
        indicator.imacec.date = indicator.date
        indicators.add(indicator.imacec)
        indicator.tpm.date = indicator.date
        indicators.add(indicator.tpm)
        indicator.poundCopper.date = indicator.date
        indicators.add(indicator.poundCopper)
        indicator.unemploymentRate.date = indicator.date
        indicators.add(indicator.unemploymentRate)
        indicator.bitcoin.date = indicator.date
        indicators.add(indicator.bitcoin)
        return  indicators
    }
}