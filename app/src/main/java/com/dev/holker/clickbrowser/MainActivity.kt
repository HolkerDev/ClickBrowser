package com.dev.holker.clickbrowser

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    //create menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_creator, menu)
        return true
    }

    //when item selected
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item != null) {
            when (item.itemId) {
                R.id.btn_menu_back -> {
                    if (webView.canGoBack()) {
                        webView.goBack()
                    }
                    return true
                }
                R.id.btn_menu_forward -> {
                    if (webView.canGoForward()) {
                        webView.goForward()
                    }
                    return true
                }
                R.id.btn_menu_bookmark -> {
                    val intent = Intent(applicationContext, Bookmarks::class.java)
                    intent.putExtra("web", webView.url)
                    startActivity(intent)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    //when back button is pressed return to the previous page
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (event != null) {
            if (event.action == KeyEvent.ACTION_DOWN) {
                when (keyCode) {
                    KeyEvent.KEYCODE_BACK -> {
                        if (webView.canGoBack()) {
                            webView.goBack()
                        } else {
                            finish()
                        }
                        return true
                    }
                }
            }

        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        webView.settings.loadsImagesAutomatically = true
        webView.webViewClient = WebViewClient()
        webView.settings.builtInZoomControls = true;
        webView.settings.displayZoomControls = false;
        webView.settings.javaScriptEnabled = true
        webView.loadUrl("https://www.google.com/")

        search_button.setOnClickListener {
            webView.loadUrl("https://www.google.com/search?q=" + search_edit_frame.text.toString())
            search_edit_frame.setText("")
        }

    }
}

