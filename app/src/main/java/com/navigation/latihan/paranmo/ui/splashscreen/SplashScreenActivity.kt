package com.navigation.latihan.paranmo.ui.splashscreen

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.core.app.ActivityOptionsCompat
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.navigation.latihan.paranmo.data.storage.PreferenceAkunParanmo
import com.navigation.latihan.paranmo.databinding.ActivitySplashScreenBinding
import com.navigation.latihan.paranmo.model.FactoryViewModel
import com.navigation.latihan.paranmo.model.HomeViewModel
import com.navigation.latihan.paranmo.ui.MainActivity
import com.navigation.latihan.paranmo.ui.akun.login.LoginActivity


private val Context.dataStoreParanmo: DataStore<Preferences> by preferencesDataStore("paranmo")
@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySplashScreenBinding
    private val second = 2000L

    private lateinit var homeViewModel: HomeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViewModel()
    }

    private fun setupViewModel() {
        val preferencesParanmo = PreferenceAkunParanmo.getInstanceParanmoApp(this.dataStoreParanmo)

        homeViewModel = ViewModelProvider(
            this, FactoryViewModel(preferencesParanmo)
        )[HomeViewModel::class.java]

        homeViewModel.getUserParanmo().observe(this){
            if(it.isLogin){
                gotoMainActivity(true)
            }
            else gotoMainActivity(false)
        }
    }

    private fun gotoMainActivity(boolean: Boolean) {

        if(boolean){
            Handler(Looper.getMainLooper()).postDelayed({

                startActivity(
                    Intent(this, MainActivity::class.java),
                    ActivityOptionsCompat.makeSceneTransitionAnimation(this as Activity).toBundle()
                )
                finish()
            }, second)
        } else {
            Handler(Looper.getMainLooper()).postDelayed({
                startActivity(
                    Intent(this, LoginActivity::class.java),
                    ActivityOptionsCompat.makeSceneTransitionAnimation(this as Activity).toBundle()
                )
                finish()
            }, second)
        }

        }

    }
