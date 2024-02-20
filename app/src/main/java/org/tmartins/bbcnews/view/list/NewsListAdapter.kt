package org.tmartins.bbcnews.view.list

import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_news_list.view.*
import org.tmartins.bbcnews.R
import org.tmartins.bbcnews.view.model.ArticleUI

/**
 * Adapter for displaying a list of articles
 *
 * @property articles The list of articles to be displayed.
 * @property articleClickedListener Listener to handle item click events.
 */
class NewsListAdapter(private val articles: List<ArticleUI>,
                      private val articleClickedListener: OnArticleClickedListener? = null)
    : RecyclerView.Adapter<NewsListAdapter.NewsListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsListViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_news_list, parent, false)
        return NewsListViewHolder(view)
    }

    override fun getItemCount() = articles.size

    override fun onBindViewHolder(holder: NewsListViewHolder, position: Int) {
        val item = articles[position]
        with(holder) {
            title.text = item.title
            itemView.setOnClickListener { articleClickedListener?.onArticledClicked(item) }
            Glide.with(itemView.context)
                    .load(item.imageUrl)
                    .into(image)
        }
    }

    class NewsListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title = view.text_view_news_list_title as AppCompatTextView
        val image = view.image_view_news_list_image as AppCompatImageView
    }
}

/**
 * Listener to handle item click events.
 */
interface OnArticleClickedListener {
    /**
     * Invoked when an article is clicked.
     *
     * @param article Article clicked
     */
    fun onArticledClicked(article: ArticleUI)
}