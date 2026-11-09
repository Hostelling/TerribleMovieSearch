package com.example.shittymoviesearch

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.squareup.picasso.Picasso

class SecondaryActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_screen)

        // Getting the information from my main, that way I use the correct movie title and poster.
        val title = intent.getStringExtra("Title")
        val posterUrl = intent.getStringExtra("Poster")

        // Now connecting my XML elements.
        val titleTextView = findViewById<TextView>(R.id.textView4)
        val imageView = findViewById<ImageView>(R.id.imageView)

        titleTextView.text = title

    }
}
