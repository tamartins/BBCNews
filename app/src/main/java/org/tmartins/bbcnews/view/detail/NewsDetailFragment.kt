package org.tmartins.bbcnews.view.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_news_detail.*
import org.tmartins.bbcnews.R
import org.tmartins.bbcnews.view.base.BaseFragment
import org.tmartins.bbcnews.view.model.ArticleUI

private const val ARG_ARTICLE = "ARG_ARTICLE::NewsDetailFragment"

/**
 * Fragment displaying a detail from an Article.
 */
class NewsDetailFragment : BaseFragment() {

    companion object {
        fun newInstance(articleUI: ArticleUI): NewsDetailFragment {
            return NewsDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_ARTICLE, articleUI)
                }
            }
        }
    }

    //region override functions

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_news_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
    }

    //endregion

    //region private functions

    private fun initUI() {
        val article = arguments?.getParcelable<ArticleUI>(ARG_ARTICLE) ?: return

        with(article) {
            text_view_news_detail_title.text = title
            text_view_news_detail_description.text = description
            text_view_news_detail_content.text = content
            Glide.with(requireContext())
                    .load(imageUrl)
                    .into(image_view_news_detail_image)
        }
    }

    //endregion
}