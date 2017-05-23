package com.kotlin

import com.squareup.okhttp.OkHttpClient

/**
 * Created by kan212 on 17/5/22.
 */
object OkClient {
    private val client = OkHttpClient()
    fun instance() = client
}