package com.example.shittymoviesearch

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class MainActivity : ComponentActivity() {
    private lateinit var titleText: TextView
    private lateinit var movieInput: EditText
    private lateinit var submitButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        if (savedInstanceState != null) {
            // Rotated or recreated: Skip splash screen and load main screen immediately
            setContentView(R.layout.main_screen)
            initViews()
        } else {
            // Display splash screen for 3 seconds, then load main screen
            setContentView(R.layout.launch)
            Handler(Looper.getMainLooper()).postDelayed({
                setContentView(R.layout.main_screen)
                initViews()
            }, 3000)
        }
    }

    private fun initViews() {
        titleText = findViewById(R.id.textView3)
        movieInput = findViewById(R.id.movieInput)
        submitButton = findViewById(R.id.submitButton)

        submitButton.setOnClickListener {
            getData()
        }
    }

    private fun getData() {
        val query = movieInput.text.toString().trim()
        val url = "https://www.omdbapi.com/?apikey=45074816&s=$query"

        val queue = Volley.newRequestQueue(this)

        val request = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                val found = response.optString("Response")
                // if Response is true you will get a quick toast message, then your poster and title will load.
                if (found == "True") {
                    Toast.makeText(this, "Yay! Here's your movie!", Toast.LENGTH_LONG).show()
                    val movies = response.optJSONArray("Search")
                    val firstMovie = movies?.optJSONObject(0)

                    if (firstMovie != null) {
                        // Using these two to grab the title as well as the poster
                        val moviename = firstMovie.optString("Title")
                        val poster = firstMovie.optString("Poster")

                        // Move onto screen 2, and put in the data from Title and poster.
                        val nextScreen = Intent(this, SecondaryActivity::class.java).apply {
                            putExtra("Title", moviename)
                            putExtra("Poster", poster)
                        }
                        startActivity(nextScreen)
                    }
                    // If your movie isn't found.
                } else if (found == "False") {
                    Toast.makeText(this, "Aww... we don't have your movie", Toast.LENGTH_LONG).show()
                }
            },
            // if there's any other error, such as a network error this will pop up as a toast message.
            {
                Toast.makeText(this, "That didn't work", Toast.LENGTH_LONG).show()
            }
        )
        queue.add(request)
    }
}
