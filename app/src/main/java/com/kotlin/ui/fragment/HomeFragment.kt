package com.kotlin.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kotlin.R
import com.kotlin.domain.Cover
import com.kotlin.network.CoverSource
import com.kotlin.ui.adapter.CoverAdapter
import org.jetbrains.anko.async
import org.jetbrains.anko.uiThread
import java.util.ArrayList


/**
 * Created by kan212 on 17/5/22.
 */
class HomeFragment : Fragment(){

    var mData = ArrayList<Cover>()

    lateinit var coverList: RecyclerView

    lateinit var homeRefresh: SwipeRefreshLayout

    lateinit var adapter: CoverAdapter

    companion object{
        val AIM_URL = "http://ishuhui.net/?PageIndex=1"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_home,container,false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
    }

    private fun  initView(view: View?) {
        homeRefresh = view?.findViewById(R.id.homeRefresh) as SwipeRefreshLayout
        coverList = view.findViewById(R.id.homeList) as RecyclerView

        coverList.layoutManager = GridLayoutManager(context, 2)

        adapter = CoverAdapter { view: View, position: Int -> jump2Comic(position) }
        coverList.adapter = adapter

        homeRefresh.setOnRefreshListener {
            load()
        }
        homeRefresh.post { homeRefresh.isRefreshing = true }
    }

    private fun jump2Comic(position: Int) {
//        homeRefresh.snackbar(mData[position].link)
//        var intent = Intent(context, ComicActivity().javaClass)
//        intent.putExtra(ComicActivity.INTENT_COMIC_URL, mData[position].link)
//        startActivity(intent)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser && mData.size == 0) {
            load()
        }

    }

    fun load(){
        async() {
            val data = CoverSource().obtain(AIM_URL)

            uiThread {
                mData = data
                adapter.refreshData(data)
                homeRefresh.isRefreshing = false
            }
        }
    }
}