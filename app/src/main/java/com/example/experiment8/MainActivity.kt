package com.example.experiment8

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.GridView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var webView: WebView
    private lateinit var gridView: GridView

    private val images = intArrayOf(
        R.drawable.image1,
        R.drawable.image2,
        R.drawable.image3
    )

    private val names = arrayOf(
        "Image 1",
        "Image 2",
        "Image 3"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        webView = findViewById(R.id.webView)
        gridView = findViewById(R.id.gridView)

        // WebView configuration
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()

        webView.loadUrl("https://www.google.com")

        // GridView adapter
        val adapter = ImageAdapter(
            this,
            images,
            names
        )

        gridView.adapter = adapter

        // Register context menu
        registerForContextMenu(gridView)

        // Grid item click
        gridView.setOnItemClickListener { _, _, position, _ ->

            Toast.makeText(
                this,
                "Selected: ${names[position]}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    // Create options menu
    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(
            R.menu.main_menu,
            menu
        )

        return true
    }

    // Handle menu clicks
    override fun onOptionsItemSelected(
        item: MenuItem
    ): Boolean {

        return when (item.itemId) {

            R.id.action_select_all -> {

                Toast.makeText(
                    this,
                    "All images selected",
                    Toast.LENGTH_SHORT
                ).show()

                true
            }

            R.id.action_share -> {

                val shareIntent = Intent(
                    Intent.ACTION_SEND
                )

                shareIntent.type = "text/plain"

                shareIntent.putExtra(
                    Intent.EXTRA_TEXT,
                    "Check out this Android application!"
                )

                startActivity(
                    Intent.createChooser(
                        shareIntent,
                        "Share using"
                    )
                )

                true
            }

            R.id.action_web -> {

                webView.loadUrl(
                    "https://www.android.com"
                )

                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    // Create context menu
    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {

        super.onCreateContextMenu(
            menu,
            v,
            menuInfo
        )

        menu.setHeaderTitle("Image Options")

        menu.add(
            "Edit"
        )

        menu.add(
            "Delete"
        )

        menu.add(
            "Share"
        )
    }

    // Context menu click
    override fun onContextItemSelected(
        item: MenuItem
    ): Boolean {

        Toast.makeText(
            this,
            "${item.title} selected",
            Toast.LENGTH_SHORT
        ).show()

        return true
    }
}