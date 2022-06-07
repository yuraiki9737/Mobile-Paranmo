package com.navigation.latihan.paranmo.ui.home.favorit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.navigation.latihan.paranmo.R
import com.navigation.latihan.paranmo.databinding.ActivityFavoritBinding
import com.navigation.latihan.paranmo.ui.MainActivity
import com.navigation.latihan.paranmo.ui.home.HomeFragment
import com.navigation.latihan.paranmo.ui.identifikasitanaman.cameraidentifikasi.CameraIdentifikasiActivity

class FavoritActivity : AppCompatActivity() {

    private lateinit var  binding: ActivityFavoritBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoritBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backFavorit.setOnClickListener{
            val view = Intent(this@FavoritActivity, MainActivity::class.java)
            startActivity(view)
        }

    }
}