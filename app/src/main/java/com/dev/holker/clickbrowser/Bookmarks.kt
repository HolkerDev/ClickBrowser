package com.dev.holker.clickbrowser

import android.content.Context
import android.content.DialogInterface
import android.database.sqlite.SQLiteDatabase
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_bookmarks.*

class Bookmarks : AppCompatActivity() {


    //check if DB exists. If not - create its
    fun creatingDB() {
        val sqlDataBase: SQLiteDatabase = this.openOrCreateDatabase("Bookmarks", Context.MODE_PRIVATE, null)
        sqlDataBase.execSQL("CREATE TABLE IF NOT EXISTS bookmarks (webPage VARCHAR)")
    }
    

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bookmarks)

        creatingDB()


        val intent = intent
        val prevWeb: String = intent.getStringExtra("web")
        val bookmarks = arrayListOf<String>()

        val arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, bookmarks)

        AlertDialog.Builder(this)
            .setIcon(R.drawable.ic_star_black_24dp)
            .setTitle("Choose")
            .setMessage("Do want to add $prevWeb to bookmarks ?")
            .setPositiveButton("Add") { dialog: DialogInterface?, which: Int ->
                run {
                    bookmarks.add(prevWeb)
                }
            }
            .setNegativeButton("No", null)
            .show()

        lv_bookmarks.adapter = arrayAdapter
    }
}
