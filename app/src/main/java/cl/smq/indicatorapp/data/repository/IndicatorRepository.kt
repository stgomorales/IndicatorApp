package cl.smq.indicatorapp.data.repository

import cl.smq.indicatorapp.data.local.IndicatorDao
import cl.smq.indicatorapp.data.local.IndicatorDetailDao
import cl.smq.indicatorapp.data.remote.IndicatorRemoteDataSource
import cl.smq.indicatorapp.utils.performGetOperation
import javax.inject.Inject

class IndicatorRepository @Inject constructor(
    private val remoteDataSource: IndicatorRemoteDataSource,
    private val localDetailDataSource: IndicatorDetailDao,
    private val localIndicatorDataSource: IndicatorDao){

    fun getIndicatorDetail(code: String) = performGetOperation(
        databaseQuery = {localDetailDataSource.getIndicator(code)},
        networkCall = suspend { remoteDataSource.getIndicatorDetail(code) },
        saveCallResult = {localDetailDataSource.insert(it)}
    )

    fun getAllIndicators() = performGetOperation(
        databaseQuery = {localIndicatorDataSource.getIndicators()},
        networkCall = suspend { remoteDataSource.getAllIndicators() },
        saveCallResult = {localIndicatorDataSource.insert(it)}
    )
}