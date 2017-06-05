package com.kotlin.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.kotlin.R
import com.kotlin.domain.Comic
import com.kotlin.network.ComicSource
import com.kotlin.ui.fragment.ComicFragment
import kotlinx.android.synthetic.main.activity_comic.*
import org.jetbrains.anko.async
import org.jetbrains.anko.uiThread
import java.util.ArrayList

class ComicActivity : AppCompatActivity() {

    companion object {
        val INTENT_COMIC_URL = "url"
    }

    lateinit var adapter: ComicPagerAdapter
    var mData = ArrayList<Comic>()
    lateinit var url: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comic)

        url = intent.getStringExtra(INTENT_COMIC_URL)
        adapter = ComicPagerAdapter(mData, supportFragmentManager)
        comicPagers.adapter = adapter
        comicPagers.offscreenPageLimit = 2

    }

    override fun onResume() {
        super.onResume()
        async() {
            var data = ComicSource().obtain(url)
            mData = data

            uiThread {
                adapter.refreshData(data)
            }
        }
    }

    class ComicPagerAdapter(var data: ArrayList<Comic> = ArrayList<Comic>(), fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {
        override fun getCount(): Int = data.size

        override fun getItem(position: Int): Fragment? = newInstance(data[position].comicUrl)

        fun refreshData(newData: ArrayList<Comic>) {
            data = newData
            notifyDataSetChanged()
        }

        fun newInstance(url: String) = ComicFragment(url)
    }
}

