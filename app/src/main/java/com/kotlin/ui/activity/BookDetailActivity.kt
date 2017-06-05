package com.kotlin.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.kotlin.R
import com.kotlin.domain.BookDetail
import com.kotlin.domain.News
import com.kotlin.domain.Page
import com.kotlin.network.BookDetailSource
import com.kotlin.network.SBSSource
import com.kotlin.snackbar
import com.kotlin.ui.adapter.PageAdapter
import com.kotlin.ui.dialog.WebDetailDialog
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_book_detail.*
import org.jetbrains.anko.async
import org.jetbrains.anko.find
import org.jetbrains.anko.uiThread
import java.util.ArrayList

class BookDetailActivity : AppCompatActivity() {

    lateinit var url: String
    lateinit var pageList: RecyclerView
    lateinit var adapter: PageAdapter
    lateinit var pageRefresh: SwipeRefreshLayout
    lateinit var bookDetail: BookDetail

    companion object {
        val INTENT_COVER_URL = "cover"
        val INTENT_URL = "url"
        val INTENT_TITLE = "title"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)
        setSupportActionBar(toolbar)
        init()
    }

    override fun onResume() {
        super.onResume()
        pageRefresh.post { pageRefresh.isRefreshing = true }
        load()
    }

    fun init(){
        val coverUrl = intent.getStringExtra(INTENT_COVER_URL)
        var title = intent.getStringExtra(INTENT_TITLE)
        url = intent.getStringExtra(INTENT_URL)

        supportActionBar?.setDefaultDisplayHomeAsUpEnabled(true)
        collapsing_toolbar.title = title
        Picasso.with(this).load(coverUrl).into(backgroundImage)
        pageRefresh = find(R.id.pageRefresh)
        pageRefresh.setOnRefreshListener { load() }

        pageList = find(R.id.pageList)
        pageList.layoutManager = GridLayoutManager(this, 4)

        //TODO need to do better
        adapter = PageAdapter { view: View, position: Int ->
            if (title.contains("SBS")) {
                val news = News(bookDetail[position].title, "", bookDetail[position].link)
                WebDetailDialog(this, news, SBSSource())
            } else
                jump2Read(position)
        }

        pageList.adapter = adapter
    }

    private fun jump2Read(position: Int) {
//        var intent = Intent(this, ComicActivity().javaClass)
//        intent.putExtra(ComicActivity.INTENT_COMIC_URL, bookDetail[position].link)
//        startActivity(intent)
    }

    private fun load() {
        async() {
            bookDetail = BookDetailSource().obtain(url)
            val data = bookDetail.pages as ArrayList<Page>

            uiThread {
                adapter.refreshData(data)
                pageRefresh.isRefreshing = false
                if (bookDetail.size() == 0) {
                    showError()
                }
            }
        }
    }

    private fun showError() {
        pageList.snackbar("fuck")
    }

    fun showBookInfo() {
        val bookInfo = bookDetail.info
        pageList.snackbar(bookInfo.description + "\n" + bookInfo.updateTime)
    }



}



