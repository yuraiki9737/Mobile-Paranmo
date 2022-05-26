package com.navigation.latihan.paranmo.ui.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.navigation.latihan.paranmo.databinding.ActivitySplashScreenBinding
import com.navigation.latihan.paranmo.ui.MainActivity
import com.navigation.latihan.paranmo.ui.akun.login.LoginActivity

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySplashScreenBinding
    private val second = 2000L

    override fun onStart()
    {
        super.onStart()
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(applicationContext, MainActivity::class.java))
            finish()
        }, second)
    }
}