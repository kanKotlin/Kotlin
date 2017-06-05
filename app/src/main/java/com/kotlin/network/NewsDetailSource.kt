package com.kotlin.network

import com.kotlin.getHtml
import org.jsoup.Jsoup

/**
 * Created by kan212 on 17/5/26.
 */

class NewsDetailSource: Source<String>{
    override fun obtain(url: String): String {
        val html = getHtml(url)
        val doc = Jsoup.parse(html)

        var contentHtml =
                "<html>${doc.select("head").toString()}<body>${doc.select("div.featureContentText").toString()}</body></html>"
        return contentHtml
    }

}
