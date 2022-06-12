package com.navigation.latihan.paranmo.ui.akun.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.navigation.latihan.paranmo.R
import com.navigation.latihan.paranmo.api.RetrofitClient
import com.navigation.latihan.paranmo.data.RegisterUser
import com.navigation.latihan.paranmo.data.response.ResponseRegister
import com.navigation.latihan.paranmo.databinding.ActivityPendaftaranAkunBinding
import com.navigation.latihan.paranmo.ui.akun.login.LoginActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PendaftaranAkunActivity : AppCompatActivity() {

    private lateinit var binding : ActivityPendaftaranAkunBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPendaftaranAkunBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bindingConfigure()
    }

    private fun bindingConfigure(){
        binding.back.setOnClickListener {
            val registrationIntentAccount = Intent(this@PendaftaranAkunActivity, LoginActivity::class.java)
            startActivity(registrationIntentAccount)
            finish()
        }
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

                    registrationAccountParanmo()


                }

            }

        }

    }

    private fun registrationAccountParanmo(){
        val email = binding.email.text.toString().trim()
        val name = binding.name.text.toString().trim()
        val password = binding.password.text.toString().trim()


        binding.progressRegister.visibility = View.VISIBLE
        RetrofitClient().getApiParanmo().registerAkunParanmo( RegisterUser(
            email,
            name,
            password))
            .enqueue(object: Callback<ResponseRegister>{

            override fun onFailure(call: Call<ResponseRegister>, t: Throwable){
                binding.progressRegister.visibility = View.INVISIBLE
                Log.d("failure: ", t.message.toString())
            }
            override fun onResponse(
                call: Call<ResponseRegister>,
                response: Response<ResponseRegister>,
            ) {
                if (response.isSuccessful) {
                    binding.progressRegister.visibility = View.VISIBLE
                    Toast.makeText(applicationContext,
                        getString(R.string.createdAccount),
                        Toast.LENGTH_LONG).show()
                    val registrationIntentAccount = Intent(this@PendaftaranAkunActivity, LoginActivity::class.java)
                    startActivity(registrationIntentAccount)
                    finish()
            } else{
                    binding.progressRegister.visibility = View.INVISIBLE
                    Toast.makeText(applicationContext,
                        getString(R.string.invalidAccount),
                        Toast.LENGTH_SHORT).show()
                }
            }


        })
    }


}