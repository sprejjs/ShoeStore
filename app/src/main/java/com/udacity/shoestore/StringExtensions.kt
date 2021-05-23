package com.udacity.shoestore

import android.content.res.Resources
import android.os.Build
import android.text.Html
import android.text.Spanned
import androidx.annotation.StringRes


fun Resources.getHtmlSpannedString(@StringRes id: Int, vararg formatArgs: Any): Spanned = getString(id, *formatArgs).toHtmlSpan()

fun String.toHtmlSpan(): Spanned = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
    Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY)
} else {
    Html.fromHtml(this)
}
