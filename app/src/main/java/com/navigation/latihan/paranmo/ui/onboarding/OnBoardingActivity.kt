package com.navigation.latihan.paranmo.ui.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.navigation.latihan.paranmo.databinding.ActivityOnBoardingBinding
import com.navigation.latihan.paranmo.ui.splashscreen.SplashScreenActivity

class OnBoardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnBoardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        button()
    }

    private fun button(){
        binding.getStarted.setOnClickListener(){
            val view = Intent(this@OnBoardingActivity, SplashScreenActivity::class.java)
            startActivity(view)
            finish()
        }
    }
}