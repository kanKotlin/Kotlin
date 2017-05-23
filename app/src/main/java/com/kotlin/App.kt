package com.kotlin

import android.app.Application
import com.squareup.picasso.LruCache
import com.squareup.picasso.Picasso

/**
 * Created by kan212 on 17/5/19.
 */
class App : Application(){

    override fun onCreate() {
        super.onCreate()
        val maxMem = Runtime.getRuntime().maxMemory().toInt()
        Picasso.Builder(this).memoryCache(LruCache(maxMem / 8)).build()
    }
}