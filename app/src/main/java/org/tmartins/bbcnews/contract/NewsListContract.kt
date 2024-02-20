package org.tmartins.bbcnews.contract

import org.tmartins.bbcnews.model.model.NewsResponse
import org.tmartins.bbcnews.presenter.OnResult
import org.tmartins.bbcnews.presenter.Response

/**
 * Contract defining the communication interface between the view, presenter, and model
 * for the News List screen.
 */
interface NewsListContract {

    /**
     * Model interface responsible for providing data to the presenter.
     */
    interface Model {
        /**
         * Fetches the list of news from the data source.
         *  @param onResult Handle the result of the data.
         */
        fun fetchTopHeadlines(onResult: OnResult<NewsResponse>)
    }

    /**
     * Presenter interface responsible to providing data to the UI from Model.
     */
    interface Presenter {
        /**
         * Retrieves the top headlines list.
         */
        fun getTopHeadlines()
    }

    /**
     * View interface representing for providing data to the UI.
     */
    interface View<T> {
        /**
         * Handle response data from model.
         */
        fun onResponse(response: Response<T>)
    }
}
