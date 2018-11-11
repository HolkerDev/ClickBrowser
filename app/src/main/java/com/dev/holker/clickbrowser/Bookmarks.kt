package com.dev.holker.clickbrowser

import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import kotlinx.android.synthetic.main.activity_bookmarks.*

class Bookmarks : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bookmarks)
        val intent = intent
        val prevWeb: String = intent.getStringExtra("web")

        AlertDialog.Builder(this)
            .setIcon(R.drawable.ic_star_black_24dp)
            .setTitle("Choose")
            .setMessage("Do want to add $prevWeb to bookmarks ?")
            .setPositiveButton("Add") { dialog: DialogInterface?, which: Int ->
                run {
                    textView.text = prevWeb
                }
            }
            .setNegativeButton("No", null)
            .show()
    }
}
