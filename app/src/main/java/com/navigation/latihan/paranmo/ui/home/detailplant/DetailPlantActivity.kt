package com.navigation.latihan.paranmo.ui.home.detailplant

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.navigation.latihan.paranmo.R
import com.navigation.latihan.paranmo.data.Article
import com.navigation.latihan.paranmo.data.storage.PreferenceAkunParanmo
import com.navigation.latihan.paranmo.databinding.ActivityDetailPlantBinding
import com.navigation.latihan.paranmo.model.FactoryViewModel
import com.navigation.latihan.paranmo.model.HomeViewModel
import com.navigation.latihan.paranmo.ui.MainActivity
import com.navigation.latihan.paranmo.adapter.AdapterHome.Companion.DATA_ARTICLE
import com.navigation.latihan.paranmo.adapter.AdapterHomeFragment
import com.navigation.latihan.paranmo.ui.home.result.ResultActivity


private val Context.dataStoreParanmo: DataStore<Preferences> by preferencesDataStore("paranmo")
class DetailPlantActivity : AppCompatActivity() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var adapterHome: AdapterHomeFragment
    private lateinit var binding: ActivityDetailPlantBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPlantBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bindingButton()
        viewDetailArticle()
        recyclerViewInitArticle()
        recyclerViewAllArticle()
    }

    private fun viewDetailArticle() {
        val detailPlant = intent.getParcelableExtra<Article>(DATA_ARTICLE) as Article
        binding.namePlantHerbal.text = detailPlant.plant_name
        binding.nameLatinPlantHerbal.text = detailPlant.latin_name
        binding.namePerson.text = detailPlant.name
        binding.descriptionPlantHerbal.text = detailPlant.description
        binding.date.text = detailPlant.createdAt

        Glide.with(this)
            .load(detailPlant.photo_url)
            .placeholder(R.drawable.ic_place_holder)
            .error(R.drawable.ic_broken_image)
            .into(binding.photo)
    }



    @SuppressLint("NotifyDataSetChanged")
    private fun recyclerViewAllArticle() {
        val preferenceAkunParanmo = PreferenceAkunParanmo.getInstanceParanmoApp(dataStoreParanmo)
        homeViewModel = ViewModelProvider(
            this, FactoryViewModel(preferenceAkunParanmo)
        )[HomeViewModel::class.java]

        homeViewModel.getUserParanmo().observe(this){article ->

            homeViewModel.setParanmo(id = article.id)
        }

        homeViewModel.getArticle().observe(this) {
            if (it != null) {
                adapterHome.setArticleList(it)
                adapterHome.notifyDataSetChanged()


            }
        }
    }

    private fun recyclerViewInitArticle() {
        var row = 1
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            row= 1

        }
        binding.rvHome.layoutManager = StaggeredGridLayoutManager(row , StaggeredGridLayoutManager.HORIZONTAL)
        adapterHome = AdapterHomeFragment()
        binding.rvHome.adapter = adapterHome
    }

    private fun bindingButton() {
        binding.home.setOnClickListener {
            val intent = Intent(this@DetailPlantActivity , MainActivity::class.java)
            startActivity(intent)
        }
        binding.share.setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            startActivity(shareIntent)
        }
        binding.toggleButton.setOnClickListener {
            Toast.makeText(this, getString(R.string.coming_soon), Toast.LENGTH_SHORT).show()
        }

        binding.showAll.setOnClickListener{
            val intent = Intent(this , ResultActivity::class.java)
            startActivity(intent)
        }
    }
}