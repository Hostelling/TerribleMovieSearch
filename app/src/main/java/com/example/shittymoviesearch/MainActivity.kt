  package com.example.shittymoviesearch

import android.os.Bundle
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import android.os.Handler

  class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        //Displaying my custom screen for launch
        setContentView(R.layout.launch)

        // After 3 seconds the app's main screen will load.
        Handler(Looper.getMainLooper()).postDelayed({
            setContentView(R.layout.main_screen)
        }, 3000)
    }
}