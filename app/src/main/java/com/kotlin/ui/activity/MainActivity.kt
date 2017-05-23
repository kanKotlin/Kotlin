package com.kotlin.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.Menu
import com.kotlin.R
import com.kotlin.ui.adapter.ContentPagerAdapter
import com.kotlin.ui.fragment.BookFragment
import com.kotlin.ui.fragment.HomeFragment
import com.kotlin.ui.fragment.NewsFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*


class MainActivity : AppCompatActivity() {

    var nameResList: ArrayList<Int> = arrayListOf(R.string.tab_one, R.string.tab_two, R.string.tab_three)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        setSupportActionBar(toolbar)
        val fragments = ArrayList<Fragment>()
        fragments.add(HomeFragment())
        fragments.add(BookFragment())
        fragments.add(BookFragment())

        var nameList = nameResList.map { Int -> getString(Int)}
        viewPager.adapter = ContentPagerAdapter(fragments,nameList,supportFragmentManager)
        viewPager.offscreenPageLimit = 2
        tab_layout.setupWithViewPager(viewPager)
    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.menu_main,menu)
//        return true
//    }

}

