package org.tmartins.bbcnews

import io.mockk.every
import io.mockk.mockk
import io.mockk.verifyOrder
import org.junit.Before
import org.junit.Test
import org.tmartins.bbcnews.contract.NewsListContract
import org.tmartins.bbcnews.model.model.Article
import org.tmartins.bbcnews.model.model.NewsResponse
import org.tmartins.bbcnews.model.model.Source
import org.tmartins.bbcnews.presenter.NewsListPresenter
import org.tmartins.bbcnews.presenter.OnResult
import org.tmartins.bbcnews.presenter.Response
import org.tmartins.bbcnews.view.model.ArticleUI


class NewsListPresenterTest {
    private lateinit var newsListPresenter: NewsListPresenter
    private lateinit var newsListViewMock: NewsListContract.View<List<ArticleUI>>
    private lateinit var newsListModelMock: NewsListContract.Model

    @Before
    fun setUp() {
        newsListViewMock = mockk(relaxed = true)
        newsListModelMock = mockk(relaxed = true)
        newsListPresenter = NewsListPresenter(newsListViewMock, newsListModelMock)
    }

    @Test
    fun `test getTopHeadlines success`() {
        val newsResponse = createSuccessNewsResponse()
        val expectedResult = createSuccessExpectedResponse()
        every { newsListModelMock.fetchTopHeadlines(any()) } answers {
            val callback = arg<OnResult<NewsResponse>>(0)
            callback.onSuccess(newsResponse)
        }

        newsListPresenter.getTopHeadlines()

        verifyOrder {
            newsListViewMock.onResponse(match { it is Response.Loading })
            newsListViewMock.onResponse(match {
                it is Response.Success<List<ArticleUI>> && it.data == expectedResult
            })
        }
    }

    @Test
    fun `test getTopHeadlines success_empty_list`() {
        val newsResponse = NewsResponse()
        val expectedResult = emptyList<ArticleUI>()
        every { newsListModelMock.fetchTopHeadlines(any()) } answers {
            val callback = arg<OnResult<NewsResponse>>(0)
            callback.onSuccess(newsResponse)
        }

        newsListPresenter.getTopHeadlines()

        verifyOrder {
            newsListViewMock.onResponse(match { it is Response.Loading })
            newsListViewMock.onResponse(match {
                it is Response.Success<List<ArticleUI>> && it.data == expectedResult
            })
        }
    }

    @Test
    fun `test getTopHeadlines error`() {
        every { newsListModelMock.fetchTopHeadlines(any()) } answers {
            val callback = arg<OnResult<NewsResponse>>(0)
            callback.onFailure()
        }

        newsListPresenter.getTopHeadlines()

        verifyOrder {
            newsListViewMock.onResponse(match { it is Response.Loading })
            newsListViewMock.onResponse(match {
                it is Response.Error<List<ArticleUI>>
            })
        }
    }

    private fun createSuccessNewsResponse() = NewsResponse(articles = listOf(Article(Source(), title = "Title", urlToImage = "url", content = "content")))
    private fun createSuccessExpectedResponse() = listOf(ArticleUI("Title", "url", "content"))
}
