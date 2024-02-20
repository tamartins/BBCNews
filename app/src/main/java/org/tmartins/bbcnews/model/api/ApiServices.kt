package org.tmartins.bbcnews.model.api

import org.tmartins.bbcnews.model.model.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Interface defining API service endpoints.
 */
interface ApiServices {

    @GET("top-headlines")
    fun getTopHeadlines(@Query("apiKey") apiKey: String, @Query("sources") sources: String): Call<NewsResponse>
}
