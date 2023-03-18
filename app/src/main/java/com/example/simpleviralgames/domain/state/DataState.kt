package com.example.simpleviralgames.domain.state

data class DataState<out T>(
    val data: T? = null,
    val error: String? = null,
    val loading: Boolean = false,
) {
    companion object {
        fun <T> success(data: T): DataState<T> = DataState(data = data, loading = false)

        fun <T> error(message: String, data: T? = null): DataState<T> =
            DataState(error = message, data = data, loading = false)

        fun <T> loading(data: T? = null): DataState<T> = DataState(data = data, loading = true)
    }
}
