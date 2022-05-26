package com.navigation.latihan.paranmo.ui.home.search

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.navigation.latihan.paranmo.R
import com.navigation.latihan.paranmo.databinding.ActivitySearchBinding
import com.navigation.latihan.paranmo.ui.MainActivity

class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bindingButton()
    }

    private fun bindingButton() {
    binding.backSearch.setOnClickListener{
        val view = Intent(this@SearchActivity, MainActivity::class.java)
        startActivity(view) }

        binding.button.setOnClickListener {
            Toast.makeText(this, getString(R.string.coming_soon), Toast.LENGTH_SHORT).show()
        }
    }
}