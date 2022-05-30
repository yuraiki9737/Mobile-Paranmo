package com.navigation.latihan.paranmo.ui.akun.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.navigation.latihan.paranmo.R
import com.navigation.latihan.paranmo.api.RetrofitClient
import com.navigation.latihan.paranmo.data.LoginAccount
import com.navigation.latihan.paranmo.data.LoginUser
import com.navigation.latihan.paranmo.data.ResponseLogin
import com.navigation.latihan.paranmo.data.storage.PreferenceAkunParanmo
import com.navigation.latihan.paranmo.databinding.ActivityLoginBinding
import com.navigation.latihan.paranmo.model.FactoryViewModel
import com.navigation.latihan.paranmo.model.MainViewModel
import com.navigation.latihan.paranmo.ui.MainActivity
import com.navigation.latihan.paranmo.ui.akun.register.PendaftaranAkunActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


private val Context.dataStoreParanmo: DataStore<Preferences> by preferencesDataStore(name = "paranmo")
class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding
    private lateinit var loginParanmoModel : MainViewModel
    private lateinit var loginAccount: LoginAccount

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bindingConfigure()
        setUpViewModel()
        loginAccount()
    }

    private fun setUpViewModel() {
        val preferencesParanmo = PreferenceAkunParanmo.getInstanceParanmoApp(this.dataStoreParanmo)

        loginParanmoModel = ViewModelProvider(
            this@LoginActivity, FactoryViewModel(preferencesParanmo)
        )[MainViewModel::class.java]

        loginParanmoModel.getAkunParanmo().observe(this){ login ->
            this.loginAccount = login
        }
    }

    private fun bindingConfigure(){
        binding.daftarAkun.setOnClickListener{
            val view = Intent(this@LoginActivity, PendaftaranAkunActivity::class.java)
            startActivity(view)
            finish()
        }

    }

    private fun loginAccount(){

        binding.login.setOnClickListener{
            val emailAccountParanmo = binding.email.text.toString()
            val passwordAccountParanmo = binding.password.text.toString()

            binding.progressLogin.visibility = View.VISIBLE

            RetrofitClient().getApiParanmo()
                .loginAkunParanmo(LoginUser(emailAccountParanmo, passwordAccountParanmo))
                .enqueue(object : Callback<ResponseLogin>{
                    override fun onResponse(
                        call: Call<ResponseLogin>,
                        response: Response<ResponseLogin>,
                    ) {
                        if (response.code() == 200) {
                            val bodyUser = response.body()?.user as LoginAccount

                            loginParanmoModel.saveParanmoApp(LoginAccount(bodyUser.id,
                                bodyUser.name,
                                bodyUser.token,
                                true))
                            binding.progressLogin.visibility = View.INVISIBLE

                            Toast.makeText(applicationContext,
                                getString((R.string.sukses)),
                                Toast.LENGTH_SHORT).show()
                            val intentStory = Intent(this@LoginActivity, MainActivity::class.java)
                            intentStory.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                            startActivity(intentStory)
                            finish()
                        } else {
                            binding.progressLogin.visibility = View.INVISIBLE

                            Toast.makeText(applicationContext,
                                getString(R.string.failed),
                                Toast.LENGTH_SHORT).show()
                            Log.d(LoginActivity::class.java.simpleName,
                                response.body()?.message.toString())
                        }
                    }

                    override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                        Log.d("failure: ", t.message.toString())
                    }

                })

        }

    }
}