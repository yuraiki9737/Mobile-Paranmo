package com.navigation.latihan.paranmo.ui.home.result

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.navigation.latihan.paranmo.R
import com.navigation.latihan.paranmo.databinding.ActivityResultBinding
import com.navigation.latihan.paranmo.ui.MainActivity

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bindingButton()
    }

    private fun bindingButton(){
        binding.backFavorit.setOnClickListener {
            val intent = Intent(this@ResultActivity , MainActivity::class.java)
            startActivity(intent)
        }
    }
}