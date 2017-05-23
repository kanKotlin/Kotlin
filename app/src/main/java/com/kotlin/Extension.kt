package com.kotlin

import com.squareup.okhttp.Request

/**
 * Created by kan212 on 17/5/22.
 */
fun getHtml(url:String):String{
    var client = OkClient.instance()
    var request = Request.Builder()
            .url(url)
            .build()
    var response = client.newCall(request).execute()
    return response.body().string()
}
