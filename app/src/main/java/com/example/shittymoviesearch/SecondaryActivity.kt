package com.example.shittymoviesearch

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.squareup.picasso.Picasso
import android.widget.Button


class SecondaryActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_screen)

        lateinit var backButton: Button

        // Getting the information from my main, that way I use the correct movie title and poster.
        val title = intent.getStringExtra("Title")
        val posterUrl = intent.getStringExtra("Poster")

        // Now connecting my XML elements.
        val titleTextView = findViewById<TextView>(R.id.textView4)
        val poster = findViewById<ImageView>(R.id.imageView)
        titleTextView.text = title

        if (posterUrl.isNullOrBlank() || posterUrl == "N/A") {
            poster.setImageResource(R.drawable.ic_no_poster)
        } else {
            Picasso.get()
                .load(posterUrl)
                .placeholder(R.drawable.ic_loading)
                .error(R.drawable.ic_no_poster)
                .fit()
                .centerInside()
                .into(poster)
        }

        backButton = findViewById(R.id.submitButton)

        backButton.setOnClickListener {
           finish()

        }

    }
}
