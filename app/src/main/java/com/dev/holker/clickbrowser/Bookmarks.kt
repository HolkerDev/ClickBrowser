package com.dev.holker.clickbrowser

import android.content.Context
import android.content.DialogInterface
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteDatabaseLockedException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.util.Log
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_bookmarks.*

class Bookmarks : AppCompatActivity() {

    lateinit var SQLDatabase: SQLiteDatabase
    lateinit var ArrayAdapter: ArrayAdapter<String>
    val BookmarksList = arrayListOf<String>()

    //check if DB exists. If not - create its
    fun creatingDB() {
        SQLDatabase = this.openOrCreateDatabase("Bookmarks", Context.MODE_PRIVATE, null)
        SQLDatabase.execSQL("CREATE TABLE IF NOT EXISTS bookmarks (webPage VARCHAR)")
    }


    fun addBookmark(webAddress: String) {
        val exec: String = "INSERT INTO bookmarks (webPage) VALUES ('$webAddress')"
        SQLDatabase.execSQL(exec)
    }

    fun update() {
        val c: Cursor = SQLDatabase.rawQuery("SELECT DISTINCT * FROM bookmarks", null)

        val bookmarksIndex: Int = c.getColumnIndex("webPage")

        c.moveToFirst()
        while (!c.isAfterLast()) {
            BookmarksList.add(c.getString(bookmarksIndex))
            //Log.i("MyLog", c.getString(bookmarksIndex))
            c.moveToNext()
        }
        lv_bookmarks.adapter = ArrayAdapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bookmarks)

        creatingDB()

        val intent = intent
        val prevWeb: String = intent.getStringExtra("web")

        ArrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, BookmarksList)

        AlertDialog.Builder(this)
            .setIcon(R.drawable.ic_star_black_24dp)
            .setTitle("Choose")
            .setMessage("Do want to add $prevWeb to bookmarks ?")
            .setPositiveButton("Add") { dialog: DialogInterface?, which: Int ->
                run {
                    addBookmark(prevWeb)
                    update()
                }
            }
            .setNegativeButton("No") { dialog: DialogInterface?, which: Int ->
                run {
                    update()
                }
            }
            .show()
    }
}
