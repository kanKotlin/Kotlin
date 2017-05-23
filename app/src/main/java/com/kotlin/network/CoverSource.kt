package com.kotlin.network

import com.kotlin.domain.Cover
import com.kotlin.getHtml
import org.jsoup.Jsoup

/**
 * Created by kan212 on 17/5/22.
 */
class CoverSource : Source<ArrayList<Cover>>{

    override fun obtain(url: String): ArrayList<Cover> {
        var list = ArrayList<Cover>()
        var html = getHtml(url)
        var doc = Jsoup.parse(html)

        val elements = doc.select("ul.mangeListBox").select("li")
        for (element in elements) {
            val coverUrl = element.select("img").attr("src")
            val title = element.select("h1").text() + "\n" + element.select("h2").text()
            val link = "http://ishuhui.net" + element.select("div.magesPhoto").select("a").attr("href")
            val cover = Cover(coverUrl, title, link)
            list.add(cover)
        }
        return list
    }
}