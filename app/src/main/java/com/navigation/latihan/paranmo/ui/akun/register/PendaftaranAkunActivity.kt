package com.navigation.latihan.paranmo.ui.akun.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.navigation.latihan.paranmo.R
import com.navigation.latihan.paranmo.databinding.ActivityPendaftaranAkunBinding

class PendaftaranAkunActivity : AppCompatActivity() {

    private lateinit var binding : ActivityPendaftaranAkunBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPendaftaranAkunBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bindingConfigure()
    }

    private fun bindingConfigure(){
        binding.buttonDaftarAkun.setOnClickListener {

            val emailAccountParanmo = binding.email.text.toString()
            val nameAccountParanmo = binding.name.text.toString()
            val passwordAccountParanmo = binding.password.text.toString()


            when {

                nameAccountParanmo.isEmpty() -> {
                    binding.name.error = getString(R.string.name_account)
                }
                emailAccountParanmo.isEmpty() -> {
                    binding.email.error = getString(R.string.email_account)
                }
                passwordAccountParanmo.isEmpty() -> {
                    binding.password.error = getString(R.string.password_account)
                }
                else ->{

                    //registrationAccountParanmo()


                }

            }

        }

    }
}