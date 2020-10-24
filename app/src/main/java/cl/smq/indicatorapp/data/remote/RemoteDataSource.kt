package cl.smq.indicatorapp.data.remote

import cl.smq.indicatorapp.utils.Resource
import retrofit2.Response

abstract class RemoteDataSource {

    private val errorMessage: String = "No hay conexión con el servidor, no se pueden actualizar los datos"

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Resource<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return Resource.onSuccess(body)
            }
            return  Resource.onError(errorMessage)
        } catch (e: Exception) {
            return Resource.onError(errorMessage)
        }
    }
}