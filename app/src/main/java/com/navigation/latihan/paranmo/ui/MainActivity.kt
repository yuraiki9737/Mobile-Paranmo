package com.navigation.latihan.paranmo.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.navigation.latihan.paranmo.R
import com.navigation.latihan.paranmo.databinding.ActivityMainBinding
import com.navigation.latihan.paranmo.ui.identifikasitanaman.resultidentifikasi.ResultIdentifikasiActivity
import com.navigation.latihan.paranmo.ui.profil.addplant.AddPlantActivity

class MainActivity : AppCompatActivity() {

    private val openAnim : Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.fab_open_anim) }
    private val closeAnim : Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.fab_close_anim) }
    private val forwardAnim : Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.forward_anim) }
    private val backwardAnim : Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.backward_anim) }

    private var click = false

    private lateinit var navController: NavController
    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNav.background = null
        binding.bottomNav.menu.getItem(2).isEnabled = false

        bindingFloat()

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)
        val navControl = findNavController(R.id.fragmentContainerView)

        bottomNav.setupWithNavController(navControl)
    }

    private fun bindingFloat(){
        binding.fabAdd.setOnClickListener{
            onAddClick()
        }
        binding.fabScan.setOnClickListener{
            val view = Intent(this@MainActivity, ResultIdentifikasiActivity::class.java)
            startActivity(view)
            Toast.makeText(this, getString(R.string.camera_identification), Toast.LENGTH_SHORT).show()
            onAddClick()
        }
        binding.fabCreate.setOnClickListener{

            val view = Intent(this@MainActivity, AddPlantActivity::class.java)
            startActivity(view)
            onAddClick()
        }
    }

    private fun onAddClick() {
        setVisibility(click)
        setAnim(click)
        click = !click
    }

    private fun setAnim(click: Boolean) {
        if(!click){
            binding.fabCreate.startAnimation(openAnim)
            binding.fabScan.startAnimation(openAnim)
            binding.fabAdd.startAnimation(forwardAnim)

        } else{
            binding.fabCreate.startAnimation(closeAnim)
            binding.fabScan.startAnimation(closeAnim)
            binding.fabAdd.startAnimation(backwardAnim)
        }

    }

    private fun setVisibility(click: Boolean) {
        if(!click){
            binding.fabCreate.visibility = View.VISIBLE
            binding.fabScan.visibility = View.VISIBLE
            binding.fabScan.isClickable= true
            binding.fabCreate.isClickable = true

        } else{
            binding.fabCreate.visibility = View.INVISIBLE
            binding.fabScan.visibility = View.INVISIBLE
            binding.fabScan.isClickable= false
            binding.fabCreate.isClickable = false
        }

    }
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}