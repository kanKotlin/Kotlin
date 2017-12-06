package com.kotlin

import android.app.ListActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import com.notepro.NoteProActivity

class MainActivity : ListActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val items = resources.getStringArray(R.array.project_list)

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)
        listAdapter = adapter
    }

    override fun onListItemClick(l: ListView?, v: View?, position: Int, id: Long) {
        super.onListItemClick(l, v, position, id)
        when(position){

            0 -> NoteProActivity.startActivity(this)
        }
    }
}
