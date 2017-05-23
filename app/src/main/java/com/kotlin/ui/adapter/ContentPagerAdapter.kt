package com.kotlin.ui.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.ViewGroup
import org.w3c.dom.NameList

/**
 * Created by kan212 on 17/5/22.
 */
class ContentPagerAdapter(var fragments: List<Fragment>,val nameList: List<String>,var fm :FragmentManager) :FragmentPagerAdapter(fm){
    override fun getCount(): Int = fragments.size

    override fun getItem(position: Int): Fragment?{
        return fragments[position]
    }

    override fun getPageTitle(position: Int): CharSequence {
        return nameList[position]
    }

    override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {
        super.destroyItem(container, position, `object`)
    }

}