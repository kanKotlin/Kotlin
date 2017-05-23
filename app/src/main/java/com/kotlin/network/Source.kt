package com.kotlin.network

/**
 * Created by kan212 on 17/5/22.
 */
interface Source <T>{
    fun obtain(url:String):T
}