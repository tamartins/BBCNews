package org.tmartins.bbcnews.model.repository

import org.tmartins.bbcnews.BuildConfig
import org.tmartins.bbcnews.contract.NewsListContract
import org.tmartins.bbcnews.model.model.NewsResponse
import org.tmartins.bbcnews.presenter.OnResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Repository class responsible for fetching news
 */
class NewsListRepository : BaseRepository(), NewsListContract.Model {

    override fun fetchTopHeadlines(onResult: OnResult<NewsResponse>) {
        apiClient.getTopHeadlines(apiKey, BuildConfig.SOURCE).enqueue(object : Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>?, response: Response<NewsResponse>) {
                when (response.code()) {
                    200 -> onResult.onSuccess(response.body())
                    else -> onResult.onFailure(response.code().toString())
                }
            }

            override fun onFailure(call: Call<NewsResponse>?, t: Throwable?) {
                onResult.onFailure(t?.message)
            }
        })
    }
}
