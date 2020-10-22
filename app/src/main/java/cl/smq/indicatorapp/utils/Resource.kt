package cl.smq.indicatorapp.utils

data class Resource<out T>(val status: Status, val data: T?, val message: String?){

    enum class Status{
        onSuccess,
        onError,
        onLoading
    }

    companion object{
        fun <T> onSuccess(data: T, message: String? = null): Resource<T>{
            return Resource(Status.onSuccess, data, message)
        }

        fun <T> onError(message: String, data: T? = null): Resource<T>{
            return Resource(Status.onError, data, message)
        }

        fun <T> onLoading(data: T? = null): Resource<T>{
            return Resource(Status.onLoading, data, null)
        }
    }
}
