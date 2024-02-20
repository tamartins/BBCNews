package org.tmartins.bbcnews.view.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Represents a UI model for an article.
 *
 * @property title The title of the article.
 * @property imageUrl The URL of the article's image.
 * @property content The content of the article.
 * @property description The description of article.
 */
data class ArticleUI(val title: String,
                     val imageUrl: String? = null,
                     val content: String? = null,
                     val description: String? = null) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readString().orEmpty(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(imageUrl)
        parcel.writeString(content)
        parcel.writeString(description)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ArticleUI> {
        override fun createFromParcel(parcel: Parcel): ArticleUI {
            return ArticleUI(parcel)
        }

        override fun newArray(size: Int): Array<ArticleUI?> {
            return arrayOfNulls(size)
        }
    }
}