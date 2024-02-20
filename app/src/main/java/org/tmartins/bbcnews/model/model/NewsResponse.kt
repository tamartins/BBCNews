package org.tmartins.bbcnews.model.model

import com.google.gson.annotations.SerializedName

data class NewsResponse(
        @SerializedName("status") val status: String? = null,
        @SerializedName("totalResults") val totalResults: Int? = null,
        @SerializedName("articles") val articles: List<Article?>? = null
)
