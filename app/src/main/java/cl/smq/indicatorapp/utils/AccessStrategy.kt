package cl.smq.indicatorapp.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import kotlinx.coroutines.Dispatchers


fun <T, A> performGetOperation(databaseQuery: () -> LiveData<T>,
                               networkCall: suspend () -> Resource<A>,
                               saveCallResult: suspend (A) -> Unit): LiveData<Resource<T>> =
    liveData(Dispatchers.IO) {
        emit(Resource.onLoading())
        val source = databaseQuery.invoke().map { Resource.onSuccess(it) }
        emitSource(source)

        val responseStatus = networkCall.invoke()
        if (responseStatus.status == Resource.Status.onSuccess) {
            saveCallResult(responseStatus.data!!)

        } else if (responseStatus.status == Resource.Status.onError) {
            emit(Resource.onError(responseStatus.message!!))
            emitSource(source)
        }
    }
