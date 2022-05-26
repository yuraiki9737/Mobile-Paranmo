package com.navigation.latihan.paranmo.ui.home.notif

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.navigation.latihan.paranmo.R
import com.navigation.latihan.paranmo.databinding.ActivityNotifikasiBinding
import com.navigation.latihan.paranmo.ui.MainActivity

class NotifikasiActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNotifikasiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotifikasiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backNotification.setOnClickListener{
            val view = Intent(this@NotifikasiActivity, MainActivity::class.java)
            startActivity(view)
        }

        binding.read.setOnClickListener {
            binding.cardOff.visibility = View.VISIBLE
            binding.cardParanmoOff.visibility = View.VISIBLE
            binding.cardScanOff.visibility = View.VISIBLE
        }
    }
}