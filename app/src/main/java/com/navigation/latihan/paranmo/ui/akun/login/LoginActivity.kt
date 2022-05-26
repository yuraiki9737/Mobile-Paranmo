package com.navigation.latihan.paranmo.ui.akun.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.navigation.latihan.paranmo.R
import com.navigation.latihan.paranmo.databinding.ActivityLoginBinding
import com.navigation.latihan.paranmo.ui.akun.register.PendaftaranAkunActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bindingConfigure()
    }

    private fun bindingConfigure(){
        binding.daftarAkun.setOnClickListener{
            val view = Intent(this@LoginActivity, PendaftaranAkunActivity::class.java)
            startActivity(view)
            finish()
        }

        binding.login.setOnClickListener{
        val emailAccountParanmo = binding.email.text.toString()
        val passwordAccountParanmo = binding.password.text.toString()


        when {

            emailAccountParanmo.isEmpty() -> {
                binding.email.error = getString(R.string.email_account)
            }
            passwordAccountParanmo.isEmpty() -> {
                binding.password.error = getString(R.string.password_account)
            }
            else ->{

                login()


            }

        }

    }

    }

    private fun login(){
//        val view = Intent(this@LoginActivity, PendaftaranAkunActivity::class.java)
//    startActivity(view)
//    finish()
    }
}