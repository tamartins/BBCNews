package org.tmartins.bbcnews.presenter

import org.tmartins.bbcnews.contract.NewsListContract
import org.tmartins.bbcnews.model.model.NewsResponse
import org.tmartins.bbcnews.view.model.ArticleUI

/**
 * This presenter is responsible for handling the business logic related to the News List screen.
 */
class NewsListPresenter(private val newsListView: NewsListContract.View<List<ArticleUI>>,
                        private val newsListModel: NewsListContract.Model) : NewsListContract.Presenter {

    override fun getTopHeadlines() {
        newsListView.onResponse(Response.Loading())

        newsListModel.fetchTopHeadlines(object : OnResult<NewsResponse> {
            override fun onFailure(errorMessage: String?) {
                newsListView.onResponse(Response.Error(errorMessage.orEmpty()))
            }

            override fun onSuccess(result: NewsResponse?) {
                newsListView.onResponse(Response.Success(mapToArticleUI(result)))
            }
        })
    }

    /**
     * Maps a NewsResponse object to a list of ArticleUI objects.
     * It sorts the articles by published date in descending order
     *
     * @param newsResponse The NewsResponse object to be mapped.
     * @return A list of ArticleUI objects mapped from [newsResponse]. If the input is null or empty, an empty list is returned.
     */
    private fun mapToArticleUI(newsResponse: NewsResponse?): List<ArticleUI> {
        return newsResponse?.articles?.filterNotNull()?.sortedByDescending { it.publishedAt }
                ?.map { ArticleUI(it.title.orEmpty(), it.urlToImage, it.content, it.description) }.orEmpty()
    }
}
