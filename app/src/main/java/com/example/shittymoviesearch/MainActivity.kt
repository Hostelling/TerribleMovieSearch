package com.example.shittymoviesearch

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
        // Displaying my custom screen for launch
        setContentView(R.layout.launch)

        // After 3 seconds the app's main screen will load.
        Handler(Looper.getMainLooper()).postDelayed({
            setContentView(R.layout.main_screen)

            // Initialize views after main_screen.xml is set as content view
            titleText = findViewById(R.id.textView3)
            movieInput = findViewById(R.id.movieInput)
            submitButton = findViewById(R.id.submitButton)

            submitButton.setOnClickListener {
                getData()
            }
        }, 3000)
    }

    private fun getData() {
        val query = movieInput.text.toString().trim()
        val url = "https://www.omdbapi.com/?apikey=45074816&s=$query"

        val queue = Volley.newRequestQueue(this)


        val request = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                val found = response.optString("Response")
                if (found == "True") {
                    Toast.makeText(this, "Yay! Here's your movie!", Toast.LENGTH_LONG).show()
                } else if (found == "False") {
                    Toast.makeText(this, "Aww... we don't have your movie", Toast.LENGTH_LONG).show()
                }
            },
            {
                Toast.makeText(this, "That didn't work", Toast.LENGTH_LONG).show()
            }
        )
        queue.add(request)
    }
}
