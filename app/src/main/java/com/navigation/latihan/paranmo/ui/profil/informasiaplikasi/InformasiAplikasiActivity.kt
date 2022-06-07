package com.navigation.latihan.paranmo.ui.profil.informasiaplikasi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.navigation.latihan.paranmo.databinding.ActivityInformasiAplikasiBinding
import com.navigation.latihan.paranmo.ui.profil.ProfileFragment

class InformasiAplikasiActivity : AppCompatActivity() {
    private lateinit var binding : ActivityInformasiAplikasiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInformasiAplikasiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backFavorit.setOnClickListener {
            val intent = Intent(this@InformasiAplikasiActivity , ProfileFragment::class.java)
            startActivity(intent)
        }
    }
}