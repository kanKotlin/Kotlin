package com.kotlin.network

import com.kotlin.domain.BookDetail
import com.kotlin.domain.BookInfo
import com.kotlin.domain.Page
import com.kotlin.getHtml
import org.jsoup.Jsoup
import java.util.ArrayList

/**
 * Created by kan212 on 17/5/26.
 */
class BookDetailSource : Source<BookDetail>{
    override fun obtain(url: String): BookDetail {
        val html = getHtml(url)
        val doc = Jsoup.parse(html)

        var pages = ArrayList<Page>()
        val elements = doc.select("div.volumeControl").select("a")

        for (element in elements) {
            val title = element.text()
            var link = "http://ishuhui.net/" + element.attr("href")
            val page = Page(title, link)
            pages.add(page)
        }

        val updateTime = doc.select("div.mangaInfoDate").text()
        val detail = doc.select("div.mangaInfoTextare").text()
        val info = BookInfo(updateTime, detail)

        return BookDetail(pages, info)
    }

}