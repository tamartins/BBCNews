package org.tmartins.bbcnews.presenter


/**
 * Interface defining callbacks for result from model.
 */
interface OnResult<T> {
    /**
     * Invoked upon successful request.
     *
     * @param result The result of the operation.
     */
    fun onSuccess(result: T? = null)

    /**
     * Invoked upon failure or error.
     *
     * @param errorMessage The error message describing the failure or error,
     */
    fun onFailure(errorMessage: String? = null)
}

/**
 * Sealed class representing the response from a request.
 */
sealed class Response<T>(
        val data: T? = null,
        val message: String? = null
) {

    /**
     * Represents a successful response.
     *
     * @param data The data received.
     */
    class Success<T>(data: T?) : Response<T>(data = data)

    /**
     * Represents an error response
     * @param errorMessage The error message
     */
    class Error<T>(errorMessage: String) : Response<T>(message = errorMessage)

    /**
     * Represents a loading state before making a request.
     */
    class Loading<T> : Response<T>()
}
