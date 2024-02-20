package org.tmartins.bbcnews.model.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://newsapi.org/v2/"

/**
 * Responsible for creating and providing the Retrofit client instance.
 */
object ApiClient {

    val client: Retrofit by lazy {
        Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
    }
}