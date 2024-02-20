package org.tmartins.bbcnews.extension

import android.view.View

fun View.setComponentVisibility(condition: Boolean) {
    visibility = if (condition) View.VISIBLE else View.GONE
}
