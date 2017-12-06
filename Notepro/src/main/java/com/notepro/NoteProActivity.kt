package com.notepro

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity

/**
 * Created by kan212 on 17/12/6.
 */
class NoteProActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }

    companion object {

        fun startActivity(activity: Activity) {
            val intent = Intent(activity, NoteProActivity::class.java)
            activity.startActivity(intent)
        }
    }
}