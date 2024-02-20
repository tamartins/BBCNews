package org.tmartins.bbcnews.view.list

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_news_list.*
import org.tmartins.bbcnews.R
import org.tmartins.bbcnews.model.repository.NewsListRepository
import org.tmartins.bbcnews.presenter.NewsListPresenter
import org.tmartins.bbcnews.view.base.BaseServiceFragment
import org.tmartins.bbcnews.view.base.MainActivity
import org.tmartins.bbcnews.view.detail.NewsDetailFragment
import org.tmartins.bbcnews.view.model.ArticleUI

private const val NEWS_DATA_SAVED = "NEWS_DATA_SAVED"

/**
 * Fragment displaying a list of news items.
 */
class NewsListFragment : BaseServiceFragment<List<ArticleUI>>(), OnArticleClickedListener {
    private val presenter = NewsListPresenter(this, NewsListRepository())

    private var articles: List<ArticleUI>? = null
    private var newsAdapter: NewsListAdapter? = null

    companion object {
        fun newInstance() = NewsListFragment()
    }
    //region override functions

    override fun callInitRequest() {
        presenter.getTopHeadlines()
    }

    override fun onSuccessResponse(response: List<ArticleUI>?) {
        articles = response
        initRecyclerView()
    }

    override fun onArticledClicked(article: ArticleUI) {
        (requireActivity() as? MainActivity)?.navigateTo(NewsDetailFragment.newInstance(article))
    }

    override fun getLayoutResource() = R.layout.fragment_news_list

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(NEWS_DATA_SAVED, ArrayList(articles))
    }

    override fun onRestore(savedInstanceState: Bundle?) {
        articles = savedInstanceState?.getParcelableArrayList(NEWS_DATA_SAVED)
        initRecyclerView()
    }

    //endregion

    //region private functions

    private fun initRecyclerView() {
        newsAdapter = NewsListAdapter(articles.orEmpty(), this)

        with(recycler_view_news_list) {
            layoutManager = LinearLayoutManager(activity)
            this.adapter = newsAdapter
        }
    }

    //endregion
}