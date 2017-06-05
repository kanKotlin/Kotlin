package com.kotlin

import android.support.design.widget.Snackbar
import android.view.View
import android.webkit.WebView
import com.squareup.okhttp.Request

/**
 * Created by kan212 on 17/5/22.
 */

fun View.snackbar(message: String, duration: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(this,message,duration).show()
}

fun getHtml(url:String):String{
    var client = OkClient.instance()
    var request = Request.Builder()
            .url(url)
            .build()
    var response = client.newCall(request).execute()
    return response.body().string()
}

fun WebView.load(html: String) {
    this.loadDataWithBaseURL("http://ishuhui.net/", html, "text/html", "charset=utf-8", null)
}