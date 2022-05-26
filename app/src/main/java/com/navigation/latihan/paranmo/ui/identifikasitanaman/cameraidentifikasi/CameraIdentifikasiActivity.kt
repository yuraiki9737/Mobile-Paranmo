package com.navigation.latihan.paranmo.ui.identifikasitanaman.cameraidentifikasi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.navigation.latihan.paranmo.R
import com.navigation.latihan.paranmo.databinding.ActivityCameraIdentifikasiBinding
import com.navigation.latihan.paranmo.databinding.ActivitySearchBinding
import com.navigation.latihan.paranmo.ui.MainActivity

class CameraIdentifikasiActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCameraIdentifikasiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCameraIdentifikasiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bindingButton()
    }

    private fun bindingButton(){
        binding.back.setOnClickListener {
            val intent = Intent(this@CameraIdentifikasiActivity , MainActivity::class.java)
            startActivity(intent)
        }
    }
}